package com.eacuamba.dev.aulas_1_2_3.exercicio_3;

import com.eacuamba.dev.utils.ConsoleBase;

public class CalculaIdadeEmAnosParaHoras extends ConsoleBase {
    public static void main(String[] args) {
        output.println("Calcula a idade em anos para horas.");
        Integer idadeEmAnos = getValorNumericoAsIntegerFromConsole("Idade em anos");
        int idadeEmHoras = idadeEmAnos * 365 * 24;
        output.printf("%nA sua idade (%d anos) em horas é %s horas.%n", idadeEmAnos, convertIntegerToString(idadeEmHoras));
    }
}
