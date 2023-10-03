package com.museu.museu.dto;

import java.util.Date;
import com.museu.museu.domain.Funcionario;
import com.museu.museu.domain.ViagensPesquisa;

public record DadosViagensPesquisa(Integer id, String destino, String objetivo,  double custos, boolean aprovada, Funcionario funcionario, Date data_inicio, Date data_fim, String resultados) {
    

    public DadosViagensPesquisa(ViagensPesquisa vp, Funcionario f) {
        this(vp.getId(), vp.getDestino(), vp.getObjetivo(), vp.getCustos(), vp.isAprovada(), f, vp.getData_inicio(), vp.getData_fim(), vp.getResultados()); 
    }
}
