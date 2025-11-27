package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence.mapper;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.model.User;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence.entity.UserJpaEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserPersistenceMapper {

    private final RolePersistenceMapper roleMapper;

    public UserPersistenceMapper(RolePersistenceMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public User toDomain(UserJpaEntity jpaEntity) {
        if (jpaEntity == null) {
            return null;
        }
        User user = new User();
        user.setId(jpaEntity.getId());
        user.setUsername(jpaEntity.getUsername());
        user.setPassword(jpaEntity.getPassword());
        if (jpaEntity.getRoles() != null) {
            user.setRoles(jpaEntity.getRoles().stream().map(roleMapper::toDomain).collect(Collectors.toSet()));
        }
        return user;
    }

    public UserJpaEntity toJpaEntity(User user) {
        if (user == null) {
            return null;
        }
        UserJpaEntity jpaEntity = new UserJpaEntity();
        jpaEntity.setId(user.getId());
        jpaEntity.setUsername(user.getUsername());
        jpaEntity.setPassword(user.getPassword());
        // Propositalmente no mapeamos los roles aquí, se manejarán en el adaptador.
        return jpaEntity;
    }
}
