package com.eacuamba.dev.aulas_1_2_3.exercicio_4;

import java.math.BigDecimal;

import com.eacuamba.dev.utils.ConsoleBase;

public class DivisaoDoPrimeiroPeloSugundo extends ConsoleBase {
    public static void main(String[] args) {
        output.println("Divisão do primeiro pelo segundo");
        BigDecimal primeiroValor = getValorNumericoAsBigDecimalFromConsole("Primeiro valor");
        BigDecimal segundoValor = getValorNumericoAsBigDecimalFromConsole("Segundo valor");
        BigDecimal divisao = primeiroValor.divide(segundoValor, mathContext);
        output.printf("O resultado da divisão do primeiro (%.2f) pelo segundo (%.2f) é %.10f.", primeiroValor, segundoValor, divisao);
    }
}
