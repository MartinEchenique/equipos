package com.echenique.equipos.configuration;

import com.echenique.equipos.security.CustomUserDetailsService;
import com.echenique.equipos.security.JWTAuthenticationFilter;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@EnableWebSecurity
@Configuration
@Getter
public class SecurityConfiguration {
    @Value("${jwt.public.key}")
    private String jwtPublicKey;
    @Value("${jwt.private.key}")
    private String jwtPrivateKey;
    @Value("${jwt.expiration.time:300000}")
    public Long expirationTime;
    @Bean
    @Primary
    public SecurityFilterChain filterChain(HttpSecurity http, RSAPublicKey rsaPublicKey) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)// NOSONAR: We are sure that disabling CSRF protection is safe in this context given that we use jwt authentication.
                .authorizeHttpRequests(authorizeRequest -> authorizeRequest
                        .requestMatchers("/equipos/**").authenticated()
                        .anyRequest().permitAll())
                .addFilterBefore(new JWTAuthenticationFilter(rsaPublicKey), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(
            CustomUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public RSAPublicKey publicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] publicKeyBytes = Base64.getDecoder().decode(jwtPublicKey);
        var keySpec = new X509EncodedKeySpec(publicKeyBytes);
        var keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }
    @Bean
    public RSAPrivateKey privateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] publicKeyBytes = Base64.getDecoder().decode(jwtPrivateKey);
        var keySpec = new PKCS8EncodedKeySpec(publicKeyBytes);
        var keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }

}
