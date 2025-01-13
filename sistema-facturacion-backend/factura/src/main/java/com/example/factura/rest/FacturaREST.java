package com.example.factura.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.FacturaService;

@RestController
@RequestMapping("/factura")
public class FacturaREST {

    @Autowired
    private FacturaService facturaService;
}
