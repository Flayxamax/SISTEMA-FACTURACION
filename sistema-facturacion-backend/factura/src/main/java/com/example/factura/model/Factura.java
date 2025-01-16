package com.example.factura.model;

import java.util.Date;
import java.util.List;

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
import lombok.Data;

@Entity
@Data
@Table(name = "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "El campo RFC no puede estar vacío")
    private String rfc;

    @NotBlank(message = "El campo nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El campo dirección no puede estar vacío")
    private String direccion;

    @NotBlank(message = "El campo colonia no puede estar vacío")
    private String colonia;

    @NotBlank(message = "El campo ciudad no puede estar vacío")
    private String ciudad;

    @NotBlank(message = "El campo estado no puede estar vacío")
    private String estado;

    @NotBlank(message = "El campo código postal no puede estar vacío")
    private String codigoPostal;

    @ManyToOne
    @JoinColumn(name = "regimen_fiscal_id", referencedColumnName = "id")
    @NotBlank(message = "El campo régimen fiscal no puede estar vacío")
    private RegimenFiscal regimenFiscal;

    @ManyToOne
    @JoinColumn(name = "uso_cfdi_id", referencedColumnName = "id")
    @NotBlank(message = "El campo uso CFDI no puede estar vacío")
    private UsoCFDI usoCFDI;

    @NotBlank(message = "El campo subtotal no puede estar vacío")
    private double subTotal;

    @NotBlank(message = "El campo total no puede estar vacío")
    private double total;

    @NotBlank(message = "El campo fechaEmisión no puede estar vacío")
    private Date fechaEmision;

    @NotBlank(message = "El campo fechaEmisiónCertificado no puede estar vacío")
    private Date fechaEmisionCertificado;

    @OneToMany(mappedBy = "factura")
    @NotEmpty(message = "El campo tickets no puede estar vacío")
    private List<Ticket> tickets;

}
