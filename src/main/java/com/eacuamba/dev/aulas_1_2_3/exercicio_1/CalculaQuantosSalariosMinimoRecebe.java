package com.eacuamba.dev.aulas_1_2_3.exercicio_1;

import com.eacuamba.dev.utils.ConsoleBase;

import java.math.BigDecimal;

public class CalculaQuantosSalariosMinimoRecebe extends ConsoleBase {
    public static void main(String[] args) {
        output.println("Calcula quantos salários mínimo recebe usando o salário principal.");
        BigDecimal salario = getValorNumericoAsBigDecimalFromConsole("Salário");
        BigDecimal salarioMinimo = getValorNumericoAsBigDecimalFromConsole("Salário mínimo");
        BigDecimal quantidadeSalariosMinimo = salario.divide(salarioMinimo, mathContext);
        output.printf("A quantidade de salários mínimos que o funcionário com salário de %s sabendo que o salário mínimo é %s recebe é %d", convertBigDecimalToStringConcurrency(salario), convertBigDecimalToStringConcurrency(salarioMinimo), quantidadeSalariosMinimo.intValue());
    }
}
