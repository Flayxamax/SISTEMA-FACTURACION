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

import com.example.factura.model.Estado;
import com.example.repository.EstadoRepository;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public void flush() {
        estadoRepository.flush();
    }

    public <S extends Estado> S saveAndFlush(S entity) {
        return estadoRepository.saveAndFlush(entity);
    }

    public <S extends Estado> List<S> saveAllAndFlush(Iterable<S> entities) {
        return estadoRepository.saveAllAndFlush(entities);
    }

    public void deleteAllInBatch(Iterable<Estado> entities) {
        estadoRepository.deleteAllInBatch(entities);
    }

    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        estadoRepository.deleteAllByIdInBatch(ids);
    }

    public void deleteAllInBatch() {
        estadoRepository.deleteAllInBatch();
    }

    @SuppressWarnings("deprecation")
    public Estado getOne(Long id) {
        return estadoRepository.getOne(id);
    }

    @SuppressWarnings("deprecation")
    public Estado getById(Long id) {
        return estadoRepository.getById(id);
    }

    public Estado getReferenceById(Long id) {
        return estadoRepository.getReferenceById(id);
    }

    public <S extends Estado> List<S> findAll(Example<S> example) {
        return estadoRepository.findAll(example);
    }

    public <S extends Estado> List<S> findAll(Example<S> example, Sort sort) {
        return estadoRepository.findAll(example, sort);
    }

    public <S extends Estado> List<S> saveAll(Iterable<S> entities) {
        return estadoRepository.saveAll(entities);
    }

    public List<Estado> findAll() {
        return estadoRepository.findAll();
    }

    public List<Estado> findAllById(Iterable<Long> ids) {
        return estadoRepository.findAllById(ids);
    }

    public <S extends Estado> S save(S entity) {
        return estadoRepository.save(entity);
    }

    public Optional<Estado> findById(Long id) {
        return estadoRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return estadoRepository.existsById(id);
    }

    public long count() {
        return estadoRepository.count();
    }

    public void deleteById(Long id) {
        estadoRepository.deleteById(id);
    }

    public void delete(Estado entity) {
        estadoRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Long> ids) {
        estadoRepository.deleteAllById(ids);
    }

    public void deleteAll(Iterable<? extends Estado> entities) {
        estadoRepository.deleteAll(entities);
    }

    public void deleteAll() {
        estadoRepository.deleteAll();
    }

    public List<Estado> findAll(Sort sort) {
        return estadoRepository.findAll(sort);
    }

    public Page<Estado> findAll(Pageable pageable) {
        return estadoRepository.findAll(pageable);
    }

    public <S extends Estado> Optional<S> findOne(Example<S> example) {
        return estadoRepository.findOne(example);
    }

    public <S extends Estado> Page<S> findAll(Example<S> example, Pageable pageable) {
        return estadoRepository.findAll(example, pageable);
    }

    public <S extends Estado> long count(Example<S> example) {
        return estadoRepository.count(example);
    }

    public <S extends Estado> boolean exists(Example<S> example) {
        return estadoRepository.exists(example);
    }

    public <S extends Estado, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        return estadoRepository.findBy(example, queryFunction);
    }

}
