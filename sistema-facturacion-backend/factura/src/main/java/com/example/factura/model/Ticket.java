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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "ticket")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    private Long folio;

    private Long codigoFacturacion;

    @ManyToOne
    @JoinColumn(name = "sucursal_id", referencedColumnName = "id")
    private Sucursal sucursal;

    private double total;

    private double subTotal;

    private TipoPago tipoPago;

    @ManyToOne
    @JoinColumn(name = "factura_id", referencedColumnName = "id")
    private Factura factura;

    @ManyToMany
    @JoinTable(name = "ticket_producto", joinColumns = @JoinColumn(name = "ticket_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private List<Producto> productos;

    public Ticket() {
    }

    public Ticket(Long folio, Long codigoFacturacion, Sucursal sucursal) {
        this.folio = folio;
        this.codigoFacturacion = codigoFacturacion;
        this.sucursal = sucursal;
    }

    public Ticket(Date date, Long folio, Long codigoFacturacion, Sucursal sucursal, double total,
            List<Producto> productos) {
        this.date = date;
        this.folio = folio;
        this.codigoFacturacion = codigoFacturacion;
        this.sucursal = sucursal;
        this.total = total;
        this.productos = productos;
    }

    public Ticket(Date date, Long folio, Long codigoFacturacion, Sucursal sucursal, double total, double subTotal,
            TipoPago tipoPago, List<Producto> productos) {
        this.date = date;
        this.folio = folio;
        this.codigoFacturacion = codigoFacturacion;
        this.sucursal = sucursal;
        this.total = total;
        this.subTotal = subTotal;
        this.tipoPago = tipoPago;
        this.productos = productos;
    }
}
