package com.eacuamba.dev.iii_semestre.programacao_ii.aulas_1_2_3.exercicio_14;

import java.math.BigDecimal;

import com.eacuamba.dev.utils.ConsoleBase;

public class CalculaVolumeDoPrisma extends ConsoleBase {
	public static void main(String[] args) {
		output.println("Calculadora de volume de prisma.");
		BigDecimal comprimento = getValorNumericoAsBigDecimalFromConsole("Comprimento");
		BigDecimal largura = getValorNumericoAsBigDecimalFromConsole("Largura");
		BigDecimal altura = getValorNumericoAsBigDecimalFromConsole("Altura");
		BigDecimal volume = comprimento.multiply(largura).multiply(altura);
		output.printf("O volume de um prisma com base rectângular  e medidas comprimento %.2f, largura %.2f e altura %.2f é %.2f%n", comprimento, largura, altura, volume);
	}
}
