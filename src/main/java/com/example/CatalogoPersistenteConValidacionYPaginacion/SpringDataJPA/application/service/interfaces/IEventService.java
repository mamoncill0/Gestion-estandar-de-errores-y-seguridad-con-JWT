package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.application.service.interfaces;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.dto.request.EventRequest;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.dto.response.EventResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface IEventService {

    EventResponse create(EventRequest request);

    EventResponse update(Integer id, EventRequest request);

    void delete(Integer id);

    EventResponse getById(Integer id);

    Page<EventResponse> getAll(String city, String category, LocalDateTime startDate, Pageable pageable);
}
