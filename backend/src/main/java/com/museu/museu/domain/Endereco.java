package com.museu.museu.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco {

    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    @Column(columnDefinition = "char")
    private String estado;
    private String cep;
}
