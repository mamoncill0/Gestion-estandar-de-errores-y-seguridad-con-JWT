package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.port.out;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.model.User;

import java.util.Optional;

// Puerto de salida para la persistencia de Users.
public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
