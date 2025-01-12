package com.example.factura.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.factura.model.RegimenFiscal;
import com.example.service.RegimenService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/regimen")
public class RegimenREST {

    @Autowired
    private RegimenService regimenService;

    @GetMapping
    private ResponseEntity<List<RegimenFiscal>> getAllRegimenes() {
        try {
            List<RegimenFiscal> regimenes = regimenService.findAll();
            return ResponseEntity.ok(regimenes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
