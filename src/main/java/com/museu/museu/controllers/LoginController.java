package com.museu.museu.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.museu.museu.dto.DadosLogin;

import jakarta.validation.Valid;

@RestController
public class LoginController {


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid DadosLogin dados) {
        
        return null;
    }

}
