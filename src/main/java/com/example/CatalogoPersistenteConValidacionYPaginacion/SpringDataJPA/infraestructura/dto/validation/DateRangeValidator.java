package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.dto.validation;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.dto.request.EventRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<DateRange, EventRequest> {

    @Override
    public void initialize(DateRange constraintAnnotation) {
    }

    @Override
    public boolean isValid(EventRequest eventRequest, ConstraintValidatorContext context) {
        if (eventRequest.getStartTime() == null || eventRequest.getEndTime() == null) {
            return true; // No se puede validar si una de las fechas es nula
        }
        return eventRequest.getStartTime().before(eventRequest.getEndTime());
    }
}
