package com.museu.museu.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.museu.museu.dto.NovaPeca;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_pecas")
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
    @ManyToOne
    private Secao secao;
    @Embedded
    private EmprestarPeca emprestarPeca = null;

    public Peca(@Valid NovaPeca peca) {
        this.nome = peca.nome();
        this.autor = peca.autor();
        this.curador = peca.curador();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            this.data_adquirida = formatter.parse(peca.data_adquirida());
        } catch (ParseException e) {
            throw new RuntimeException("Erro ao converter data");
        }

        this.descricao_peca = peca.descricao_peca();
        this.estado_conservacao = peca.estado_conservacao();
    }
}
