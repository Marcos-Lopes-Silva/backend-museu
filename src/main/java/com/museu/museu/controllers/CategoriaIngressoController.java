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

import com.museu.museu.domain.CategoriaIngresso;
import com.museu.museu.dto.DadosCategoriaIngresso;
import com.museu.museu.repositories.CategoriaIngressoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaIngressoController {

    @Autowired
    private CategoriaIngressoRepository categoriaIngressoRepository;

    @PostMapping("/nova")
    @Transactional
    public ResponseEntity<DadosCategoriaIngresso> criarCategoriaIngresso(@Valid @RequestBody DadosCategoriaIngresso dadosCategoriaIngresso) {
        CategoriaIngresso novaCategoriaIngresso = categoriaIngressoRepository.save(new CategoriaIngresso(dadosCategoriaIngresso));

        return ResponseEntity.ok(new DadosCategoriaIngresso(novaCategoriaIngresso));
      
}
 @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<String> deleteCategoriaIngresso(@PathVariable Long id) {
    
        Optional<CategoriaIngresso> optionalCategoriaIngresso = categoriaIngressoRepository.findById(id);

        if (optionalCategoriaIngresso.isPresent()) {
            categoriaIngressoRepository.delete(optionalCategoriaIngresso.get());
            return ResponseEntity.ok("Categoria de Ingresso deletada.");
        } else {
            
            return ResponseEntity.notFound().build();
}