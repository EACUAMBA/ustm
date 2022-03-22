package com.eacuamba.dev.aulas_19_20_21.exercicios.arraybidimensional;

import com.eacuamba.dev.utils.ConsoleBase;

public class Somatorios extends ConsoleBase {
	public static void main(String[] args) {
		int linhas = 2;
		int colunas = 2;
		double[][] valores = new double[linhas][colunas];
		double somatorioDeTodosElementos = 0;
		double[] somatorioDosElementosPorColuna = new double[linhas];
		double[] somatorioDosElementosPorLinha= new double[colunas];
		
		for(int linha = 0; linha < valores.length; linha++) {
			for(int coluna = 0; coluna < valores[linha].length; coluna++) {
				valores[linha][coluna] = getValorNumericoAsDoubleFromConsole(String.format("Valor da celula[%d][%d]", linha, coluna));
			}
		}
		
		for(int linha = 0; linha < valores.length; linha++) {
			for(int coluna = 0; coluna < valores[linha].length; coluna++) {
				somatorioDeTodosElementos += valores[linha][coluna];
			}
		}
		
		//Somatorio por coluna
		for(int coluna = 0; coluna < valores.length; coluna++) {
			for(int linha = 0; linha < valores.length; linha++) {
				somatorioDosElementosPorColuna[coluna] += + valores[linha][coluna]; 
			}
		}
		
		for(int linha = 0; linha < valores.length; linha++) {
			for(int coluna = 0; coluna < valores[linha].length; coluna++) {
				somatorioDosElementosPorLinha[linha] += valores[linha][coluna];
			}
		}
		
		
		System.out.printf("O somatorio é igual a %.2f%nSoma dos elementos por coluna deu:%n", somatorioDeTodosElementos);
		for(int coluna = 0; coluna < somatorioDosElementosPorColuna.length; coluna++) {
			System.out.printf("Coluna[%d]: %.2f%n", coluna, somatorioDosElementosPorColuna[coluna]);
		}
		
		double linhaMaiorSomatorioValor = somatorioDosElementosPorLinha[0];
		int linhaMaiorSomatorioIndice = -1;
		
		for(int linha = 0; linha < valores.length; linha++) {
			if(somatorioDosElementosPorLinha[linha] <\               linhaMaiorSomatorioValor) {
				linhaMaiorSomatorioValor = somatorioDosElementosPorLinha[linha];
				linhaMaiorSomatorioIndice = linha;
			}
		}
		
		System.out.printf("%nA linha com maior somatorio é linha[%d] com o somatorio: %.2f", linhaMaiorSomatorioIndice, linhaMaiorSomatorioValor);
		
		
		
		
	}
}
