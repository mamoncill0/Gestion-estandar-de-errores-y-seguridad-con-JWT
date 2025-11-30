package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.application.service;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.port.in.IVenueService;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.model.Venue;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.port.out.VenueRepositoryPort;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.exception.DuplicateResourceException;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.exception.ResourceNotFoundException;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.dto.request.VenueRequest;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.dto.response.VenueResponse;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.mapper.VenueMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VenueService implements IVenueService {

    private final VenueRepositoryPort venueRepositoryPort;
    private final VenueMapper venueMapper;

    public VenueService(VenueRepositoryPort venueRepositoryPort,  VenueMapper venueMapper) {
        this.venueRepositoryPort = venueRepositoryPort;
        this.venueMapper = venueMapper;
    }

    @Override
    @Transactional
    public VenueResponse create(VenueRequest request) {
        if (venueRepositoryPort.existsByName(request.getName())) {
            throw new DuplicateResourceException("Ya existe un venue con el nombre: " + request.getName());
        }
        // Usamos el mapper, para usar el mapper debemos llamar a la variable que declaramos
        //En este caso se llama venueMapper (venueMapper.toDomain) es el metodo que declaramos en la interfaz
        Venue venue = venueMapper.toDomain(request);
        Venue savedVenue = venueRepositoryPort.save(venue);
        return venueMapper.toResponse(savedVenue);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VenueResponse> findAll(Pageable pageable) {
        return venueRepositoryPort.findAll(pageable).map(venueMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public VenueResponse findById(Integer id) {
        return venueRepositoryPort.findById(id)
                .map(venueMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Venue no encontrado con el id: " + id));
    }

    @Override
    @Transactional
    public VenueResponse update(Integer id, VenueRequest request) {
        Venue existingVenue = venueRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venue no encontrado con el id: " + id));

        if (!existingVenue.getName().equals(request.getName()) && venueRepositoryPort.existsByName(request.getName())) {
            throw new DuplicateResourceException("Ya existe un venue con el nombre: " + request.getName());
        }

        venueMapper.updateDomainFromRequest(request, existingVenue);
        Venue updatedVenue = venueRepositoryPort.save(existingVenue);
        return venueMapper.toResponse(updatedVenue);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!venueRepositoryPort.findById(id).isPresent()) {
            throw new ResourceNotFoundException("Venue no encontrado con el id: " + id);
        }
        venueRepositoryPort.deleteById(id);
    }
}
