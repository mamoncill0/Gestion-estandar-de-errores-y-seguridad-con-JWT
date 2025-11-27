package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.port.out;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.model.Venue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

// Este es un puerto de salida. Define el contrato que la capa de dominio necesita
// para interactuar con el exterior (en este caso, para la persistencia de Venues).
// No contiene ninguna tecnología específica.
public interface VenueRepositoryPort {

    Venue save(Venue venue);

    Optional<Venue> findById(Integer id);

    void deleteById(Integer id);

    Page<Venue> findAll(Pageable pageable);

    boolean existsByName(String name);
}
