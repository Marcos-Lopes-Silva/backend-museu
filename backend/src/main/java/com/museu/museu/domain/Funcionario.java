package com.museu.museu.domain;

import com.museu.museu.dto.CadastroFuncionario;
import com.museu.museu.dto.EditarFuncionario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpf;
    private String cargo;
    private String telefone;
    private String rg;
    private Endereco endereco;
    @OneToOne
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Funcionario(@Valid CadastroFuncionario funcionario) {
        this.nome = funcionario.nome();
        this.cpf = funcionario.cpf();
        this.telefone = funcionario.telefone();
        this.cargo = funcionario.cargo();
        this.rg = funcionario.rg();
        this.endereco = new Endereco(funcionario.rua(), funcionario.numero(), funcionario.bairro(),
                funcionario.cidade(), funcionario.estado(), funcionario.cep());
    }

    public void setEdit(@Valid EditarFuncionario f) {
        this.nome = f.nome();
        this.cargo = f.cargo();
        this.telefone = f.telefone();
    }

}
