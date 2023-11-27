package com.museu.museu.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.museu.museu.domain.Cache;
import com.museu.museu.domain.EmprestarPeca;
import com.museu.museu.domain.Peca;
import com.museu.museu.dto.DadosListagemPeca;
import com.museu.museu.dto.DadosPeca;
import com.museu.museu.dto.NovaPeca;
import com.museu.museu.repositories.PecaRepository;
import com.museu.museu.repositories.SecaoRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pecas")
public class PecaController {

    private Cache cache = Cache.getInstance();
    private final PecaRepository pecaRepository;

    
    private final SecaoRepository secaoRepository;

    @Autowired
    public PecaController(PecaRepository pecaRepository, SecaoRepository secaoRepository) {
        this.pecaRepository = pecaRepository;
        this.secaoRepository = secaoRepository;
    }

    
    @PostMapping("/criar")
    @Transactional
    public ResponseEntity<DadosPeca> criarPeca(@Valid @RequestBody NovaPeca peca, HttpServletRequest request,
            UriComponentsBuilder builder) {
        Peca novaPeca = new Peca(peca);
        var secao = secaoRepository.findById(peca.secao());
        if (secao.isPresent()) {
            novaPeca.setSecao(secao.get());
            pecaRepository.save(novaPeca);

            var uri = builder.buildAndExpand(novaPeca.getId()).toUri();

            return ResponseEntity.created(uri).body(new DadosPeca(novaPeca));
        } else {
            // Handle the case when the secao is not found
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPeca>> listarPecas(
            @PageableDefault(size = 10, sort = "id") Pageable paginacao, String filtro) {

        if (filtro == null) {
            
            Page<Peca> lista = pecaRepository.findAll(paginacao);

            var dados = lista.getContent();

            List<DadosListagemPeca> dadosList = new ArrayList<>();

            for (Peca p : dados) {
                // cache.put("peca" + p.getId(), new DadosListagemPeca(p));
                dadosList.add(new DadosListagemPeca(p));
            }

            Page<DadosListagemPeca> dadosPage = new PageImpl<>(dadosList, paginacao, 0);

            return ResponseEntity.ok(dadosPage);

        } else if (filtro.equals("emprestadas")) {

            Page<Peca> lista = pecaRepository.findAllByEmprestarPecaNotNull(paginacao);

            var dados = lista.getContent();

            List<DadosListagemPeca> dadosList = new ArrayList<>();

            for (Peca p : dados) {
                dadosList.add(new DadosListagemPeca(p));
            }

            Page<DadosListagemPeca> dadosPage = new PageImpl<>(dadosList, paginacao, 0);

            return ResponseEntity.ok(dadosPage);
        }
        return null;
    }

    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity<String> excluirPeca(@PathVariable Integer id) {

        pecaRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<DadosPeca> detalharPeca(@PathVariable Integer id) {
        if(cache.get("peca" + id) != null) {
            return ResponseEntity.ok(new DadosPeca((Peca) cache.get("peca" + id)));
        } else {
            Optional<Peca> peca = pecaRepository.findById(id);

        if (peca.isPresent()) {
            return ResponseEntity.ok(new DadosPeca(peca.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
        }
        
    }

    public ResponseEntity<Peca> emprestarPeca(@Valid @RequestBody EmprestarPeca emprestarPeca, @PathVariable Integer id, UriComponentsBuilder builder) {

        Optional<Peca> optionalPeca = pecaRepository.findById(id);
        if (optionalPeca.isPresent()) {
            Peca peca = optionalPeca.get();
            peca.setEmprestarPeca(emprestarPeca);

            var uri = builder.path("/pecas/{id}").buildAndExpand(peca.getId()).toUri();

            return ResponseEntity.created(uri).body(peca);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    public ResponseEntity<Peca> devolverPeca(@PathVariable Integer id, 
            UriComponentsBuilder builder) {

        Optional<Peca> optionalPeca = pecaRepository.findById(id);
        if (optionalPeca.isPresent()) {
            Peca peca = optionalPeca.get();
            peca.setEmprestarPeca(null);

            var uri = builder.path("/pecas/{id}").buildAndExpand(peca.getId()).toUri();

            return ResponseEntity.created(uri).body(peca);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
