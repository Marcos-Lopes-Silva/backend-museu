package com.museu.museu.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroExposicao(@NotBlank String nome, @NotBlank String dataInicio, @NotBlank String dataFim, @NotBlank Integer divisao, @NotBlank List<Integer> pecas) {
    
}
