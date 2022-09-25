package com.eacuamba.dev.iii_semestre.programacao_ii.aulas_1_2_3.exercicio_4;

import com.eacuamba.dev.utils.ConsoleBase;

import java.math.BigDecimal;

public class PrimeiroElevadoAoQuadrado extends ConsoleBase {
    public static void main(String[] args) {
        output.println("O primeiro elevado ao quadrado");
        BigDecimal primeiroValor = getValorNumericoAsBigDecimalFromConsole("Primeiro valor");
        BigDecimal elevado = primeiroValor.pow(2, mathContext);
        output.printf("O resultado do primeiro %.2f elevado ao quadrado é de %.2f.", primeiroValor, elevado);
    }
}
