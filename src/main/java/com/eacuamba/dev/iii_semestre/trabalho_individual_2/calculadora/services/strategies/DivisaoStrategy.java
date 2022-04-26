package com.eacuamba.dev.iii_semestre.trabalho_individual_2.calculadora.services.strategies;

public class DivisaoStrategy extends Operacao {
    public DivisaoStrategy(Double primeiroValor, Double segundoValor) {
        super(primeiroValor, segundoValor);
    }

    @Override
    public Double operar() {
        if(this.getPrimeiroValor() != null && this.getSegundoValor() != null){
            return this.getPrimeiroValor() / this.getSegundoValor();
        }
        return null;
    }
}
