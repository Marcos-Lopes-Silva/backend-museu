package com.museu.museu.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Vendedor extends Funcionario {

    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL)
    private List<Ingresso> ingressosVendidos;
}
