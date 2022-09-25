package com.eacuamba.dev.programacao_i.avaliacao_1.exercicio_1;

import java.util.Scanner;

public class Soma {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Soma");

        System.out.println("Introduza o primeiro valor: ");
        Double valor_1 = input.nextDouble();

        System.out.println("Introduza o segundo valor: ");
        Double valor_2 = input.nextDouble();

        System.out.println("A soma de " + valor_1 + " + " + valor_2 + " é igual a " + (valor_1 + valor_2));

    }
}
