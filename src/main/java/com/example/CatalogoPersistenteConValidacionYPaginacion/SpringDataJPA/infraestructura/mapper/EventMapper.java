package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.mapper;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.model.Event;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.dto.request.EventRequest;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.dto.response.EventResponse;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface EventMapper {

    @Mapping(source = "venueId", target = "venue.id")
    Event toDomain(EventRequest request);

    // ✅ Domain -> Response (para retornar al cliente)
    @Mapping(source = "venue.id", target = "venueId")
    EventResponse toResponse(Event event);

    // ✅ Request -> Domain existente (para actualizar)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "venue", ignore = true) // Se asigna manualmente en el servicio
    void updateDomainFromRequest(EventRequest request, @MappingTarget Event event);
}
