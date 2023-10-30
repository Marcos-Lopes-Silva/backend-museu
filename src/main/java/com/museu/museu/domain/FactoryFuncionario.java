package com.museu.museu.domain;

import com.museu.museu.dto.CadastroFuncionario;

public class FactoryFuncionario {
    

    public Funcionario getFuncionario(CadastroFuncionario f) {
        if(f.role() == "PESQUISADOR") return new Pesquisador(f);
        else if(f.role() == "GERENTE") return new Gerente(f);
        else if(f.role() == "VENDEDOR") return new Vendedor(f);
        else return null;
    }
}
