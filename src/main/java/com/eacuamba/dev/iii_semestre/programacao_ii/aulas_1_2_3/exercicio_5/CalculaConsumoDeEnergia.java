package com.eacuamba.dev.iii_semestre.programacao_ii.aulas_1_2_3.exercicio_5;

import com.eacuamba.dev.utils.ConsoleBase;

import java.math.BigDecimal;

public class CalculaConsumoDeEnergia extends ConsoleBase {
    public static void main(String[] args) {
        output.println("Calcula o consumo de energia.");
        BigDecimal salarioMinimo = getValorNumericoAsBigDecimalFromConsole("Salário mínimo");
        BigDecimal quantidadeDeQuilowattsGastos = getValorNumericoAsBigDecimalFromConsole("Quantidade de quilowatts gastos");
        BigDecimal custoDaEnergia = salarioMinimo.divide(BigDecimal.valueOf(5), mathContext);
        BigDecimal valorSerPago = quantidadeDeQuilowattsGastos.multiply(custoDaEnergia);
        BigDecimal custoDaEnergiaComDesconto = custoDaEnergia.multiply(BigDecimal.valueOf(0.15));
        BigDecimal novoCustoDeEnergia = quantidadeDeQuilowattsGastos.multiply(custoDaEnergiaComDesconto);
        output.printf("Com o salário mínimo %.2f, o quilowatt custa %.2f o valor que a família vai pagar é de %.2f e o novo valor com desconto de 15%% é de %.2f", salarioMinimo, custoDaEnergia, valorSerPago, novoCustoDeEnergia);
    }
}
