package com.eacuamba.dev.aulas_1_2_3.exercicio_11;

import com.eacuamba.dev.utils.ConsoleBase;

import java.math.BigDecimal;

public class CalculaAreaDoTrapezio extends ConsoleBase {
    public static void main(String[] args) {
        output.println("Calcula a área do trapézio.");
        BigDecimal baseMaior = getValorNumericoAsBigDecimalFromConsole("Base maior");
        BigDecimal baseMenor = getValorNumericoAsBigDecimalFromConsole("Base menor");
        BigDecimal altura = getValorNumericoAsBigDecimalFromConsole("Altura");

        BigDecimal superficieDoTrapezio = baseMaior.add(baseMenor).multiply(altura).divide(BigDecimal.valueOf(2D), mathContext);
        output.printf("A superficie do trapézio com valores de base maior %.2f, base menor %.2f e altura %.2f é %.2f", baseMaior, baseMenor, altura, superficieDoTrapezio);
    }
}
