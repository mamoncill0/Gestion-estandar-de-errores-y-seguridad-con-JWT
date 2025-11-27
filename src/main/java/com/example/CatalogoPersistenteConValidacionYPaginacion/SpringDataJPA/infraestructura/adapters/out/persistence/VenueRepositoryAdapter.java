package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.model.Venue;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.port.out.VenueRepositoryPort;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence.entity.VenueJpaEntity;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence.mapper.VenuePersistenceMapper;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence.repository.VenueJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VenueRepositoryAdapter implements VenueRepositoryPort {

    private final VenueJpaRepository venueJpaRepository;
    private final VenuePersistenceMapper venueMapper;

    public VenueRepositoryAdapter(VenueJpaRepository venueJpaRepository, VenuePersistenceMapper venueMapper) {
        this.venueJpaRepository = venueJpaRepository;
        this.venueMapper = venueMapper;
    }

    @Override
    public Venue save(Venue venue) {
        VenueJpaEntity venueJpaEntity = venueMapper.toJpaEntity(venue);
        VenueJpaEntity savedEntity = venueJpaRepository.save(venueJpaEntity);
        return venueMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Venue> findById(Integer id) {
        return venueJpaRepository.findById(id).map(venueMapper::toDomain);
    }

    @Override
    public void deleteById(Integer id) {
        venueJpaRepository.deleteById(id);
    }

    @Override
    public Page<Venue> findAll(Pageable pageable) {
        return venueJpaRepository.findAll(pageable).map(venueMapper::toDomain);
    }

    @Override
    public boolean existsByName(String name) {
        return venueJpaRepository.existsByName(name);
    }
}
