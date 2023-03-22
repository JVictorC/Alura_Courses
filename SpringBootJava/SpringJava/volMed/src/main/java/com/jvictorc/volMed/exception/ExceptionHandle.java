package com.jvictorc.volMed.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandle {
    @ResponseStatus(value = HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    @ResponseBody()
    public ErrorView conflictHandle(HttpServletRequest req, DataIntegrityViolationException e) {
        final boolean isEmailInvalid = e.getMessage().contains("medicos_email_key");
        final boolean isCrmInvalid = e.getMessage().contains("medicos_crm_key");

        if (isCrmInvalid) {
            return new ErrorView("CRM Já em uso", 409);
        }

        if (isEmailInvalid) {
            return new ErrorView("Email Já em uso", 409);
        }


        return new ErrorView(e.getMessage(), 409);
    }
}
