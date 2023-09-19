package com.museu.museu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.museu.museu.domain.Ingresso;
import com.museu.museu.domain.Usuario;
import com.museu.museu.dto.DadosIngresso;
import com.museu.museu.repositories.FuncionarioRepository;
import com.museu.museu.repositories.IngressoRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ingressos")
public class IngressosController {
    

    @Autowired
    private IngressoRepository ingressoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @PostMapping("/venda")
    public ResponseEntity<DadosIngresso> vender(@RequestBody @Valid DadosIngresso dadosIngresso, HttpServletRequest request){

        var user = request.getAttribute("user");

        if(user != null){
            var ingresso = new Ingresso(dadosIngresso);

            var funcionario = funcionarioRepository.findByUsuarioId(((Usuario) user).getId());
            ingresso.setVendedor(funcionario);
            ingressoRepository.save(ingresso);

            return ResponseEntity.ok().body(dadosIngresso);
        }

        return null;
    }
}
