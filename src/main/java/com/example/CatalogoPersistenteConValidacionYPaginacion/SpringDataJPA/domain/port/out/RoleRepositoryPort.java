package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.port.out;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.model.Role;

import java.util.Optional;

// Puerto de salida para la persistencia de Roles.
public interface RoleRepositoryPort {
    Optional<Role> findByName(String name);
    Role save(Role role);
}
