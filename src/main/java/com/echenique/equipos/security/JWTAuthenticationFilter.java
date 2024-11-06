package com.echenique.equipos.security;

import com.echenique.equipos.exception.DefaultExceptionDescription;
import com.echenique.equipos.exception.InvalidAuthenticationException;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.security.InvalidParameterException;
import java.security.interfaces.RSAPublicKey;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_TYPE = "Bearer ";
    private final RSAPublicKey rsaPublicKey;
    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) {
        try {

            final String authorization = request.getHeader(AUTHORIZATION_HEADER);
            if (!isBearerToken(authorization)) {
                filterChain.doFilter(request, response);
                return;
            }

            var authenticationToken = getAuthentication(authorization);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        } catch (InvalidParameterException e) {
            throw new InvalidAuthenticationException(DefaultExceptionDescription.builder()
                    .action("AUTHENTICATING BY JWT")
                    .detail(e.getMessage())
                    .build());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private Authentication getAuthentication(String authorization) {
        var authDecoded = new AuthDecoded(authorization, rsaPublicKey);
        var userPrincipal = authDecoded.getUserPrincipal();
        if (userPrincipal.getName().isEmpty()) {
            throw new InvalidParameterException("username is required");
        }
        return new UsernamePasswordAuthenticationToken(userPrincipal, null, null);
    }

    private boolean isBearerToken(final String authorization) {
        return (null != authorization) && authorization.startsWith(AUTHORIZATION_TYPE);
    }
}
