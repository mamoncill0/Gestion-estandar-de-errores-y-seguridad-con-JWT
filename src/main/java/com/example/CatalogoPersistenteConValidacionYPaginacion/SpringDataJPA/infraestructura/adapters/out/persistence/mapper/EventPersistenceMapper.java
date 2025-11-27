package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence.mapper;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.model.Event;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence.entity.EventJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class EventPersistenceMapper {

    private final VenuePersistenceMapper venueMapper;

    public EventPersistenceMapper(VenuePersistenceMapper venueMapper) {
        this.venueMapper = venueMapper;
    }

    public Event toDomain(EventJpaEntity jpaEntity) {
        if (jpaEntity == null) {
            return null;
        }
        Event event = new Event();
        event.setId(jpaEntity.getId());
        event.setNameEvent(jpaEntity.getNameEvent());
        event.setStartTime(jpaEntity.getStartTime());
        event.setEndTime(jpaEntity.getEndTime());
        event.setDescription(jpaEntity.getDescription());
        event.setCapacity(jpaEntity.getCapacity());
        if (jpaEntity.getVenue() != null) {
            event.setVenue(venueMapper.toDomain(jpaEntity.getVenue()));
        }
        return event;
    }

    public EventJpaEntity toJpaEntity(Event event) {
        if (event == null) {
            return null;
        }
        EventJpaEntity jpaEntity = new EventJpaEntity();
        jpaEntity.setId(event.getId());
        jpaEntity.setNameEvent(event.getNameEvent());
        jpaEntity.setStartTime(event.getStartTime());
        jpaEntity.setEndTime(event.getEndTime());
        jpaEntity.setDescription(event.getDescription());
        jpaEntity.setCapacity(event.getCapacity());
        if (event.getVenue() != null) {
            jpaEntity.setVenue(venueMapper.toJpaEntity(event.getVenue()));
        }
        return jpaEntity;
    }
}
