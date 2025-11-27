package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.model.Event;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.port.out.EventRepositoryPort;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence.mapper.EventPersistenceMapper;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence.repository.EventJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EventRepositoryAdapter implements EventRepositoryPort {

    private final EventJpaRepository eventJpaRepository;
    private final EventPersistenceMapper eventMapper;

    public EventRepositoryAdapter(EventJpaRepository eventJpaRepository, EventPersistenceMapper eventMapper) {
        this.eventJpaRepository = eventJpaRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public Event save(Event event) {
        var eventJpaEntity = eventMapper.toJpaEntity(event);
        var savedEntity = eventJpaRepository.save(eventJpaEntity);
        return eventMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Event> findById(Integer id) {
        return eventJpaRepository.findById(id).map(eventMapper::toDomain);
    }

    @Override
    public void deleteById(Integer id) {
        eventJpaRepository.deleteById(id);
    }

    @Override
    public Page<Event> findAll(Pageable pageable) {
        return eventJpaRepository.findAll(pageable).map(eventMapper::toDomain); // CORRECCIÓN APLICADA AQUÍ
    }

    @Override
    public boolean existsById(Integer id) {
        return eventJpaRepository.existsById(id);
    }
}
