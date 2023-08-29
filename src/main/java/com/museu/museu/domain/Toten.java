package com.museu.museu.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Toten {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Divisao localizacao;
    private CameraSeguranca reconhecimento_facial;
    private CriancaPerdida crianca_perdida;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Divisao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Divisao localizacao) {
        this.localizacao = localizacao;
    }

    public CameraSeguranca getReconhecimento_facial() {
        return reconhecimento_facial;
    }

    public void setReconhecimento_facial(CameraSeguranca reconhecimento_facial) {
        this.reconhecimento_facial = reconhecimento_facial;
    }

    public CriancaPerdida getCrianca_perdida() {
        return crianca_perdida;
    }

    public void setCrianca_perdida(CriancaPerdida crianca_perdida) {
        this.crianca_perdida = crianca_perdida;
    }

}
