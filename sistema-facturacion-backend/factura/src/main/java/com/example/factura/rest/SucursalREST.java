package com.example.factura.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.factura.exception.RequestException;
import com.example.factura.model.Sucursal;
import com.example.service.SucursalService;

@RestController
@RequestMapping("/sucursal")
public class SucursalREST {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    private ResponseEntity<List<Sucursal>> getAllSucursales() {
        try {
            List<Sucursal> sucursales = sucursalService.findAll();
            if (sucursales.isEmpty()) {
                throw new RequestException("S-102", "No hay sucursales disponibles");
            }
            return ResponseEntity.ok(sucursales);
        } catch (RequestException e) {
            throw e;
        } catch (Exception e) {
            throw new RequestException("S-101", "Error al obtener las sucursales");
        }
    }

}
