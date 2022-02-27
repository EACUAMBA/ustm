package com.eacuamba.dev.aulas_1_2_3.exercicio_4;

import com.eacuamba.dev.utils.ConsoleBase;

import java.math.BigDecimal;

public class SubtracaoDoSegundoPeloPrimeiro extends ConsoleBase {
    public static void main(String[] args) {
        output.println("Subtracção do segundo pelo primeiro");
        BigDecimal primeiroValor = getValorNumericoAsBigDecimalFromConsole("Primeiro valor");
        BigDecimal segundoValor = getValorNumericoAsBigDecimalFromConsole("Segundo valor");
        BigDecimal subtracao = segundoValor.subtract(primeiroValor);
        output.printf("O resultado da subtracção do segundo (%.2f) pelo primeiro (%.2f) é %.2f.", segundoValor, primeiroValor, subtracao);
    }
}
