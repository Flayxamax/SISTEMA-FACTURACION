package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.factura.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
