package com.eacuamba.dev.aulas_1_2_3.exercicio_18;

import java.math.BigDecimal;

import com.eacuamba.dev.utils.ConsoleBase;

public class CalculaVolumeDeUmaPiramideTriangular extends ConsoleBase{
	public static void main(String[] args) {
		output.println("Calcula o volume de uma pirâmide triangular (tetraedro).");
		BigDecimal areaBase = getValorNumericoAsBigDecimalFromConsole("Area da base");
		BigDecimal altura = getValorNumericoAsBigDecimalFromConsole("Altura");
		BigDecimal volume = areaBase.multiply(altura).divide(BigDecimal.valueOf(3));
		output.printf("O volume de uma pirâmide triangular com area base %.2f e altura %.2f é de %.2f.%n", areaBase, altura, volume);
	}
}
