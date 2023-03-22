package com.jvictorc.volMed.infra.exception;

import org.springframework.validation.FieldError;

public record ErrorViewValidationError(
        String campo,
        String message
) {


    public ErrorViewValidationError(FieldError e) {
        this(e.getField(), e.getDefaultMessage());
    }


}
