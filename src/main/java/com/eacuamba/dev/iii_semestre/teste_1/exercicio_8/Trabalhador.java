package com.eacuamba.dev.iii_semestre.teste_1.exercicio_8;

public class Trabalhador extends Pessoa{
  private Double dinheiro;

  public Trabalhador(){}
  public Trabalhador(Double dinheiro){
    this.dinheiro = dinheiro;
  }
  public Trabalhador(String nome, Integer idade, Double dinheiro){
    super(nome, idade);
    this.dinheiro = dinheiro;
  }

  public void trabalhar(){
      System.out.println("Trabalhar.");
  }

  public void fazerCompras(){
    System.out.println("Fazer compras.");
  }

  public void setDinheiro(Double dinheiro){
    this.dinheiro = dinheiro;
  }
  public Double getDinheiro(){
    return this.dinheiro;
  }
}
