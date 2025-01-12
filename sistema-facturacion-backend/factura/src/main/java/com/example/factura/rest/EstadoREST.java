package com.example.factura.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return ResponseEntity.ok(estados);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
