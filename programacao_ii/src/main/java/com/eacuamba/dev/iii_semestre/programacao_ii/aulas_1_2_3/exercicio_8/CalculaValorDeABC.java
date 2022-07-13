package com.eacuamba.dev.iii_semestre.programacao_ii.aulas_1_2_3.exercicio_8;

import com.eacuamba.dev.utils.ConsoleBase;

import java.math.BigDecimal;

public class CalculaValorDeABC extends ConsoleBase {
    public static void main(String[] args) {
        output.println("Calcula valores de A, B e C.");
        BigDecimal valorDeC = getValorNumericoAsBigDecimalFromConsole("Valor de C");
        BigDecimal valorDeB = getValorNumericoAsBigDecimalFromConsole("Valor de B");

        /*
        Insolando o A
        C = (A + B) * B
        C/B = A+B
        (C/B)-B = A
         */

        BigDecimal valorDeA = valorDeC.divide(valorDeB, mathContext).subtract(valorDeB);
        output.printf("%nO valores de A, B e C respectivamente são %.2f, %.2f e %.2f.", valorDeA.doubleValue(), valorDeB.doubleValue(), valorDeC.doubleValue());
    }
}
