package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Esta excepción se lanza cuando se busca un recurso en la base de datos
 * y no se encuentra. Al anotarla con @ResponseStatus(HttpStatus.NOT_FOUND),
 * Spring automáticamente devolverá un error 404 Not Found si la excepción
 * no es capturada por un @ControllerAdvice.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}