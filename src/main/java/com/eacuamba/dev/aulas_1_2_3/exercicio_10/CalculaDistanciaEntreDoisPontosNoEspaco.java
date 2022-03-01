package com.eacuamba.dev.aulas_1_2_3.exercicio_10;

import java.math.BigDecimal;

import com.eacuamba.dev.utils.ConsoleBase;

public class CalculaDistanciaEntreDoisPontosNoEspaco extends ConsoleBase {
	public static void main(String[] args) {
		output.println("Calcula distância entre dois pontos no espaço.");
		BigDecimal pontoXa = getValorNumericoAsBigDecimalFromConsole("Ponto Xa");
		BigDecimal pontoXb = getValorNumericoAsBigDecimalFromConsole("Ponto Xb");
		BigDecimal pontoVa = getValorNumericoAsBigDecimalFromConsole("Ponto Va");
		BigDecimal pontoVb = getValorNumericoAsBigDecimalFromConsole("Ponto Vb");
		
		double resultado = Math.sqrt(Math.pow(pontoXa.subtract(pontoXb).doubleValue(), 2) + Math.pow(pontoVa.subtract(pontoVb).doubleValue(), 2));
		output.printf("A distância entre os pontos Xa %.2f, Xb %.2f, Va %.2f e Vb %.2f é de %.2f", pontoXa, pontoXb, pontoVa, pontoVb, resultado);
	}
}
