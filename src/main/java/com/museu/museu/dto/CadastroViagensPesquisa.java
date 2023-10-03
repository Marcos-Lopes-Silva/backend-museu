package com.museu.museu.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroViagensPesquisa(
        @NotBlank double custos,
        @NotBlank boolean aprovada,
        @NotBlank String destino,
        @NotBlank String objetivo,
        @NotBlank String resultados,
        @NotBlank Date data_inicio,
        @NotBlank Date data_fim,
        @NotNull Integer pesquisador_id) {
}