package com.echenique.equipos.controller;

import com.echenique.equipos.response.DefaultExceptionResponse;
import com.echenique.equipos.exception.InvalidAuthenticationException;
import com.echenique.equipos.exception.InvalidRequestException;
import com.echenique.equipos.exception.TeamNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAdvice.class);
    @ExceptionHandler(value = TeamNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public DefaultExceptionResponse teamNotFoundException(TeamNotFoundException ex) {
        LOGGER.error(":::: TEAM NOT FOUND {}::::", ex.getDefaultExceptionDescription());
        return DefaultExceptionResponse
                .builder()
                .message("Equipo no encontrado")
                .code(HttpStatus.NOT_FOUND.value())
                .build();
    }
    @ExceptionHandler(value = InvalidRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DefaultExceptionResponse invalidRequestException(InvalidRequestException ex) {
        LOGGER.error(":::: INVALID REQUEST {}::::", ex.getDefaultExceptionDescription());
        return DefaultExceptionResponse
                .builder()
                .message("La solicitud es invalida")
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }
    @ExceptionHandler(value =  MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DefaultExceptionResponse argumentNotValid(MethodArgumentNotValidException ex) {
        LOGGER.error(":::: INVALID REQUEST {}::::", ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
        return DefaultExceptionResponse
                .builder()
                .message("La solicitud es invalida")
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }
    @ExceptionHandler(value = InvalidAuthenticationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public DefaultExceptionResponse invalidAuthenticationException(InvalidRequestException ex) {
        LOGGER.error(":::: INVALID AUTHENTICATION {}::::", ex.getDefaultExceptionDescription());
        return DefaultExceptionResponse
                .builder()
                .message("La solicitud es invalida")
                .code(HttpStatus.UNAUTHORIZED.value())
                .build();
    }
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public DefaultExceptionResponse unexpectedException(InvalidRequestException ex) {
        LOGGER.error(":::: UNEXPECTED EXCEPTION OCCURRED {}::::", ex.getMessage());
        return DefaultExceptionResponse
                .builder()
                .message("Ocurrio un error al procesar la solicitud.")
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
    }

}
