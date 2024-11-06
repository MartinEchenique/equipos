package com.echenique.equipos.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPublicKey;
import java.util.Map;

import static java.util.Objects.isNull;
@RequiredArgsConstructor
@Component
public class AuthDecoded {
    public static final String USERNAME_CLAIM_KEY = "USERNAME";
    private DecodedJWT decodedJWT;
    public AuthDecoded(String authorizationHeader, RSAPublicKey rsaPublicKey) {
        var token = authorizationHeader.substring(7);
        this.decodedJWT = this.decodedJwt(token, rsaPublicKey);
    }
    private DecodedJWT decodedJwt(String token, RSAPublicKey rsaPublicKey) {
        var algorithm = Algorithm.RSA512(rsaPublicKey, null);
        JWTVerifier verifier = JWT.require(algorithm).build();
        decodedJWT = verifier.verify(token);

        return decodedJWT;
    }
    public String getUsername() {
        Map<String, Claim> claims = this.decodedJWT.getClaims();
        var claimUser = claims.get(USERNAME_CLAIM_KEY);
        if (isNull(claimUser)) {
            return Strings.EMPTY;
        }

        return claimUser.asString();
    }
    public UserPrincipal getUserPrincipal() {
        return new UserPrincipal(getUsername());
    }

}