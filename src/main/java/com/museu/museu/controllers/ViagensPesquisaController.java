package com.museu.museu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.museu.museu.dto.CadastroViagensPesquisa;
import com.museu.museu.dto.DadosViagensPesquisa;
import com.museu.museu.repositories.ViagensPesquisaRepository;

import jakarta.validation.Valid;


@RequestMapping("/viagenspesquisa")
@RestController
public class ViagensPesquisaController {
    

    @Autowired
    private ViagensPesquisaRepository viagensPesquisaRepository;


    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DadosViagensPesquisa> novaViagemPesquisa(@Valid @RequestBody CadastroViagensPesquisa viagem){

        return null;
    }
}
