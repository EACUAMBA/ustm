package com.eacuamba.dev.iii_semestre.programacao_ii.aulas_1_2_3.exercicio_6;

import com.eacuamba.dev.utils.ConsoleBase;

import java.time.LocalDate;

public class CalculaIdade extends ConsoleBase {
    public static void main(String[] args) {
        output.println("Calcula a idade da pessoa em anos e semanas a partir do ano de nascimento e ano actual.");
        Integer anoDeNascimento = getValorNumericoAsIntegerFromConsole("Ano de nascimento");
        Integer anoActual = getValorNumericoAsIntegerFromConsole("Ano actual");

        int dias = 0;
        for (int ano = anoDeNascimento; ano <= anoActual; ano++ )
            dias += LocalDate.of(ano, 1, 1).lengthOfYear();

        int anos = anoActual - anoDeNascimento;
        int anoEmSemanas = dias / 7;

        output.printf("%nA sua idade é  %d anos.%n", anos);
        output.printf("%nA sua idade em semanas é %d semanas.%n", anoEmSemanas);


    }
}
