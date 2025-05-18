package com.example.service;

import java.io.File;
import java.io.FileWriter;
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
import com.example.factura.model.Factura;
import com.example.repository.FacturaRepository;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public File convertFacturaToXml(Factura factura) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.findAndRegisterModules();

        String xmlContent = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(factura);

        File tempFile = File.createTempFile("factura-" + factura.getId(), ".xml");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(xmlContent);
        }

        return tempFile;
    }

    public void flush() {
        facturaRepository.flush();
    }

    public <S extends Factura> S saveAndFlush(S entity) {
        return facturaRepository.saveAndFlush(entity);
    }

    public <S extends Factura> List<S> saveAllAndFlush(Iterable<S> entities) {
        return facturaRepository.saveAllAndFlush(entities);
    }

    public void deleteAllInBatch(Iterable<Factura> entities) {
        facturaRepository.deleteAllInBatch(entities);
    }

    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        facturaRepository.deleteAllByIdInBatch(ids);
    }

    public void deleteAllInBatch() {
        facturaRepository.deleteAllInBatch();
    }

    public Factura getOne(Long id) {
        return facturaRepository.getReferenceById(id);
    }

    public Factura getById(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    public Factura getReferenceById(Long id) {
        return facturaRepository.getReferenceById(id);
    }

    public <S extends Factura> List<S> findAll(Example<S> example) {
        return facturaRepository.findAll(example);
    }

    public <S extends Factura> List<S> findAll(Example<S> example, Sort sort) {
        return facturaRepository.findAll(example, sort);
    }

    public <S extends Factura> List<S> saveAll(Iterable<S> entities) {
        return facturaRepository.saveAll(entities);
    }

    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    public List<Factura> findAllById(Iterable<Long> ids) {
        return facturaRepository.findAllById(ids);
    }

    public <S extends Factura> S save(S entity) {
        return facturaRepository.save(entity);
    }

    public Optional<Factura> findById(Long id) {
        return facturaRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return facturaRepository.existsById(id);
    }

    public long count() {
        return facturaRepository.count();
    }

    public void deleteById(Long id) {
        facturaRepository.deleteById(id);
    }

    public void delete(Factura entity) {
        facturaRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Long> ids) {
        facturaRepository.deleteAllById(ids);
    }

    public void deleteAll(Iterable<? extends Factura> entities) {
        facturaRepository.deleteAll(entities);
    }

    public void deleteAll() {
        facturaRepository.deleteAll();
    }

    public List<Factura> findAll(Sort sort) {
        return facturaRepository.findAll(sort);
    }

    public Page<Factura> findAll(Pageable pageable) {
        return facturaRepository.findAll(pageable);
    }

    public <S extends Factura> Optional<S> findOne(Example<S> example) {
        return facturaRepository.findOne(example);
    }

    public <S extends Factura> Page<S> findAll(Example<S> example, Pageable pageable) {
        return facturaRepository.findAll(example, pageable);
    }

    public <S extends Factura> long count(Example<S> example) {
        return facturaRepository.count(example);
    }

    public <S extends Factura> boolean exists(Example<S> example) {
        return facturaRepository.exists(example);
    }

    public <S extends Factura, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        return facturaRepository.findBy(example, queryFunction);
    }
}
