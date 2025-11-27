package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class EventResponse {

    private Integer id;
    private String nameEvent;
    private Date startTime;
    private Date endTime;
    private String description;
    private int capacity;
    private Integer venueId;
}
