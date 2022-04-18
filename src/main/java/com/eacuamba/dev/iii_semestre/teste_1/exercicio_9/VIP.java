package com.eacuamba.dev.iii_semestre.teste_1.exercicio_9;

public class VIP extends Ingresso{
    //teste: USTM-POO-2021-SemI-TesteII-V1.docx
    private Double valorAdicional;

    public VIP(Double valorEmReais, Double valorAdicional){
        super(valorEmReais);
        this.valorAdicional = valorAdicional;
    }
    public Double getValorAdicional() {
        return valorAdicional + this.getValorEmReais();
    }
}
