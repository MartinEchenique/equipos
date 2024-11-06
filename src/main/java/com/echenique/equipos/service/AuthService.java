package com.echenique.equipos.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.echenique.equipos.configuration.SecurityConfiguration;
import com.echenique.equipos.request.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final RSAPublicKey publicKey;
    private final RSAPrivateKey privateKey;
    private final SecurityConfiguration securityConfiguration;

    public String login(AuthenticationRequest authenticationDto){
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(authenticationDto.getUsername(), authenticationDto.getPassword());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        return getToken(authenticationResponse.getName());
    }
    private String getToken(String userName){
        HashMap<String, Object> headerClaim = new HashMap<>();
        headerClaim.put("alg", "RSA");
        headerClaim.put("typ", "JWT");

        return JWT.create()
                .withHeader(headerClaim)
                .withExpiresAt(new Date(System.currentTimeMillis() + securityConfiguration.expirationTime))
                .withClaim("USERNAME", userName)
                .sign(Algorithm.RSA512( publicKey, privateKey));
    }
}
