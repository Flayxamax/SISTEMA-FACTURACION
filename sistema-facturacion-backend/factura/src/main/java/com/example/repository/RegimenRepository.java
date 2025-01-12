package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.factura.model.RegimenFiscal;

@Repository
public interface RegimenRepository extends JpaRepository<RegimenFiscal, Long> {

}
