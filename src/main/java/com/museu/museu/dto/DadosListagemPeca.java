package com.museu.museu.dto;

import java.util.Date;

import com.museu.museu.domain.Peca;

public record DadosListagemPeca(Integer id, String nome, String autor, String curador, Date data_adquirida) {

    public DadosListagemPeca(Peca peca) {
        this(peca.getId(), peca.getNome(), peca.getAutor(), peca.getCurador(), peca.getData_adquirida());
    }

}
