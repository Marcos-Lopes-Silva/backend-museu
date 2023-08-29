package com.museu.museu.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Peca {

    @Id
    private Integer id;
    private String nome;
    private Date data_adquirida;
    private String descricao_peca;
    private Divisao divisao;
    private String estado_conservacao;
    private Divisao localizacao;
    private Sessao sessao;

    public Peca() {
        
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData_adquirida() {
        return data_adquirida;
    }

    public void setData_adquirida(Date data_adquirida) {
        this.data_adquirida = data_adquirida;
    }

    public String getDescricao_peca() {
        return descricao_peca;
    }

    public void setDescricao_peca(String descricao_peca) {
        this.descricao_peca = descricao_peca;
    }

    public Divisao getDivisao() {
        return divisao;
    }

    public void setDivisao(Divisao divisao) {
        this.divisao = divisao;
    }

    public String getEstado_conservacao() {
        return estado_conservacao;
    }

    public void setEstado_conservacao(String estado_conservacao) {
        this.estado_conservacao = estado_conservacao;
    }

    public Divisao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Divisao localizacao) {
        this.localizacao = localizacao;
    }

}
