package com.eacuamba.dev.aulas_1_2_3.exercicio_4;

import com.eacuamba.dev.utils.ConsoleBase;

import java.math.BigDecimal;

public class SomaDoisNumeros extends ConsoleBase {
    public static void main(String[] args) {
        output.println("Soma dois números");
        BigDecimal primeiroValor = getValorNumericoAsBigDecimalFromConsole("Primeiro valor");
        BigDecimal segundoValor = getValorNumericoAsBigDecimalFromConsole("Segundo valor");
        BigDecimal soma = primeiroValor.add(segundoValor);
        output.printf("O resultado da soma de %.2f com %.2f é de %.2f.", primeiroValor, segundoValor, soma);
    }
}
