package com.museu.museu.dto;

import com.museu.museu.domain.Funcionario;

public record DadosListagemFuncionario(Integer id, String nome, String cpf) {

    public DadosListagemFuncionario(Funcionario f) {
        this(f.getId(), f.getNome(), f.getCpf());
    }
}
