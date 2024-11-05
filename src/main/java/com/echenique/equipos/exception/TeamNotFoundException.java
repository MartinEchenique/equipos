package com.echenique.equipos.exception;

public class TeamNotFoundException extends RuntimeException{
    public TeamNotFoundException(){
        super("Equipo no encontrado");
    }
}
