package com.example.factura.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String RFC;

    private String nombre;

    private String direccion;

    private String colonia;

    private String ciudad;

    private String estado;

    private String codigoPostal;

    private String regimenFiscal;

    private String usoCFDI;

    private double subTotal;

    private double total;

    private Date fechaEmision;

    private Date fechaEmisionCertificado;

    @OneToMany(mappedBy = "factura")
    private List<Ticket> tickets;

}
