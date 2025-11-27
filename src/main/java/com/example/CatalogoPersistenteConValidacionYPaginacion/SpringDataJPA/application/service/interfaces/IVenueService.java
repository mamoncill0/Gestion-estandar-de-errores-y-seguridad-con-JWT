package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.application.service.interfaces;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.dto.request.VenueRequest;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.dto.response.VenueResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IVenueService {

    VenueResponse create(VenueRequest request);

    Page<VenueResponse> findAll(Pageable pageable);

    VenueResponse findById(Integer id);

    VenueResponse update(Integer id, VenueRequest request);

    void delete(Integer id);
}
