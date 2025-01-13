package com.example.factura.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.factura.exception.RequestException;
import com.example.factura.model.Estado;
import com.example.service.EstadoService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/estado")
public class EstadoREST {

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    private ResponseEntity<List<Estado>> getAllEstados() {
        try {
            List<Estado> estados = estadoService.findAll();
            if (estados.isEmpty()) {
                throw new RequestException("E-102", "No hay estados disponibles");
            }
            return ResponseEntity.ok(estados);
        } catch (RequestException e) {
            throw e;
        } catch (Exception e) {
            throw new RequestException("E-101", "Error al obtener los estados");
        }
    }

}
