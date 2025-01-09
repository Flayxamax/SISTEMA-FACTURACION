package com.example.factura.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.factura.model.Ticket;
import com.example.service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketREST {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/{id}")
    private ResponseEntity<Ticket> getTicket(@PathVariable("id") String id) {
        Ticket ticket = ticketService.getById(Long.parseLong(id));
        if (ticket != null) {
            return ResponseEntity.ok(ticket);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }

    @GetMapping("/folio/{folio}/codigoFacturacion/{codigoFacturacion}/sucursal/{sucursal_id}")
    private ResponseEntity<Ticket> getTicketByParams(@PathVariable("folio") String folio,
            @PathVariable("codigoFacturacion") String codigoFacturacion,
            @PathVariable("sucursal_id") String sucursal_id) {
        Ticket ticket = ticketService.getByAttributes(Long.parseLong(folio), Long.parseLong(codigoFacturacion),
                Long.parseLong(sucursal_id));
        if (ticket != null) {
            return ResponseEntity.ok(ticket);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
