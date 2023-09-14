package com.museu.museu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.museu.museu.domain.Usuario;
import com.museu.museu.dto.DadosLogin;
import com.museu.museu.dto.Token;
import com.museu.museu.services.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<Token> login(@Valid @RequestBody DadosLogin dados) {


        var authToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = authManager.authenticate(authToken);
        var token = tokenService.getToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new Token(token));
    }

}
