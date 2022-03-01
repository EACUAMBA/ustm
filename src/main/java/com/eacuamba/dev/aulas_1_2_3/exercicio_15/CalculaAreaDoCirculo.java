package com.eacuamba.dev.aulas_1_2_3.exercicio_15;

import java.math.BigDecimal;

import com.eacuamba.dev.utils.ConsoleBase;

public class CalculaAreaDoCirculo extends ConsoleBase{
	public static void main(String[] args) {
		output.println("Calcula  área do circulo.");
		BigDecimal raio = getValorNumericoAsBigDecimalFromConsole("Raio");
		BigDecimal area = raio.pow(2, mathContext).multiply(BigDecimal.valueOf(Math.PI));
		output.printf("%nA área do circulo com raio %.2f é %.2f.%n", raio, area);
	}
}
