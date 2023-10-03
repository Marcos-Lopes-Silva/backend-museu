package com.museu.museu.domain;

import java.util.Date;

import com.museu.museu.dto.CadastroViagensPesquisa;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_viagens_pesquisa")
@Setter
@Getter
public class ViagensPesquisa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double custos;
    private boolean aprovada = false;
    private String destino;
    private String objetivo;
    private Date data_inicio;
    private Date data_fim;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    private String resultados;

    public ViagensPesquisa(@Valid CadastroViagensPesquisa cadastroViagensPesquisa) {
        this.aprovada = false;
        this.custos = cadastroViagensPesquisa.custos();
        this.destino = cadastroViagensPesquisa.destino();
        this.objetivo = cadastroViagensPesquisa.objetivo();
        this.resultados = null;
        this.data_inicio = cadastroViagensPesquisa.data_inicio();
        this.data_fim = cadastroViagensPesquisa.data_fim();
    }

    public Integer getPesquisadorId() {
        return funcionario.getId();
    }

    public void setPesquisadorId(Integer pesquisador_id) {
        // do nothing
    }
}