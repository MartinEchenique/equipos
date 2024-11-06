package com.echenique.equipos.exception;

public class InvalidAuthenticationException  extends RuntimeException{
    public InvalidAuthenticationException(String message){
        super(message);
    }

}
