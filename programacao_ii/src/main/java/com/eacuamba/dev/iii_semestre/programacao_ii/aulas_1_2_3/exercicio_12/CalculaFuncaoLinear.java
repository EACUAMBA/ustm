package com.eacuamba.dev.iii_semestre.programacao_ii.aulas_1_2_3.exercicio_12;

import com.eacuamba.dev.utils.ConsoleBase;

import java.math.BigDecimal;

public class CalculaFuncaoLinear extends ConsoleBase {
    public static void main(String[] args) {
        output.println("Calcula a função linear.");
        output.println("Formúla: f(x) = a * x + b");
        BigDecimal valorDeA = getValorNumericoAsBigDecimalFromConsole("Valor de a (não pode ser zero)");
        BigDecimal valorDeX = getValorNumericoAsBigDecimalFromConsole("Valor de x");
        BigDecimal valorDeB = getValorNumericoAsBigDecimalFromConsole("Valor de b (pode ser zero)");

        BigDecimal resultado = valorDeA.multiply(valorDeX).add(valorDeB, mathContext);
        output.printf("O resultado da função linear f(%.2f)=%.2f x %.2f + %.2f é %.2f", valorDeX, valorDeA, valorDeX, valorDeB, resultado);

    }
}
