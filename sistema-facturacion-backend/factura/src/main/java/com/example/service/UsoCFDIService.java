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

import com.example.factura.model.UsoCFDI;
import com.example.repository.UsoCFDIRepository;

@Service
public class UsoCFDIService {

    @Autowired
    private UsoCFDIRepository usoCFDIRepository;

    public void flush() {
        usoCFDIRepository.flush();
    }

    public <S extends UsoCFDI> S saveAndFlush(S entity) {
        return usoCFDIRepository.saveAndFlush(entity);
    }

    public <S extends UsoCFDI> List<S> saveAllAndFlush(Iterable<S> entities) {
        return usoCFDIRepository.saveAllAndFlush(entities);
    }

    public void deleteAllInBatch(Iterable<UsoCFDI> entities) {
        usoCFDIRepository.deleteAllInBatch(entities);
    }

    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        usoCFDIRepository.deleteAllByIdInBatch(ids);
    }

    public void deleteAllInBatch() {
        usoCFDIRepository.deleteAllInBatch();
    }

    @SuppressWarnings("deprecation")
    public UsoCFDI getOne(Long id) {
        return usoCFDIRepository.getOne(id);
    }

    @SuppressWarnings("deprecation")
    public UsoCFDI getById(Long id) {
        return usoCFDIRepository.getById(id);
    }

    public UsoCFDI getReferenceById(Long id) {
        return usoCFDIRepository.getReferenceById(id);
    }

    public <S extends UsoCFDI> List<S> findAll(Example<S> example) {
        return usoCFDIRepository.findAll(example);
    }

    public <S extends UsoCFDI> List<S> findAll(Example<S> example, Sort sort) {
        return usoCFDIRepository.findAll(example, sort);
    }

    public <S extends UsoCFDI> List<S> saveAll(Iterable<S> entities) {
        return usoCFDIRepository.saveAll(entities);
    }

    public List<UsoCFDI> findAll() {
        return usoCFDIRepository.findAll();
    }

    public List<UsoCFDI> findAllById(Iterable<Long> ids) {
        return usoCFDIRepository.findAllById(ids);
    }

    public <S extends UsoCFDI> S save(S entity) {
        return usoCFDIRepository.save(entity);
    }

    public Optional<UsoCFDI> findById(Long id) {
        return usoCFDIRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return usoCFDIRepository.existsById(id);
    }

    public long count() {
        return usoCFDIRepository.count();
    }

    public void deleteById(Long id) {
        usoCFDIRepository.deleteById(id);
    }

    public void delete(UsoCFDI entity) {
        usoCFDIRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Long> ids) {
        usoCFDIRepository.deleteAllById(ids);
    }

    public void deleteAll(Iterable<? extends UsoCFDI> entities) {
        usoCFDIRepository.deleteAll(entities);
    }

    public void deleteAll() {
        usoCFDIRepository.deleteAll();
    }

    public List<UsoCFDI> findAll(Sort sort) {
        return usoCFDIRepository.findAll(sort);
    }

    public Page<UsoCFDI> findAll(Pageable pageable) {
        return usoCFDIRepository.findAll(pageable);
    }

    public <S extends UsoCFDI> Optional<S> findOne(Example<S> example) {
        return usoCFDIRepository.findOne(example);
    }

    public <S extends UsoCFDI> Page<S> findAll(Example<S> example, Pageable pageable) {
        return usoCFDIRepository.findAll(example, pageable);
    }

    public <S extends UsoCFDI> long count(Example<S> example) {
        return usoCFDIRepository.count(example);
    }

    public <S extends UsoCFDI> boolean exists(Example<S> example) {
        return usoCFDIRepository.exists(example);
    }

    public <S extends UsoCFDI, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        return usoCFDIRepository.findBy(example, queryFunction);
    }
}
