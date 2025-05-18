package com.example.factura.rest;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.factura.exception.BusinessException;
import com.example.factura.model.Factura;
import com.example.factura.model.Ticket;
import com.example.factura.model.TokenResponse;
import com.example.service.FacturaService;
import com.example.service.TicketService;
import com.example.utils.JwtTokenUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/factura")
public class FacturaREST {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<TokenResponse> create(@Valid @RequestBody Factura factura) {
        try {
            validateTickets(factura.getTickets());

            double subTotal = calculateSubTotal(factura.getTickets());
            double total = subTotal + (subTotal * 0.16);

            factura.setSubTotal(subTotal);
            factura.setTotal(total);
            factura.setFechaEmision(new Date());
            factura.setFechaEmisionCertificado(new Date());
            Factura facturaCreated = facturaService.save(factura);

            updateTicketsWithFactura(facturaCreated, factura.getTickets());

            String token = JwtTokenUtil.generateToken(facturaCreated.getId(), 60);
            TokenResponse response = new TokenResponse();
            response.setToken(token);
            return ResponseEntity.ok(response);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("F-101", "Error al crear la factura", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void validateTickets(List<Ticket> tickets) throws BusinessException {
        for (Ticket ticket : tickets) {
            Ticket existingTicket = ticketService.getById(ticket.getId());
            try {
                String existingTicketJson = new ObjectMapper().writeValueAsString(existingTicket);
                String ticketJson = new ObjectMapper().writeValueAsString(ticket);

                if (!existingTicketJson.equals(ticketJson)) {
                    throw new BusinessException("T-404", "Los tickets no coinciden", HttpStatus.NOT_FOUND);
                }
            } catch (JsonProcessingException e) {
                throw new BusinessException("T-500", "Error al procesar JSON", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    private double calculateSubTotal(List<Ticket> tickets) {
        return tickets.stream().mapToDouble(Ticket::getSubTotal).sum();
    }

    private void updateTicketsWithFactura(Factura factura, List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            ticket.setFactura(factura);
            ticketService.save(ticket);
        }
    }

    @SuppressWarnings("deprecation")
    @GetMapping("/{token}")
    public ResponseEntity<Factura> getFacturaByToken(@PathVariable String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(JwtTokenUtil.SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Long facturaId = Long.parseLong(claims.getSubject());

            if (claims.getExpiration().before(new Date())) {
                throw new BusinessException("F-202", "El token ha expirado", HttpStatus.UNAUTHORIZED);
            }

            Factura factura = facturaService.getById(facturaId);
            if (factura == null) {
                throw new BusinessException("F-404", "Factura no encontrada", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(factura);
        } catch (ExpiredJwtException e) {
            throw new BusinessException("F-202", "El token ha expirado", HttpStatus.UNAUTHORIZED);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("F-201", "Token inválido", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/download-xml/{token}")
    public ResponseEntity<FileSystemResource> descargarXml(@PathVariable String token) throws Exception {
        Factura factura = getFacturaByToken(token).getBody();

        File xmlFile = facturaService.convertFacturaToXml(factura);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=factura-" + factura.getId() + ".xml")
                .contentType(MediaType.APPLICATION_XML)
                .body(new FileSystemResource(xmlFile));
    }
}
