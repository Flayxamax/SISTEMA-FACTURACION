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

import com.example.factura.model.Ticket;
import com.example.repository.TicketRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public void flush() {
        ticketRepository.flush();
    }

    public <S extends Ticket> S saveAndFlush(S entity) {
        return ticketRepository.saveAndFlush(entity);
    }

    public <S extends Ticket> List<S> saveAllAndFlush(Iterable<S> entities) {
        return ticketRepository.saveAllAndFlush(entities);
    }

    public void deleteAllInBatch(Iterable<Ticket> entities) {
        ticketRepository.deleteAllInBatch(entities);
    }

    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        ticketRepository.deleteAllByIdInBatch(ids);
    }

    public void deleteAllInBatch() {
        ticketRepository.deleteAllInBatch();
    }

    public Ticket getOne(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public Ticket getByAttributes(Long folio, Long codigoFacturacion, Long sucursalId) {
        String jpql = "SELECT t FROM Ticket t WHERE t.folio = :folio AND t.codigoFacturacion = :codigoFacturacion AND t.sucursal.id = :sucursalId";
        TypedQuery<Ticket> query = entityManager.createQuery(jpql, Ticket.class);
        query.setParameter("folio", folio);
        query.setParameter("codigoFacturacion", codigoFacturacion);
        query.setParameter("sucursalId", sucursalId);

        return query.getResultStream().findFirst().orElse(null);
    }

    public Ticket getByFolioAndCodigoFacturacion(Long folio, Long codigoFacturacion) {
        String jpql = "SELECT t FROM Ticket t WHERE t.folio = :folio AND t.codigoFacturacion = :codigoFacturacion";
        TypedQuery<Ticket> query = entityManager.createQuery(jpql, Ticket.class);
        query.setParameter("folio", folio);
        query.setParameter("codigoFacturacion", codigoFacturacion);

        return query.getResultStream().findFirst().orElse(null);
    }

    public Ticket getById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public Ticket getReferenceById(Long id) {
        return ticketRepository.getReferenceById(id);
    }

    public <S extends Ticket> List<S> findAll(Example<S> example) {
        return ticketRepository.findAll(example);
    }

    public <S extends Ticket> List<S> findAll(Example<S> example, Sort sort) {
        return ticketRepository.findAll(example, sort);
    }

    public <S extends Ticket> List<S> saveAll(Iterable<S> entities) {
        return ticketRepository.saveAll(entities);
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public List<Ticket> findAllById(Iterable<Long> ids) {
        return ticketRepository.findAllById(ids);
    }

    public <S extends Ticket> S save(S entity) {
        return ticketRepository.save(entity);
    }

    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return ticketRepository.existsById(id);
    }

    public long count() {
        return ticketRepository.count();
    }

    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }

    public void delete(Ticket entity) {
        ticketRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Long> ids) {
        ticketRepository.deleteAllById(ids);
    }

    public void deleteAll(Iterable<? extends Ticket> entities) {
        ticketRepository.deleteAll(entities);
    }

    public void deleteAll() {
        ticketRepository.deleteAll();
    }

    public List<Ticket> findAll(Sort sort) {
        return ticketRepository.findAll(sort);
    }

    public Page<Ticket> findAll(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }

    public <S extends Ticket> Optional<S> findOne(Example<S> example) {
        return ticketRepository.findOne(example);
    }

    public <S extends Ticket> Page<S> findAll(Example<S> example, Pageable pageable) {
        return ticketRepository.findAll(example, pageable);
    }

    public <S extends Ticket> long count(Example<S> example) {
        return ticketRepository.count(example);
    }

    public <S extends Ticket> boolean exists(Example<S> example) {
        return ticketRepository.exists(example);
    }

    public <S extends Ticket, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        return ticketRepository.findBy(example, queryFunction);
    }

}
