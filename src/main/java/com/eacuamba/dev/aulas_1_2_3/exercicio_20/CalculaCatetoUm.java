package com.eacuamba.dev.aulas_1_2_3.exercicio_20;

import com.eacuamba.dev.utils.ConsoleBase;

import java.math.BigDecimal;

public class CalculaCatetoUm extends ConsoleBase {
    public static void main(String[] args) {
        output.println("Calcula o valor do cateto um usando hipotenusa e cateto dois.");
        BigDecimal catetoDois = getValorNumericoAsBigDecimalFromConsole("Cateto dois");
        BigDecimal hipotenusa = getValorNumericoAsBigDecimalFromConsole("Hipotenusa");

        double catetoUm = Math.sqrt(hipotenusa.pow(2, mathContext).subtract(catetoDois.pow(2, mathContext)).doubleValue());
        output.printf("%nO valor do cateto um calculando usando valores de cateto dois %.2f e hipotenusa %.2f é %.2f.%n", catetoDois, hipotenusa, catetoUm);
    }
}
