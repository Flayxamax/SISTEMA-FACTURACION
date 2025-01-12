package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.factura.model.UsoCFDI;

@Repository
public interface UsoCFDIRepository extends JpaRepository<UsoCFDI, Long> {

}
