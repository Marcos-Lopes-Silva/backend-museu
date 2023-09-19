package com.museu.museu.dto;

import com.museu.museu.domain.Funcionario;

public record DadosFuncionario(String nome, String cpf, String email, String telefone) {

    public DadosFuncionario(Funcionario funcionario) {
        this(funcionario.getNome(), funcionario.getCpf(), funcionario.getUsuario().getEmail(),
                funcionario.getTelefone());
    }
}
