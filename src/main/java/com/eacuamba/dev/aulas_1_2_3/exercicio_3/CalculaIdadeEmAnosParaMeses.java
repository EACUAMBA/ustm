package com.eacuamba.dev.aulas_1_2_3.exercicio_3;

import com.eacuamba.dev.utils.ConsoleBase;

public class CalculaIdadeEmAnosParaMeses extends ConsoleBase {
    public static void main(String[] args) {
        output.println("Calcula a idade em anos para meses.");
        Integer idadeEmAnos = getValorNumericoAsIntegerFromConsole("Idade em anos");
        int idadeEmMeses = idadeEmAnos * 12;
        output.printf("%nA sua idade (%d anos) em meses é %d meses.%n", idadeEmAnos, idadeEmMeses);
    }
}
