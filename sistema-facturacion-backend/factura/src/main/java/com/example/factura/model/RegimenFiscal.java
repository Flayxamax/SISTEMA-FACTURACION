package com.example.factura.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "regimen_fiscal")
public class RegimenFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String clave;

    private String descripcion;

}
