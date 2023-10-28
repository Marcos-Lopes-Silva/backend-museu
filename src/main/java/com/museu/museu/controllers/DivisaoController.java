package com.museu.museu.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.museu.museu.domain.CategoriaIngresso;
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

         @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<String> deleteCategoriaIngresso(@PathVariable Long id) {
        Optional<CategoriaIngresso> optionalCategoriaIngresso = categoriaIngressoRepository.findById(id);

        if (optionalCategoriaIngresso.isPresent()) {
    
            categoriaIngressoRepository.delete(optionalCategoriaIngresso.get());
            return ResponseEntity.ok("Categoria de ingresso deletada.");
        } else {
           
            return ResponseEntity.notFound().build();
        }
    }
    }
    
}
