package com.eacuamba.dev.aulas_1_2_3.exercicio_9;

import com.eacuamba.dev.utils.ConsoleBase;

import java.math.BigDecimal;
import java.math.MathContext;

public class CalcularHipotenusaDoTriangulo extends ConsoleBase {
    public static void main(String[] args) {
        output.println("Calcula a hipotenusa do triângulo.");
        BigDecimal primeiroCateto = getValorNumericoAsBigDecimalFromConsole("Primeiro cateto");
        BigDecimal segundoCateto = getValorNumericoAsBigDecimalFromConsole("Segundo cateto");

        double hipotenusa = Math.sqrt(primeiroCateto.pow(2, mathContext).add(segundoCateto.pow(2, mathContext)).doubleValue());
        output.printf("A hipotenusa do triângulo com primeiro cateto %.2f e segundo cateto %.2f é %.2f", primeiroCateto, segundoCateto, hipotenusa);
    }
}
