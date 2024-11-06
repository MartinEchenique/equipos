package com.echenique.equipos.exception;

import lombok.Getter;

@Getter
public class TeamNotFoundException extends RuntimeException{
    private final transient DefaultExceptionDescription defaultExceptionDescription;

    public TeamNotFoundException(DefaultExceptionDescription description){
        super(description.getDetail());
        this.defaultExceptionDescription = description;

    }
}
