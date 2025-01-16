package com.example.factura.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.factura.exception.RequestException;
import com.example.factura.model.Ticket;
import com.example.service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketREST {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/{id}")
    private ResponseEntity<Ticket> getTicket(@PathVariable("id") String id) {
        try {
            Ticket ticket = ticketService.getById(Long.parseLong(id));
            if (ticket != null) {
                return ResponseEntity.ok(ticket);
            } else {
                throw new RequestException("T-102", "Ticket no encontrado");
            }
        } catch (RequestException e) {
            throw e;
        } catch (Exception e) {
            throw new RequestException("T-101", "Error al obtener el ticket");
        }
    }

    @GetMapping("/folio/{folio}/codigoFacturacion/{codigoFacturacion}/sucursal/{sucursal_id}")
    private ResponseEntity<Ticket> getTicketByParams(@PathVariable("folio") String folio,
            @PathVariable("codigoFacturacion") String codigoFacturacion,
            @PathVariable("sucursal_id") String sucursal_id) {
        try {
            Ticket ticket = ticketService.getByAttributes(Long.parseLong(folio), Long.parseLong(codigoFacturacion),
                    Long.parseLong(sucursal_id));
            if (ticket != null) {
                if (ticket.getFactura() == null) {
                    return ResponseEntity.ok(ticket);
                } else {
                    throw new RequestException("T-103", "Ticket ya facturado");
                }
            } else {
                throw new RequestException("T-102", "Ticket no encontrado");
            }
        } catch (RequestException e) {
            throw e;
        } catch (Exception e) {
            throw new RequestException("T-101", "Error al obtener el ticket");
        }
    }

}
