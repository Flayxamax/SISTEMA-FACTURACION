package com.example.factura.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "factura")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "El campo RFC no puede estar vacio")
    private String rfc;

    @NotBlank(message = "El campo nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El campo direccion no puede estar vacio")
    private String direccion;

    @NotBlank(message = "El campo colonia no puede estar vacio")
    private String colonia;

    @NotBlank(message = "El campo ciudad no puede estar vacio")
    private String ciudad;

    @NotBlank(message = "El campo estado no puede estar vacio")
    private String estado;

    @NotBlank(message = "El campo codigo postal no puede estar vacio")
    private String codigoPostal;

    @ManyToOne
    @JoinColumn(name = "regimen_fiscal_id", referencedColumnName = "id")
    @NotNull(message = "El campo regimen fiscal no puede estar vacio")
    private RegimenFiscal regimenFiscal;

    @ManyToOne
    @JoinColumn(name = "uso_cfdi_id", referencedColumnName = "id")
    @NotNull(message = "El campo uso CFDI no puede estar vacio")
    private UsoCFDI usoCFDI;

    private double subTotal;

    private double total;

    private Date fechaEmision;

    private Date fechaEmisionCertificado;

    @OneToMany(mappedBy = "factura")
    @NotEmpty(message = "El campo tickets no puede estar vacio")
    private List<Ticket> tickets;

}
