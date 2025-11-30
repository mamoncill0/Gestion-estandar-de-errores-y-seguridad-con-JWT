package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.dto.response;

import lombok.Data;

@Data
public class VenueResponse {

    private Integer id;
    private String name;
    private String city;
    private String address;
    private String capacity;
}
