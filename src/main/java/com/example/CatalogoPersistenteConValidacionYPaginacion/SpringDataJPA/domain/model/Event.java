package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.model;

import java.util.Date;

// Objeto de dominio puro para Event.
public class Event {

    private Integer id;
    private String nameEvent;
    private Date startTime;
    private Date endTime;
    private String description;
    private int capacity;
    private Venue venue; // Relación con el objeto de dominio Venue

    // Constructor, Getters y Setters...

    public Event() {
    }

    public Event(Integer id, String nameEvent, Date startTime, Date endTime, String description, int capacity, Venue venue) {
        this.id = id;
        this.nameEvent = nameEvent;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.capacity = capacity;
        this.venue = venue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
