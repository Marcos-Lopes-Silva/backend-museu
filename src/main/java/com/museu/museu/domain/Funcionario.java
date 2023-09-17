package com.museu.museu.domain;

import com.museu.museu.dto.CadastroFuncionario;
import com.museu.museu.dto.EditarFuncionario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "tb_funcionarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Funcionario {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
    private String rg;
    private String cargo;
    private double salario;
    @Embedded
    private Endereco endereco;
    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    public Funcionario(@Valid CadastroFuncionario funcionario) {
        this.nome = funcionario.nome();
        this.cpf = funcionario.cpf();
        this.telefone = funcionario.telefone();
        this.rg = funcionario.rg();
        this.cargo = funcionario.cargo();
    }

    public void setSenha(String senha) {
        usuario.setSenha(senha);
    }

    public void setEdit(EditarFuncionario f){
        this.nome = f.nome();
        this.telefone = f.telefone();
        this.cargo = f.cargo();
        this.salario = f.salario();
        this.endereco = f.endereco();
    }
}
