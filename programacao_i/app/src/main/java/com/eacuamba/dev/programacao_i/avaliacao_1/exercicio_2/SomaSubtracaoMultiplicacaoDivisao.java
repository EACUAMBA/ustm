package com.eacuamba.dev.programacao_i.avaliacao_1.exercicio_2;

import java.util.Scanner;

public class SomaSubtracaoMultiplicacaoDivisao {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Soma, Subtração, Multiplicação e Divisão");

        System.out.println("Introduza o primeiro valor: ");
        Double valor_1 = input.nextDouble();

        System.out.println("Introduza o segundo valor: ");
        Double valor_2 = input.nextDouble();

        System.out.println("A Soma de " + valor_1 + " + " + valor_2 + " é igual a " + (valor_1 + valor_2));
        System.out.println("A Subtração de " + valor_1 + " - " + valor_2 + " é igual a " + (valor_1 - valor_2));
        System.out.println("A Multiplicação de " + valor_1 + " x " + valor_2 + " é igual a " + (valor_1 * valor_2));
        System.out.println("A Divisão de " + valor_1 + " / " + valor_2 + " é igual a " + (valor_1 / valor_2));

    }
}
