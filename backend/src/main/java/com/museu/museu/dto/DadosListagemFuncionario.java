package com.museu.museu.dto;

import com.museu.museu.domain.Funcionario;

public record DadosListagemFuncionario(String nome, String cpf, String email, String telefone, String cargo, double salario) {


    public DadosListagemFuncionario(Funcionario f){
        this(f.getNome(), f.getCpf(), f.getUsuario().getEmail(), f.getTelefone(), f.getCargo(), f.getSalario());
    }
}
