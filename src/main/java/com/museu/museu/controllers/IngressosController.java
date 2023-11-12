package com.museu.museu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.museu.museu.domain.Ingresso;
import com.museu.museu.domain.Usuario;
import com.museu.museu.dto.DadosIngresso;
import com.museu.museu.repositories.FuncionarioRepository;
// import com.museu.museu.repositories.FuncionariosRepository;
import com.museu.museu.repositories.IngressoRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ingressos")
public class IngressosController {

    private static final int MAX_CAPACITY = 150;

    @Autowired
    private IngressoRepository ingressoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @PostMapping("/venda")
    @Transactional
    public ResponseEntity<String> vender(@Valid @RequestBody DadosIngresso dadosIngresso,
            HttpServletRequest request) {

        var user = request.getAttribute("user");

        if (user != null) {
            if (getCurrentCapacity() >= MAX_CAPACITY) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Capacidade máxima atingida.");
            }
            else{
            var ingresso = new Ingresso(dadosIngresso);

            var funcionario = funcionarioRepository.findById(((Usuario) user).getId());
            ingresso.setVendedor(funcionario);
            ingressoRepository.save(ingresso);

            return ResponseEntity.ok().body(dadosIngresso);
        }

        return null;
    }
 
    private int getCurrentCapacity() {
        int currentCapacity = (int) ingressoRepository.count();
        return currentCapacity;
    }
    
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<String> deleteIngresso(@PathVariable Long id) {
    
        return ingressoRepository.findById(id);
        .map(ingresso -> {
            ingressoRepository.delete(ingresso);
            return ResponseEntity.ok("Ingresso deletado.");
        })
        .orElse(ResponseEntity.notFound().build());
}
    }
    