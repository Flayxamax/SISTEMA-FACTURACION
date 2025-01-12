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

import com.example.factura.model.RegimenFiscal;
import com.example.repository.RegimenRepository;

@Service
public class RegimenService {

    @Autowired
    private RegimenRepository regimenRepository;

    public void flush() {
        regimenRepository.flush();
    }

    public <S extends RegimenFiscal> S saveAndFlush(S entity) {
        return regimenRepository.saveAndFlush(entity);
    }

    public <S extends RegimenFiscal> List<S> saveAllAndFlush(Iterable<S> entities) {
        return regimenRepository.saveAllAndFlush(entities);
    }

    public void deleteAllInBatch(Iterable<RegimenFiscal> entities) {
        regimenRepository.deleteAllInBatch(entities);
    }

    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        regimenRepository.deleteAllByIdInBatch(ids);
    }

    public void deleteAllInBatch() {
        regimenRepository.deleteAllInBatch();
    }

    @SuppressWarnings("deprecation")
    public RegimenFiscal getOne(Long id) {
        return regimenRepository.getOne(id);
    }

    @SuppressWarnings("deprecation")
    public RegimenFiscal getById(Long id) {
        return regimenRepository.getById(id);
    }

    public RegimenFiscal getReferenceById(Long id) {
        return regimenRepository.getReferenceById(id);
    }

    public <S extends RegimenFiscal> List<S> findAll(Example<S> example) {
        return regimenRepository.findAll(example);
    }

    public <S extends RegimenFiscal> List<S> findAll(Example<S> example, Sort sort) {
        return regimenRepository.findAll(example, sort);
    }

    public <S extends RegimenFiscal> List<S> saveAll(Iterable<S> entities) {
        return regimenRepository.saveAll(entities);
    }

    public List<RegimenFiscal> findAll() {
        return regimenRepository.findAll();
    }

    public List<RegimenFiscal> findAllById(Iterable<Long> ids) {
        return regimenRepository.findAllById(ids);
    }

    public <S extends RegimenFiscal> S save(S entity) {
        return regimenRepository.save(entity);
    }

    public Optional<RegimenFiscal> findById(Long id) {
        return regimenRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return regimenRepository.existsById(id);
    }

    public long count() {
        return regimenRepository.count();
    }

    public void deleteById(Long id) {
        regimenRepository.deleteById(id);
    }

    public void delete(RegimenFiscal entity) {
        regimenRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Long> ids) {
        regimenRepository.deleteAllById(ids);
    }

    public void deleteAll(Iterable<? extends RegimenFiscal> entities) {
        regimenRepository.deleteAll(entities);
    }

    public void deleteAll() {
        regimenRepository.deleteAll();
    }

    public List<RegimenFiscal> findAll(Sort sort) {
        return regimenRepository.findAll(sort);
    }

    public Page<RegimenFiscal> findAll(Pageable pageable) {
        return regimenRepository.findAll(pageable);
    }

    public <S extends RegimenFiscal> Optional<S> findOne(Example<S> example) {
        return regimenRepository.findOne(example);
    }

    public <S extends RegimenFiscal> Page<S> findAll(Example<S> example, Pageable pageable) {
        return regimenRepository.findAll(example, pageable);
    }

    public <S extends RegimenFiscal> long count(Example<S> example) {
        return regimenRepository.count(example);
    }

    public <S extends RegimenFiscal> boolean exists(Example<S> example) {
        return regimenRepository.exists(example);
    }

    public <S extends RegimenFiscal, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction) {
        return regimenRepository.findBy(example, queryFunction);
    }

}
