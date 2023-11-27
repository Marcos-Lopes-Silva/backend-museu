package com.museu.museu.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.museu.museu.domain.Cache;
import com.museu.museu.domain.CategoriaIngresso;
import com.museu.museu.dto.DadosCategoriaIngresso;
import com.museu.museu.repositories.CategoriaIngressoRepository;

import jakarta.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaIngressoController {
    private static final String PEGAR_CATEGORIA = "categoria";
    private Cache cache = Cache.getInstance();

    private final CategoriaIngressoRepository categoriaIngressoRepository;

    @Autowired
    public CategoriaIngressoController(CategoriaIngressoRepository categoriaIngressoRepository) {
        this.categoriaIngressoRepository = categoriaIngressoRepository;
    }

    @PostMapping("/nova")
    @Transactional
    public ResponseEntity<DadosCategoriaIngresso> criarCategoriaIngresso(
            @Valid @RequestBody DadosCategoriaIngresso dadosCategoriaIngresso) {
        CategoriaIngresso novaCategoriaIngresso = categoriaIngressoRepository
                .save(new CategoriaIngresso(dadosCategoriaIngresso));

        return ResponseEntity.ok(new DadosCategoriaIngresso(novaCategoriaIngresso));

    }

    @GetMapping
    public ResponseEntity<Page<DadosCategoriaIngresso>> getCategoriaIngresso(
            @PageableDefault(size = 10, sort = "id") Pageable paginacao) {

        Page<CategoriaIngresso> lista = categoriaIngressoRepository.findAll(paginacao);

        var dados = lista.getContent();

        List<DadosCategoriaIngresso> dadosList = new ArrayList<>();

        for (CategoriaIngresso dci : dados) {
            dadosList.add(new DadosCategoriaIngresso(dci));
        }

        Page<DadosCategoriaIngresso> dadosPage = new PageImpl<>(dadosList, paginacao, 0);

        return ResponseEntity.ok(dadosPage);
    }

    @GetMapping("{id}")
    public ResponseEntity<DadosCategoriaIngresso> getCategoriaIngressoById(@PathVariable Integer id) {
        if(cache.get(PEGAR_CATEGORIA + id) != null){
            CategoriaIngresso categoriaCached = (CategoriaIngresso) cache.get(PEGAR_CATEGORIA + id);
            return ResponseEntity.ok(new DadosCategoriaIngresso(categoriaCached));
        } else {
             Optional<CategoriaIngresso> optionalCategoriaIngresso = categoriaIngressoRepository.findById(id);
        if (optionalCategoriaIngresso.isPresent()) {
            CategoriaIngresso categoriaIngresso = optionalCategoriaIngresso.get();
            cache.put(PEGAR_CATEGORIA + categoriaIngresso.getId(), categoriaIngresso);
            return ResponseEntity.ok(new DadosCategoriaIngresso(categoriaIngresso));
        } else {
            return ResponseEntity.notFound().build();
        }
        }
    }

    @PostMapping("{id}")
    @Transactional
    public ResponseEntity<DadosCategoriaIngresso> updatePrecoIngresso(@PathVariable Integer id,
            @RequestBody DadosCategoriaIngresso dadosCategoriaIngresso) {
        if (cache.get(PEGAR_CATEGORIA + id) != null) {
            CategoriaIngresso categoriaCached = (CategoriaIngresso) cache.get(PEGAR_CATEGORIA + id);
            categoriaCached.setPreco(dadosCategoriaIngresso.preco());
            categoriaIngressoRepository.save(categoriaCached);
            return ResponseEntity.ok(new DadosCategoriaIngresso(categoriaCached));
        } else {
            Optional<CategoriaIngresso> optionalCategoriaIngresso = categoriaIngressoRepository.findById(id);
            if (optionalCategoriaIngresso.isPresent()) {
                CategoriaIngresso categoriaIngresso = optionalCategoriaIngresso.get();
                cache.put(PEGAR_CATEGORIA + categoriaIngresso.getId(), dadosCategoriaIngresso);
                categoriaIngresso.setPreco(dadosCategoriaIngresso.preco());
                categoriaIngressoRepository.save(categoriaIngresso);
                return ResponseEntity.ok(new DadosCategoriaIngresso(categoriaIngresso));
            } else {
                return ResponseEntity.notFound().build();
            }
        }

    }

    @DeleteMapping
    public ResponseEntity<String> deleteCategoriaIngresso(@PathVariable Integer id) {
        Optional<CategoriaIngresso> optionalCategoriaIngresso = categoriaIngressoRepository.findById(id);
        if (optionalCategoriaIngresso.isPresent()) {
            categoriaIngressoRepository.deleteById(id);
            return ResponseEntity.ok("Categoria de Ingresso deletada com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
