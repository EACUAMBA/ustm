package com.eacuamba.dev.aulas_1_2_3.exercicio_19;

import com.eacuamba.dev.utils.ConsoleBase;

import java.math.BigDecimal;

public class CalculaVolumeDoCilindro extends ConsoleBase {
    public static void main(String[] args) {
        output.println("Calcula o volume do cilindro (area da base (pi * raio * raio) * altura).");
        BigDecimal raio = getValorNumericoAsBigDecimalFromConsole("Raio da base");
        BigDecimal altura = getValorNumericoAsBigDecimalFromConsole("Altura");

        BigDecimal volume = raio.pow(2, mathContext).multiply(BigDecimal.valueOf(Math.PI)).multiply(altura);
        output.printf("%nA área do cilindro com raio %.2f e altura %.2f é de %.2f.%n", raio, altura, volume);
    }
}
