package com.eacuamba.dev.iii_semestre.programacao_ii.teste_1.ustm_poo_2021_semi_teste_ii_v1.docx.exercicio_9;

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
