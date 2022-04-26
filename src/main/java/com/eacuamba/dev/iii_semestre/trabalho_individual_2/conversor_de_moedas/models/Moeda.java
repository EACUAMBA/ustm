package com.eacuamba.dev.iii_semestre.trabalho_individual_2.conversor_de_moedas.models;

public class Moeda {
    private String nome;
    private Double precoEmMeticais;

    public Moeda() {
    }

    public Moeda(String nome, Double precoEmMeticais) {
        this.nome = nome;
        this.precoEmMeticais = precoEmMeticais;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrecoEmMeticais() {
        return precoEmMeticais;
    }

    public void setPrecoEmMeticais(Double precoEmMeticais) {
        this.precoEmMeticais = precoEmMeticais;
    }

    @Override
    public String toString(){
        return this.nome;
    }
}
