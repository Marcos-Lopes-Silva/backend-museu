package com.museu.museu.dto;

import java.util.Date;
import com.museu.museu.domain.ViagensPesquisa;
import com.museu.museu.domain.Funcionario;

import com.museu.museu.controllers.FuncionariosController;

import com.museu.museu.dto.DadosFuncionario;

public record DadosViagensPesquisa(boolean aprovada, Integer id, double custos, String destino, String objetivo, Date data_inicio, Date data_fim, Funcionario funcionario) {
    public DadosViagensPesquisa(ViagensPesquisa viagensPesquisa){
        this(viagensPesquisa.isAprovada(), viagensPesquisa.getId(), viagensPesquisa.getCustos(), viagensPesquisa.getDestino(), viagensPesquisa.getObjetivo(), viagensPesquisa.getData_inicio(), viagensPesquisa.getData_fim(), viagensPesquisa.getFuncionario());
    }
    
    public DadosViagensPesquisa(boolean aprovada, Integer id, double custos, String destino, String objetivo, Date data_inicio, Date data_fim, Funcionario funcionario) {
        this.aprovada = aprovada;
        this.id = id;
        this.custos = custos;
        this.destino = destino;
        this.objetivo = objetivo;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.funcionario = funcionario;
    }
}