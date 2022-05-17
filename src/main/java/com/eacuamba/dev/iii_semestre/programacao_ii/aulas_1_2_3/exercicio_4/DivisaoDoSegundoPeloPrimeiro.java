package com.eacuamba.dev.iii_semestre.programacao_ii.aulas_1_2_3.exercicio_4;

import com.eacuamba.dev.utils.ConsoleBase;

import java.math.BigDecimal;

public class DivisaoDoSegundoPeloPrimeiro extends ConsoleBase {
    public static void main(String[] args) {
        output.println("Divisão do segundo pelo primeiro");
        BigDecimal primeiroValor = getValorNumericoAsBigDecimalFromConsole("Primeiro valor");
        BigDecimal segundoValor = getValorNumericoAsBigDecimalFromConsole("Segundo valor");
        BigDecimal divisao = segundoValor.divide(primeiroValor, mathContext);
        output.printf("O resultado da divisão do segundo (%.2f) pelo primeiro (%.2f) é %.10f.", segundoValor, primeiroValor, divisao);
    }
}
