package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.application.service;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.port.in.IEventService;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.model.Event;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.model.Venue;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.port.out.EventRepositoryPort;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.port.out.VenueRepositoryPort;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.exception.ResourceNotFoundException;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.dto.request.EventRequest;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.dto.response.EventResponse;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.mapper.EventMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EventService implements IEventService {

    private final EventRepositoryPort eventRepositoryPort;
    private final VenueRepositoryPort venueRepositoryPort;
    private final EventMapper eventMapper; // ✅ Inyectas el mapper

    public EventService(EventRepositoryPort eventRepositoryPort,
                        VenueRepositoryPort venueRepositoryPort,
                        EventMapper eventMapper) {
        this.eventRepositoryPort = eventRepositoryPort;
        this.venueRepositoryPort = venueRepositoryPort;
        this.eventMapper = eventMapper;
    }

    @Override
    @Transactional
    public EventResponse create(EventRequest request) {
        // Obtener venue
        Venue venue = venueRepositoryPort.findById(request.getVenueId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Venue no encontrado con el id: " + request.getVenueId()));

        // Mapear request a dominio
        Event event = eventMapper.toDomain(request);
        event.setVenue(venue); // Asignar venue manualmente

        // Guardar
        Event savedEvent = eventRepositoryPort.save(event);

        // ✅ USA EL MAPPER
        return eventMapper.toResponse(savedEvent);
    }

    @Override
    @Transactional
    public EventResponse update(Integer id, EventRequest request) {
        // Obtener evento existente
        Event existingEvent = eventRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Evento no encontrado con el id: " + id));

        // Obtener venue
        Venue venue = venueRepositoryPort.findById(request.getVenueId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Venue no encontrado con el id: " + request.getVenueId()));

        // ✅ USA EL MAPPER para actualizar
        eventMapper.updateDomainFromRequest(request, existingEvent);
        existingEvent.setVenue(venue);

        // Guardar
        Event updatedEvent = eventRepositoryPort.save(existingEvent);

        return eventMapper.toResponse(updatedEvent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!eventRepositoryPort.existsById(id)) {
            throw new ResourceNotFoundException("Evento no encontrado con el id: " + id);
        }
        eventRepositoryPort.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public EventResponse getById(Integer id) {
        return eventRepositoryPort.findById(id)
                .map(eventMapper::toResponse) // ✅ USA EL MAPPER
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Evento no encontrado con el id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EventResponse> getAll(String city, String category,
                                      LocalDateTime startDate, Pageable pageable) {
        // Implementar filtrado usando los parámetros
        return eventRepositoryPort.findAll(pageable)
                .map(eventMapper::toResponse);
    }

}