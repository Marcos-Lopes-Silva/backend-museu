package com.museu.museu.dto;

import java.util.List;

import com.museu.museu.domain.Divisao;
import com.museu.museu.domain.Peca;

public record DadosExposicao(Integer id, String nome, String dataInicio, String dataFim, Divisao divisao, List<Peca> pecas) {
    public DadosExposicao(com.museu.museu.domain.Exposicao exposicao) {
        this(exposicao.getId(), exposicao.getNome(), exposicao.getDataInicio().toString(), exposicao.getDataFim().toString(), exposicao.getDivisao(), exposicao.getPecas());
    }
}
