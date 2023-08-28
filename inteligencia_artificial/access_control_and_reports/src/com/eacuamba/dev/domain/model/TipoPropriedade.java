package com.eacuamba.dev.domain.model;

public enum TipoPropriedade {
    VIVENDA("Vivenda", 2),
    FLAT("Flat", 3);

    TipoPropriedade(String nome, int numero){
        this.nome = nome;
        this.numero = numero;
    }
    private String nome;
    private int numero;

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }
}
