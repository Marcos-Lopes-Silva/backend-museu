package com.museu.museu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.museu.museu.domain.Exposicao;
import com.museu.museu.domain.Peca;
import com.museu.museu.dto.DadosCadastroExposicao;
import com.museu.museu.dto.DadosExposicao;
import com.museu.museu.repositories.DivisaoRepository;
import com.museu.museu.repositories.ExposicaoRepository;
import com.museu.museu.repositories.PecaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.museu.museu.domain.Divisao;

@RequestMapping("/exposicao")
@RestController
public class ExposicaoController {


    private final ExposicaoRepository exposicaoRepository;
    private final PecaRepository pecaRepository;
    private final DivisaoRepository divisaoRepository;

    @Autowired
    public ExposicaoController(ExposicaoRepository exposicaoRepository, PecaRepository pecaRepository, DivisaoRepository divisaoRepository) {
        this.exposicaoRepository = exposicaoRepository;
        this.pecaRepository = pecaRepository;
        this.divisaoRepository = divisaoRepository;
    }


    @PostMapping("/nova")
    @Transactional
    public ResponseEntity<DadosExposicao> criarExposicao(@RequestBody DadosCadastroExposicao dadosCadastroExposicao) {
        var exposicao = new Exposicao(dadosCadastroExposicao);
        Optional<Divisao> divisaoOptional = divisaoRepository.findById(dadosCadastroExposicao.divisao());
        List<Peca> pecas = new ArrayList<>();

        for (Integer pecaId : dadosCadastroExposicao.pecas()) {
            var pecaOptional = pecaRepository.findById(pecaId);
            if (pecaOptional.isPresent()) {
                pecas.add(pecaOptional.get());
            } else {
                throw new RuntimeException("Peça inexistente.");
            }
        }
        exposicao.setPecas(pecas);

        if (divisaoOptional.isPresent()) {
            exposicao.setDivisao(divisaoOptional.get());
        } else {
            throw new RuntimeException("Divisão inexistente.");
        }
        exposicaoRepository.save(exposicao);

        return ResponseEntity.ok(new DadosExposicao(exposicao));
    }


    @GetMapping
    public ResponseEntity<List<DadosExposicao>> getExposicao() {
        var exposicoes = exposicaoRepository.findAll();
        List<DadosExposicao> dadosExposicao = new ArrayList<>();

        for (Exposicao exposicao : exposicoes) {
            dadosExposicao.add(new DadosExposicao(exposicao));
        }

        return ResponseEntity.ok(dadosExposicao);
    }


    @GetMapping("{id}")
    public ResponseEntity<DadosExposicao> getExposicaoById(Integer id) {
        var exposicaoOptional = exposicaoRepository.findById(id);

        if (exposicaoOptional.isPresent()) {
            return ResponseEntity.ok(new DadosExposicao(exposicaoOptional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> excluirExposicao(Integer id) {
        exposicaoRepository.deleteById(id);
        return ResponseEntity.ok("Exposição excluída com sucesso!");
    }


    @PostMapping("{id}")
    public ResponseEntity<DadosExposicao> updateExposicao(Integer id, @RequestBody DadosCadastroExposicao dadosCadastroExposicao) {
        var exposicaoOptional = exposicaoRepository.findById(id);

        if (exposicaoOptional.isPresent()) {
            var exposicao = exposicaoOptional.get();
            exposicao.setNome(dadosCadastroExposicao.nome());
            exposicao.setDataInicio(LocalDate.parse(dadosCadastroExposicao.dataInicio()));
            exposicao.setDataFim(LocalDate.parse(dadosCadastroExposicao.dataFim()));
            exposicaoRepository.save(exposicao);
            return ResponseEntity.ok(new DadosExposicao(exposicao));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}