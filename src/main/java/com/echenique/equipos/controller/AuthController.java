package com.echenique.equipos.controller;

import com.echenique.equipos.dto.AuthenticationDto;
import com.echenique.equipos.dto.TokenDto;
import com.echenique.equipos.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @GetMapping(value = "/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthenticationDto authenticationDto) {
        try {
            String token = authService.login(authenticationDto);
            return ResponseEntity.ok(TokenDto.builder().token(token).build());
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}
    }
}
