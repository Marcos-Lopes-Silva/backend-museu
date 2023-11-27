package com.museu.museu.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

import com.museu.museu.domain.Ingresso;
import com.museu.museu.domain.Usuario;
import com.museu.museu.dto.DadosIngresso;
import com.museu.museu.dto.DadosListagemIngressos;
import com.museu.museu.repositories.FuncionarioRepository;
import com.museu.museu.repositories.IngressoRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ingressos")
public class IngressosController {

    private final IngressoRepository ingressoRepository;

    @Autowired
    public IngressosController(IngressoRepository ingressoRepository, FuncionarioRepository funcionarioRepository) {
        this.ingressoRepository = ingressoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

   
    private final FuncionarioRepository funcionarioRepository;

    @PostMapping("/venda")
    @Transactional
    public ResponseEntity<DadosIngresso> vender(@Valid @RequestBody DadosIngresso dadosIngresso,
            HttpServletRequest request) {

        var user = request.getAttribute("user");

        if (user != null) {
            var ingresso = new Ingresso(dadosIngresso);

            var funcionario = funcionarioRepository.findById(((Usuario) user).getId());
            ingresso.setVendedor(funcionario);
            ingressoRepository.save(ingresso);

            return ResponseEntity.ok().body(dadosIngresso);
        }

        return null;
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemIngressos>> listarIngressos(
            @PageableDefault(size = 10, sort = "id") Pageable paginacao) {

        Page<Ingresso> lista = ingressoRepository.findAll(paginacao);

        var dados = lista.getContent();

        List<DadosListagemIngressos> dadosList = new ArrayList<>();

        for (Ingresso i : dados) {
            dadosList.add(new DadosListagemIngressos(i));
        }

        Page<DadosListagemIngressos> dadosPage = new PageImpl<>(dadosList, paginacao, 0);

        return ResponseEntity.ok(dadosPage);
    }

    @GetMapping("{id}")
    public ResponseEntity<DadosListagemIngressos> getIngresso(@PathVariable Integer id) {

        Optional<Ingresso> optionalIngresso = ingressoRepository.findById(id);

        if (optionalIngresso.isPresent()) {
            return ResponseEntity.ok(new DadosListagemIngressos(optionalIngresso.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletarIngresso(@PathVariable Integer id) {
        
        Optional<Ingresso> optionalIngresso = ingressoRepository.findById(id);
        if (optionalIngresso.isPresent()) {
            ingressoRepository.deleteById(id);
            return ResponseEntity.ok("Ingresso deletado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
