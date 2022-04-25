package com.eacuamba.dev.iii_semestre.teste_1.ustm_poo_2021_semi_teste_ii_v1.docx.exercicio_8;

public class App {
  public static void main(String[] args) {
    Pessoa trabalhador = new Trabalhador("Erituny Salenko", 34, 50_000D);
    Pessoa miseravel = new Miseravel("Erituny Salenko", 56);

    System.out.println("Trabalhdor:");
    System.out.println("\tPessoa.Nome: " + trabalhador.getNome());
    System.out.println("\tPessoa.Idade: " + trabalhador.getIdade());
    System.out.println("\tTrabalhador.Dinheiro: " + ((Trabalhador)trabalhador).getDinheiro());
    ((Trabalhador)trabalhador).fazerCompras();
    ((Trabalhador)trabalhador).trabalhar();

    System.out.println();

    System.out.println("Miseravel:");
    System.out.println("\tPessoa.Nome: " + miseravel.getNome());
    System.out.println("\tPessoa.Idade: " + miseravel.getIdade());
    ((Miseravel)miseravel).mendigar();
  }
}
