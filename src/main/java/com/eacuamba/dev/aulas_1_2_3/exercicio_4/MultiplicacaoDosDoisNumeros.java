package com.eacuamba.dev.aulas_1_2_3.exercicio_4;

import com.eacuamba.dev.utils.ConsoleBase;

import java.math.BigDecimal;

public class MultiplicacaoDosDoisNumeros extends ConsoleBase {
    public static void main(String[] args) {
        output.println("Multiplicação dos dois números");
        BigDecimal primeiroValor = getValorNumericoAsBigDecimalFromConsole("Primeiro valor");
        BigDecimal segundoValor = getValorNumericoAsBigDecimalFromConsole("Segundo valor");
        BigDecimal multiplicacao = primeiroValor.multiply(segundoValor);
        output.printf("O resultado da multiplicação de %.2f com %.2f é de %.2f.", primeiroValor, segundoValor, multiplicacao);
    }
}
