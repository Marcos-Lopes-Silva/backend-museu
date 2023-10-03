package com.museu.museu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.museu.museu.domain.ViagensPesquisa;
import com.museu.museu.dto.DadosViagensPesquisa;
import com.museu.museu.repositories.ViagensPesquisaRepository;

import com.museu.museu.domain.Funcionario;
import com.museu.museu.repositories.FuncionarioRepository;
import com.museu.museu.dto.CadastroFuncionario;
import com.museu.museu.dto.CadastroViagensPesquisa;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RequestMapping("/viagenspesquisa")
@RestController
public class ViagensPesquisaController {

    @Autowired
    private ViagensPesquisaRepository viagensPesquisaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    /**
     * @param cadastroViagensPesquisa
     * @param builder
     * @param request
     * @return
     */
    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DadosViagensPesquisa> criarViagensPesquisa(@Valid @RequestBody CadastroViagensPesquisa cadastroViagensPesquisa,
                                                                    UriComponentsBuilder builder,
                                                                    HttpServletRequest request) {

        // Retrieve the associated Funcionario by ID from the repository
        Funcionario funcionario = funcionarioRepository.findById(CadastroFuncionario.getId())
                .orElseThrow(() -> new RuntimeException("Funcionario not found"));

        // Create a new ViagensPesquisa object based on CadastroViagensPesquisa data
        ViagensPesquisa novaViagensPesquisa = new ViagensPesquisa(cadastroViagensPesquisa);
        
        // Set the Funcionario for the ViagensPesquisa
        novaViagensPesquisa.setFuncionario(funcionario);

        Integer pesquisador_id = 7;
        novaViagensPesquisa.setPesquisadorId(pesquisador_id);

        
        // Save the ViagensPesquisa object to the repository
        viagensPesquisaRepository.save(novaViagensPesquisa);

        // Return the created ViagensPesquisa data
        return ResponseEntity.ok(new DadosViagensPesquisa(novaViagensPesquisa));
    }

    public ViagensPesquisaRepository getViagensPesquisaRepository() {
        return viagensPesquisaRepository;
    }

    public void setViagensPesquisaRepository(ViagensPesquisaRepository viagensPesquisaRepository) {
        this.viagensPesquisaRepository = viagensPesquisaRepository;
    }
}