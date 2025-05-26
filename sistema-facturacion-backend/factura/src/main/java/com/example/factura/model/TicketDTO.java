package com.example.factura.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TicketDTO {
    private Long id;
    private Date date;
    private Long folio;
    private Long codigoFacturacion;
    private double total;
    private double subTotal;
    private String tipoPago;
    @JsonProperty("factura_id")
    private Long facturaId;
    private List<Producto> productos;
    private Sucursal sucursal;
}
