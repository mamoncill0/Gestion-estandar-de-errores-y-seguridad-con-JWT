package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence.repository;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence.entity.RoleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleJpaEntity, Long> {
    Optional<RoleJpaEntity> findByName(String name);
}
