package com.echenique.equipos.security;

import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.security.Principal;
@AllArgsConstructor
public class UserPrincipal implements Principal, Serializable {
    private String name;
    @Override
    public String getName() {
        return this.name;
    }
}
