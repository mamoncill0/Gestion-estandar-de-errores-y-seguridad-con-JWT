package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.model;

// Objeto de dominio puro para Role.
public class Role {

    private Long id;
    private String name;

    // Constructor, Getters y Setters...

    public Role() {
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
