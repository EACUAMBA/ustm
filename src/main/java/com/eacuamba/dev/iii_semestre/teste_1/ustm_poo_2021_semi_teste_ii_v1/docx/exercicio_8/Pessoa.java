package com.eacuamba.dev.iii_semestre.teste_1.ustm_poo_2021_semi_teste_ii_v1.docx.exercicio_8;

public class Pessoa {
  private String nome;
  private Integer idade;
  
  public Pessoa(){}
  public Pessoa(String nome, Integer idade){
    this.nome = nome;
    this.idade = idade;
  }

  public void setNome(String nome){
    this.nome = nome;
  }
  public String getNome(){
    return this.nome;
  }

  public void setIdade(Integer idade){
    this.idade = idade;
  }
  public Integer getIdade(){
    return this.idade;
  }
}
