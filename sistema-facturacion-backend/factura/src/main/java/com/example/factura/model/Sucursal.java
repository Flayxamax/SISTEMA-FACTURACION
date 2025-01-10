package com.example.factura.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "sucursal")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    @OneToOne(mappedBy = "sucursal")
    @JsonIgnore
    private Ticket ticket;

    public Sucursal() {
    }

    public Sucursal(String nombre) {
        this.nombre = nombre;
    }

    public Sucursal(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
