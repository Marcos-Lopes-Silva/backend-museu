package com.museu.museu.dto;



public record DadosLogin(String login, String senha) {

    public DadosLogin(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
}
