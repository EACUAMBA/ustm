package com.eacuamba.dev.programacao_i.avaliacao_1.exercicio_4;

import java.util.Scanner;

public class TrocaValores {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Troca valores...");

        System.out.println("Introduza o primeiro valor: ");
        String valor_1 = input.nextLine();

        System.out.println("Introduza o segundo valor: ");
        String valor_2 = input.nextLine();

        String valor_temporario = valor_1;
        valor_1 = valor_2;
        valor_2 = valor_temporario;

        System.out.println("Imprimindo o primeiro valor: " + valor_1);
        System.out.println("Imprimindo o segundo valor: " + valor_2);
    }
}
