package com.museu.museu.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "tb_categoria_ingressos")
@Entity
public class CategoriaIngresso {
    
    private String nome;
    private String vendas;
    private double valor;
    private List<Ingresso> ingresso = new ArrayList<>();

}
