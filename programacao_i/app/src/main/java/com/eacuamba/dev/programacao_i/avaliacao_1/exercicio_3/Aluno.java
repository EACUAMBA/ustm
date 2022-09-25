package com.eacuamba.dev.programacao_i.avaliacao_1.exercicio_3;

public class Aluno {
    private String nome;
    private Double nota_1;
    private Double nota_2;
    private Double nota_3;

    public Aluno(String nome, Double nota_1, Double nota_2, Double nota_3){
        this.nome = nome;
        this.nota_1 = nota_1;
        this.nota_2 = nota_2;
        this.nota_3 = nota_3;
    }

    public String getNome() {
        return nome;
    }

    public Double getMediaAritmetica(){
        return (this.nota_1 + this.nota_2 + this.nota_3)/(3);
    }
}
