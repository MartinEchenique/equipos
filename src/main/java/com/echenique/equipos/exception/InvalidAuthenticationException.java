package com.echenique.equipos.exception;

import lombok.Getter;

@Getter
public class InvalidAuthenticationException  extends RuntimeException{
    private final transient DefaultExceptionDescription defaultExceptionDescription;
    public InvalidAuthenticationException(DefaultExceptionDescription description){
        super(description.getDetail());
        this.defaultExceptionDescription = description;
    }

}
