package com.echenique.equipos.exception;

public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(){
        super("La solicitud es invalida");
    }
}
