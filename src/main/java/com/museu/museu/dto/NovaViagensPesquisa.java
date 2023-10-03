
    package com.museu.museu.dto;

import java.sql.Date;

import com.museu.museu.domain.Funcionario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NovaViagensPesquisa(@NotBlank boolean aprovada, @NotBlank String objetivo, @NotBlank double custos, @NotNull Date data_inicio, @NotNull Date data_fim, String resultados, @NotBlank String destino, @NotNull Funcionario funcionario) {
    
}
