package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.mapper;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.model.Venue;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.dto.request.VenueRequest;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.dto.response.VenueResponse;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface VenueMapper {

    // ✅ Request -> Domain (para crear)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "events", ignore = true) // Lista de eventos no se mapea desde request
    Venue toDomain(VenueRequest request);

    // ✅ Domain -> Response (para retornar al cliente)
    VenueResponse toResponse(Venue venue);

    // ✅ Request -> Domain existente (para actualizar)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "events", ignore = true)
    void updateDomainFromRequest(VenueRequest request, @MappingTarget Venue venue);
}