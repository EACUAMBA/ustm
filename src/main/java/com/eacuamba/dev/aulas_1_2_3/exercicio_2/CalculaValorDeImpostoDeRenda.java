package com.eacuamba.dev.aulas_1_2_3.exercicio_2;

import com.eacuamba.dev.utils.ConsoleBase;

import java.math.BigDecimal;

public class CalculaValorDeImpostoDeRenda extends ConsoleBase {
    public static void main(String[] args) {
        output.println("Bem vindo ao SICIR (Sistema de Calculo de Imposto Renda).");
        String nome = getTextoFromConsole("Nome");
        BigDecimal salario = getValorNumericoAsBigDecimalFromConsole("Salário");
        BigDecimal impostoDeRenda = salario.multiply(BigDecimal.valueOf(0.05));

        output.printf("%nO valor de imposto de rende do \"%s\" com o salário de \"%s\" é de \"%s\".%n", nome, convertBigDecimalToStringConcurrency(salario), convertBigDecimalToStringConcurrency(impostoDeRenda));

    }


}
