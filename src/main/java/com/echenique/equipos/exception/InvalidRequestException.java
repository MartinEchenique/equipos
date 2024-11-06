package com.echenique.equipos.exception;

import lombok.Getter;

@Getter
public class InvalidRequestException extends RuntimeException{
    private final transient DefaultExceptionDescription defaultExceptionDescription;

    public InvalidRequestException(DefaultExceptionDescription description){
        super(description.getDetail());
        this.defaultExceptionDescription = description;

    }
}
