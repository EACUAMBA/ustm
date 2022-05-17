package com.eacuamba.dev.iii_semestre.programacao_ii.aulas_1_2_3.exercicio_4;

import com.eacuamba.dev.utils.ConsoleBase;

import java.math.BigDecimal;

public class SubtracaoDoPrimeiroPeloSugundo extends ConsoleBase {
    public static void main(String[] args) {
        output.println("Subtracção do primeiro pelo segundo");
        BigDecimal primeiroValor = getValorNumericoAsBigDecimalFromConsole("Primeiro valor");
        BigDecimal segundoValor = getValorNumericoAsBigDecimalFromConsole("Segundo valor");
        BigDecimal subtracao = primeiroValor.subtract(segundoValor);
        output.printf("O resultado da subtracção do primeiro (%.2f) pelo segundo (%.2f) é %.2f.", primeiroValor, segundoValor, subtracao);

    }
}
