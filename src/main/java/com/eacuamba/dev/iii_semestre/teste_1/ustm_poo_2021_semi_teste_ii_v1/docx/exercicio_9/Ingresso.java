package com.eacuamba.dev.iii_semestre.teste_1.ustm_poo_2021_semi_teste_ii_v1.docx.exercicio_9;

public abstract class Ingresso {
    //teste: USTM-POO-2021-SemI-TesteII-V1.docx
    private Double valorEmReais;

    public Ingresso(Double valorEmReais){
        this.valorEmReais = valorEmReais;
    }

    public void imprimeValor(){
        System.out.println("Imprime valor!");
    }

    public Double getValorEmReais(){
        return this.valorEmReais;
    }

    public void setValorEmReais(Double valorEmReais) {
        this.valorEmReais = valorEmReais;
    }
}
