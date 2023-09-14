package com.museu.museu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.museu.museu.domain.Funcionario;
import com.museu.museu.domain.Usuario;
import com.museu.museu.dto.CadastroFuncionario;
import com.museu.museu.dto.DadosFuncionario;
import com.museu.museu.repositories.FuncionarioRepository;
import com.museu.museu.repositories.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<DadosFuncionario> registrarFuncionario(@Valid @RequestBody CadastroFuncionario funcionario, UriComponentsBuilder builder) {

        Funcionario novoFuncionario = new Funcionario(funcionario);
        Usuario usuario = new Usuario(funcionario.email(), encoder.encode(funcionario.senha()), novoFuncionario);
        novoFuncionario.setUsuario(usuario);

        userRepository.save(usuario);
        funcionarioRepository.save(novoFuncionario);

        var uri = builder.buildAndExpand(novoFuncionario.getId()).toUri();


        return ResponseEntity.created(uri).body(new DadosFuncionario(novoFuncionario));
    }

    @GetMapping("{id}")
    public ResponseEntity<DadosFuncionario> infoFuncionario(@PathVariable Integer id) {
        var funcionario = funcionarioRepository.findById(id);

        if (funcionario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new DadosFuncionario(funcionario.get()));
    }
}
