package com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_individual_2.calculadora.services.strategies;

public abstract class Operacao {

    private Double primeiroValor;
    private Double segundoValor;

    public Operacao(Double primeiroValor, Double segundoValor){
        this.primeiroValor = primeiroValor;
        this.segundoValor = segundoValor;
    }

    public Double getPrimeiroValor() {
        return primeiroValor;
    }

    public void setPrimeiroValor(Double primeiroValor) {
        this.primeiroValor = primeiroValor;
    }

    public Double getSegundoValor() {
        return segundoValor;
    }

    public void setSegundoValor(Double segundoValor) {
        this.segundoValor = segundoValor;
    }

    public abstract Double operar();



}
