package com.example.factura.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    private Long folio;

    private Long codigoFacturacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sucursal_id", referencedColumnName = "id")
    private Sucursal sucursal;

    private double total;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getFolio() {
        return folio;
    }

    public void setFolio(Long folio) {
        this.folio = folio;
    }

    public Long getCodigoFacturacion() {
        return codigoFacturacion;
    }

    public void setCodigoFacturacion(Long codigoFacturacion) {
        this.codigoFacturacion = codigoFacturacion;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

}
