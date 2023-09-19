package com.museu.museu.dto;

import com.museu.museu.domain.Funcionario;

public record DadosListagemFuncionario(String nome, String cpf) {

    public DadosListagemFuncionario(Funcionario f) {
        this(f.getNome(), f.getCpf());
    }
}
