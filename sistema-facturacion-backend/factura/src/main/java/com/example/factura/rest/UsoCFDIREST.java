package com.example.factura.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.factura.exception.RequestException;
import com.example.factura.model.UsoCFDI;
import com.example.service.UsoCFDIService;

@RestController
@RequestMapping("/usocfdi")
public class UsoCFDIREST {

    @Autowired
    private UsoCFDIService usocfdiService;

    @GetMapping
    private ResponseEntity<List<UsoCFDI>> getAllUsosCFDI() {
        try {
            List<UsoCFDI> usosCFDI = usocfdiService.findAll();
            if (usosCFDI.isEmpty()) {
                throw new RequestException("U-102", "No hay usos CFDI disponibles");
            }
            return ResponseEntity.ok(usosCFDI);
        } catch (RequestException e) {
            throw e;
        } catch (Exception e) {
            throw new RequestException("U-101", "Error al obtener los usos CFDI");
        }
    }
}
