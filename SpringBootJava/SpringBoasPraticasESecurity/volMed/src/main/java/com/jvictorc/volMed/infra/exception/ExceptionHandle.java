package com.jvictorc.volMed.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class ExceptionHandle {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<List<ErrorViewValidationError>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        var erros = e.getFieldErrors();


        return ResponseEntity.badRequest().body(erros.stream().map(ErrorViewValidationError::new).toList());
    }


    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ErrorView handleNotFOundInDataBase(
            EntityNotFoundException e
    ) {

        return new ErrorView(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    @ResponseBody()
    public ErrorView conflictHandle(HttpServletRequest req, DataIntegrityViolationException e) {
        final boolean isEmailInvalid = e.getMessage().contains("medicos_email_key");
        final boolean isCrmInvalid = e.getMessage().contains("medicos_crm_key");

        if (isCrmInvalid) {
            return new ErrorView("CRM Já em uso", HttpStatus.CONFLICT.value());
        }

        if (isEmailInvalid) {
            return new ErrorView("Email Já em uso",  HttpStatus.CONFLICT.value());
        }


        return new ErrorView(e.getMessage(), 409);
    }
}
