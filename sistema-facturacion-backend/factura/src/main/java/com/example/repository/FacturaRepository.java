package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.factura.model.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

}
