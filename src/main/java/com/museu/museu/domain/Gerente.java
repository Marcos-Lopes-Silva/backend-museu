package com.museu.museu.domain;

import com.museu.museu.dto.CadastroFuncionario;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Gerente extends Funcionario{

    @OneToMany(mappedBy = "gerente")
    private Divisao divisao_responsavel;

    public Gerente(CadastroFuncionario f) {
        super(f);
        this.divisao_responsavel = f.divisao_responsavel();
    }
}
