package com.eacuamba.dev.iii_semestre.programacao_ii.teste_2.exercicio_4.exercicio_1;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int idade;
        boolean termina;
        do {
            try {
                idade = Integer.parseInt(new Scanner(System.in).nextLine());
                termina = true;
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido volte a inserir.");
                termina = false;
            }
        } while (!termina);
    }
}
