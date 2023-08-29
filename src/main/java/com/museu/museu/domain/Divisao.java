package com.museu.museu.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Divisao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome_divisao;
    @OneToMany(mappedBy = "divisao", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Peca> pecas_exposicao = new ArrayList<Peca>();
    private String predio;
    private String sala;
    private Toten toten;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome_divisao() {
        return nome_divisao;
    }

    public void setNome_divisao(String nome_divisao) {
        this.nome_divisao = nome_divisao;
    }

    public List<Peca> getPecas_exposicao() {
        return pecas_exposicao;
    }

    public void setPecas_exposicao(List<Peca> pecas_exposicao) {
        this.pecas_exposicao = pecas_exposicao;
    }

    public String getPredio() {
        return predio;
    }

    public void setPredio(String predio) {
        this.predio = predio;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public Toten getToten() {
        return toten;
    }

    public void setToten(Toten toten) {
        this.toten = toten;
    }

}
