package com.eacuamba.dev.aulas_1_2_3.exercicio_3;

import com.eacuamba.dev.utils.ConsoleBase;

public class CalculaIdadeEmAnosParaDias extends ConsoleBase {
    public static void main(String[] args) {
        output.println("Calcula a idade em anos para dias.");
        Integer idadeEmAnos = getValorNumericoAsIntegerFromConsole("Idade em anos");
        int idadeEmDias = idadeEmAnos * 365 ;
        output.printf("%nA sua idade (%d anos) em dias é %d dias.%n", idadeEmAnos, idadeEmDias);
    }
}
