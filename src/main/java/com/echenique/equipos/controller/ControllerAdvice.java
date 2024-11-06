package com.echenique.equipos.controller;

import com.echenique.equipos.dto.DefaultExceptionResponse;
import com.echenique.equipos.exception.InvalidAuthenticationException;
import com.echenique.equipos.exception.InvalidRequestException;
import com.echenique.equipos.exception.TeamNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = TeamNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public DefaultExceptionResponse teamNotFoundException(TeamNotFoundException ex) {
        return DefaultExceptionResponse
                .builder()
                .message(ex.getMessage())
                .code(HttpStatus.NOT_FOUND.value())
                .build();
    }
    @ExceptionHandler(value = InvalidRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DefaultExceptionResponse invalidRequestException(InvalidRequestException ex) {
        return DefaultExceptionResponse
                .builder()
                .message(ex.getMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }
    @ExceptionHandler(value = InvalidAuthenticationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public DefaultExceptionResponse invalidAuthenticationException(InvalidRequestException ex) {
        return DefaultExceptionResponse
                .builder()
                .message(ex.getMessage())
                .code(HttpStatus.UNAUTHORIZED.value())
                .build();
    }
}
