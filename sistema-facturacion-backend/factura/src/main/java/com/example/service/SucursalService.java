package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.example.factura.model.Sucursal;
import com.example.repository.SucursalRepository;

@Service
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    public void flush() {
        sucursalRepository.flush();
    }

    public <S extends Sucursal> S saveAndFlush(S entity) {
        return sucursalRepository.saveAndFlush(entity);
    }

    public <S extends Sucursal> List<S> saveAllAndFlush(Iterable<S> entities) {
        return sucursalRepository.saveAllAndFlush(entities);
    }

    public void deleteAllInBatch(Iterable<Sucursal> entities) {
        sucursalRepository.deleteAllInBatch(entities);
    }

    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        sucursalRepository.deleteAllByIdInBatch(ids);
    }

    public void deleteAllInBatch() {
        sucursalRepository.deleteAllInBatch();
    }

    public Sucursal getOne(Long id) {
        return sucursalRepository.getReferenceById(id);
    }

    public Sucursal getById(Long id) {
        return sucursalRepository.findById(id).orElse(null);
    }

    public Sucursal getReferenceById(Long id) {
        return sucursalRepository.getReferenceById(id);
    }

    public Sucursal getByName(String nombre) {
        List<Sucursal> sucursales = sucursalRepository.findAll();
        for (Sucursal sucursal : sucursales) {
            if (sucursal.getNombre().equalsIgnoreCase(nombre)) {
                return sucursal;
            }
        }
        return null;
    }

    public <S extends Sucursal> List<S> findAll(Example<S> example) {
        return sucursalRepository.findAll(example);
    }

    public <S extends Sucursal> List<S> findAll(Example<S> example, Sort sort) {
        return sucursalRepository.findAll(example, sort);
    }

    public <S extends Sucursal> List<S> saveAll(Iterable<S> entities) {
        return sucursalRepository.saveAll(entities);
    }

    public List<Sucursal> findAll() {
        return sucursalRepository.findAll();
    }

    public List<Sucursal> findAllById(Iterable<Long> ids) {
        return sucursalRepository.findAllById(ids);
    }

    public <S extends Sucursal> S save(S entity) {
        return sucursalRepository.save(entity);
    }

    public Optional<Sucursal> findById(Long id) {
        return sucursalRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return sucursalRepository.existsById(id);
    }

    public long count() {
        return sucursalRepository.count();
    }

    public void deleteById(Long id) {
        sucursalRepository.deleteById(id);
    }

    public void delete(Sucursal entity) {
        sucursalRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Long> ids) {
        sucursalRepository.deleteAllById(ids);
    }

    public void deleteAll(Iterable<? extends Sucursal> entities) {
        sucursalRepository.deleteAll(entities);
    }

    public void deleteAll() {
        sucursalRepository.deleteAll();
    }

    public List<Sucursal> findAll(Sort sort) {
        return sucursalRepository.findAll(sort);
    }

    public Page<Sucursal> findAll(Pageable pageable) {
        return sucursalRepository.findAll(pageable);
    }

    public <S extends Sucursal> Optional<S> findOne(Example<S> example) {
        return sucursalRepository.findOne(example);
    }

    public <S extends Sucursal> Page<S> findAll(Example<S> example, Pageable pageable) {
        return sucursalRepository.findAll(example, pageable);
    }

    public <S extends Sucursal> long count(Example<S> example) {
        return sucursalRepository.count(example);
    }

    public <S extends Sucursal> boolean exists(Example<S> example) {
        return sucursalRepository.exists(example);
    }

    public <S extends Sucursal, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        return sucursalRepository.findBy(example, queryFunction);
    }
}
