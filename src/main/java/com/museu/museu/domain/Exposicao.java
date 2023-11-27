package com.museu.museu.domain;

import java.time.LocalDate;
import java.util.List;

import com.museu.museu.dto.DadosCadastroExposicao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Exposicao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Divisao divisao;
    private List<Peca> pecas;


    public Exposicao(DadosCadastroExposicao cadastroExposicao) {
        this.nome = cadastroExposicao.nome();
        this.dataInicio = LocalDate.parse(cadastroExposicao.dataInicio());
        this.dataFim = LocalDate.parse(cadastroExposicao.dataFim());
        
    }

    
}
