package com.museu.museu.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.museu.museu.dto.CadastroFuncionario;
import com.museu.museu.dto.EditarFuncionario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tb_funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
    private double salario;
    private String rg;
    private Endereco endereco;
    @OneToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ingresso> ingressos;
    private String area_especializacao;
    @Enumerated(EnumType.STRING)
    private Role role;
    
    private boolean demitido = false;
    
    @OneToMany(mappedBy = "pesquisador")
    @JsonIgnore
    private List<ViagensPesquisa> viagensPesquisa;

    public String getArea_especializacao() {
        return area_especializacao;
    }

    public void setArea_especializacao(String area_especializacao) {
        this.area_especializacao = area_especializacao;
    }


    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

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
        this.rg = funcionario.rg();
        this.salario = funcionario.salario();
        this.endereco = new Endereco(funcionario.rua(), funcionario.numero(), funcionario.bairro(),
                funcionario.cidade(), funcionario.estado(), funcionario.cep());
        if(funcionario.role() == Role.PESQUISADOR){
            this.area_especializacao = funcionario.area_especializacao();
        }
        this.role = funcionario.role();
    }

    public void setEdit(@Valid EditarFuncionario f) {
        this.nome = f.nome();
        this.telefone = f.telefone();

    }

    public boolean getDemitido(){
        return this.demitido;
    }

    public void setDemitido(boolean b) {
        this.demitido = b;
    }

}
