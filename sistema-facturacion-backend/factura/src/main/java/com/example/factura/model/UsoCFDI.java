package com.example.factura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "uso_cfdi")
public class UsoCFDI {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String clave;

    private String descripcion;

    @OneToMany(mappedBy = "usoCFDI")
    @JsonIgnore
    private List<Factura> facturas;

}
