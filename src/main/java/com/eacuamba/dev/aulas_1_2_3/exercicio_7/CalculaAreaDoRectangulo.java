package com.eacuamba.dev.aulas_1_2_3.exercicio_7;

import com.eacuamba.dev.utils.ConsoleBase;

import java.math.BigDecimal;

public class CalculaAreaDoRectangulo extends ConsoleBase {
    public static void main(String[] args) {
        output.println("Calcula a area do rectângulo.");
        BigDecimal valorDaAltura = getValorNumericoAsBigDecimalFromConsole("Valor da altura");
        BigDecimal valorDaBase = getValorNumericoAsBigDecimalFromConsole("Valor da base");
        double area = valorDaAltura.multiply(valorDaBase).doubleValue();

        output.printf("A area do rectângulo com altura (%.2f) e base (%.2f) é de %.2f", valorDaAltura, valorDaBase, area);
    }
}
