package com.museu.museu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.museu.museu.domain.ViagensPesquisa;
import com.museu.museu.dto.CadastroViagensPesquisa;
import com.museu.museu.dto.DadosViagensPesquisa;
import com.museu.museu.repositories.FuncionarioRepository;
import com.museu.museu.repositories.ViagensPesquisaRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RequestMapping("/viagenspesquisa")
@RestController
public class ViagensPesquisaController {

    @Autowired
    private ViagensPesquisaRepository viagensPesquisaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @PostMapping("/cadastrar/{id}")
    @Transactional
    public ResponseEntity<DadosViagensPesquisa> novaViagemPesquisa(@Valid @RequestBody CadastroViagensPesquisa viagem,
            @PathVariable Integer id) {

        if (funcionarioRepository.existsById(id)) {
            var f = funcionarioRepository.findById(id);
            
            var viagens = new ViagensPesquisa(viagem);
            viagens.setPesquisador(f.get());
            viagensPesquisaRepository.save(viagens);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/aprovar/{id}")
    @Transactional
    public ResponseEntity<DadosViagensPesquisa> aprovarViagemPesquisa(@PathVariable Integer id, HttpServletRequest request) {

        if (viagensPesquisaRepository.existsById(id)) {
            var viagem = viagensPesquisaRepository.findById(id);
            viagem.get().setAprovada(true);
            viagensPesquisaRepository.save(viagem.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
