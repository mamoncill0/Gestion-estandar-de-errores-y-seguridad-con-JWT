package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence.mapper;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.model.Role;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence.entity.RoleJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class RolePersistenceMapper {

    public Role toDomain(RoleJpaEntity jpaEntity) {
        if (jpaEntity == null) {
            return null;
        }
        Role role = new Role();
        role.setId(jpaEntity.getId());
        role.setName(jpaEntity.getName());
        return role;
    }

    public RoleJpaEntity toJpaEntity(Role role) {
        if (role == null) {
            return null;
        }
        RoleJpaEntity jpaEntity = new RoleJpaEntity();
        jpaEntity.setId(role.getId());
        jpaEntity.setName(role.getName());
        return jpaEntity;
    }
}
