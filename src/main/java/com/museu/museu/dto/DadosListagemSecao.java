package com.museu.museu.dto;

import com.museu.museu.domain.Secao;

public record DadosListagemSecao(Integer id, String nome) {
    
    public DadosListagemSecao(Secao s){
        this(s.getId(), s.getNome());
    }
}
