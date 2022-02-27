package com.eacuamba.dev.aulas_1_2_3.exercicio_3;

import com.eacuamba.dev.utils.ConsoleBase;

public class CalculaIdadeEmAnosParaMinutos extends ConsoleBase {
    public static void main(String[] args) {
        output.println("Calcula a idade em anos para minutos.");
        Integer idadeEmAnos = getValorNumericoAsIntegerFromConsole("Idade em anos");
        //anos * dias * horas * minutos = 2 anos * 365 dias * 24 horas * 60 minutos = numero de minutos que tem 2 anos.
        int idadeEmMinutos = idadeEmAnos * 365 * 24 * 60;
        output.printf("%nA sua idade (%d anos) em minutos é %s minutos.%n", idadeEmAnos, convertIntegerToString(idadeEmMinutos));
    }
}
