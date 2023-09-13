package com.museu.museu.domain;

import java.util.Date;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pecas")
@Entity
public class Peca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String autor;
    private String curador;
    private Date data_adquirida;
    private String descricao_peca;
    // private Divisao divisao;
    private String estado_conservacao;
    // private Sessao sessao;
    @Embedded
    private EmprestarPeca emprestarPeca = null;

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

    public String getEstado_conservacao() {
        return estado_conservacao;
    }

    public void setEstado_conservacao(String estado_conservacao) {
        this.estado_conservacao = estado_conservacao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCurador() {
        return curador;
    }

    public void setCurador(String curador) {
        this.curador = curador;
    }

    public EmprestarPeca getEmprestarPeca() {
        return emprestarPeca;
    }

    public void setEmprestarPeca(EmprestarPeca emprestarPeca) {
        this.emprestarPeca = emprestarPeca;
    }

}
