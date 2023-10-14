package com.museu.museu.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.museu.museu.domain.Role;

public record CadastroFuncionario(@NotBlank String nome, @NotBlank @Pattern(regexp = "[0-9]{11}") String cpf,
                @NotBlank @Email String email, @NotBlank @Pattern(regexp = "[0-9]{0,11}") String telefone,
                @NotBlank String senha, @NotBlank @Pattern(regexp = "[0-9]{0,10}") String rg,
                String rua, String numero, String bairro, String cidade, String estado, String cep, @NotNull Role role,
                String area_especializacao, @NotNull double salario) {

    public static Integer getId() {
        return null;
    }

}
