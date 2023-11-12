package com.museu.museu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.museu.museu.domain.Divisao;
import com.museu.museu.dto.DadosDivisao;
import com.museu.museu.repositories.DivisaoRepository;

import jakarta.validation.Valid;

@RequestMapping("/divisao")
@RestController
public class DivisaoController {
    

    @Autowired
    private DivisaoRepository divisaoRepository;


    @PostMapping("/nova")
    @Transactional
    public ResponseEntity<DadosDivisao> criarDivisao(@Valid @RequestBody DadosDivisao divisao) {
        var novaDivisao = divisaoRepository.save(new Divisao(divisao));

        return ResponseEntity.ok(new DadosDivisao(novaDivisao));
        }
        
         @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<String> deleteDivisao(@PathVariable Long id) {
        Optional<Divisao> optionalDivisao = DivisaoRepository.findById(id);

        if (optionalDivisao.isPresent()) {
    
            DivisaoRepository.delete(optionalDivisao.get());
            return ResponseEntity.ok("Categoria de Divisao deletada.");
        } else {
           
            return ResponseEntity.notFound().build();
    }

