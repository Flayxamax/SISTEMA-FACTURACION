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

import com.example.factura.model.Producto;
import com.example.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        productoRepository.deleteAllByIdInBatch(ids);
    }

    public void deleteAllInBatch() {
        productoRepository.deleteAllInBatch();
    }

    public void deleteAllInBatch(Iterable<Producto> entities) {
        productoRepository.deleteAllInBatch(entities);
    }

    public <S extends Producto> List<S> findAll(Example<S> example) {
        return productoRepository.findAll(example);
    }

    public <S extends Producto> List<S> findAll(Example<S> example, Sort sort) {
        return productoRepository.findAll(example, sort);
    }

    public void flush() {
        productoRepository.flush();
    }

    public Producto getById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto getOne(Long id) {
        return productoRepository.getReferenceById(id);
    }

    public Producto getReferenceById(Long id) {
        return productoRepository.getReferenceById(id);
    }

    public <S extends Producto> List<S> saveAllAndFlush(Iterable<S> entities) {
        return productoRepository.saveAllAndFlush(entities);
    }

    public <S extends Producto> S saveAndFlush(S entity) {
        return productoRepository.saveAndFlush(entity);
    }

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public List<Producto> findAllById(Iterable<Long> ids) {
        return productoRepository.findAllById(ids);
    }

    public <S extends Producto> List<S> saveAll(Iterable<S> entities) {
        return productoRepository.saveAll(entities);
    }

    public long count() {
        return productoRepository.count();
    }

    public void delete(Producto entity) {
        productoRepository.delete(entity);
    }

    public void deleteAll() {
        productoRepository.deleteAll();
    }

    public void deleteAll(Iterable<? extends Producto> entities) {
        productoRepository.deleteAll(entities);
    }

    public void deleteAllById(Iterable<? extends Long> ids) {
        productoRepository.deleteAllById(ids);
    }

    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return productoRepository.existsById(id);
    }

    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    public <S extends Producto> S save(S entity) {
        return productoRepository.save(entity);
    }

    public List<Producto> findAll(Sort sort) {
        return productoRepository.findAll(sort);
    }

    public Page<Producto> findAll(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    public <S extends Producto> long count(Example<S> example) {
        return productoRepository.count(example);
    }

    public <S extends Producto> boolean exists(Example<S> example) {
        return productoRepository.exists(example);
    }

    public <S extends Producto> Page<S> findAll(Example<S> example, Pageable pageable) {
        return productoRepository.findAll(example, pageable);
    }

    public <S extends Producto, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        return productoRepository.findBy(example, queryFunction);
    }

    public <S extends Producto> Optional<S> findOne(Example<S> example) {
        return productoRepository.findOne(example);
    }
}
