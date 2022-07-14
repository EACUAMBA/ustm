package com.eacuamba.dev.iii_semestre.programacao_ii.aulas_1_2_3.exercicio_16;

import java.math.BigDecimal;

import com.eacuamba.dev.utils.ConsoleBase;

public class CalcularHipotenusaDoTriangulo extends ConsoleBase {
    public static void main(String[] args) {
        output.println("Calcula a hipotenusa do triângulo rectângulo.");
        BigDecimal primeiroCateto = getValorNumericoAsBigDecimalFromConsole("Primeiro cateto");
        BigDecimal segundoCateto = getValorNumericoAsBigDecimalFromConsole("Segundo cateto");
        double hipotenusa = Math.sqrt(primeiroCateto.pow(2, mathContext).add(segundoCateto.pow(2, mathContext)).doubleValue());
        output.printf("A hipotenusa do triângulo com primeiro cateto %.2f e segundo cateto %.2f é %.2f", primeiroCateto, segundoCateto, hipotenusa);
    }
}
