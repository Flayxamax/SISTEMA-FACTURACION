package com.example.factura.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.factura.exception.RequestException;
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
            if (regimenes.isEmpty()) {
                throw new RequestException("R-102", "No hay regimenes disponibles");
            }
            return ResponseEntity.ok(regimenes);
        } catch (RequestException e) {
            throw e;
        } catch (Exception e) {
            throw new RequestException("R-101", "Error al obtener los regimenes");
        }
    }
}
