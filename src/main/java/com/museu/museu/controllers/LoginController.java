package com.museu.museu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.museu.museu.domain.Usuario;
import com.museu.museu.dto.DadosLogin;
import com.museu.museu.services.TokenService;

import jakarta.validation.Valid;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid DadosLogin dados) {

        var authenticationPerson = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha(), null);
        var authentication = manager.authenticate(authenticationPerson);
        var token = tokenService.getToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(token);
    }

}
