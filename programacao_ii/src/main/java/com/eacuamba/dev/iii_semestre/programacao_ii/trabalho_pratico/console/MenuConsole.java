package com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.console;

import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models.Carta;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models.Jogador;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.service.Croupier;
import com.eacuamba.dev.utils.ConsoleBase;

import java.util.*;

public class MenuConsole extends ConsoleBase{
    public static  void main(String... args){
        final Croupier  croupier= Croupier.getInstance();
        short alternativa = 0;
        final String cardapio = "" +
                "0 - Sair\n"+
                "1 - Cadastrar Jogador\n"+
                "2 - Estado da mesa\n"+
                "3 - Jogar\n"+
                "\n"
                ;

        do{
            System.out.println();
            System.out.println(cardapio);
            alternativa = getValorNumericoAsShortFromConsole("Opção escolhida do cardápio");
            System.out.println();
            switch (alternativa){
                case 0: {
                    System.out.println("Deseja realmente sair?\n1 - Sim\n2 - Nâo");

                    do {
                        alternativa = getValorNumericoAsShortFromConsole("Resposta");
                    }while(alternativa != 1 && alternativa != 2);
                    if(alternativa == 1) alternativa = -1;
                    else alternativa = 0;
                    break;
                }
                case 1: {
                    short cadastrarMais = 0;
                    do {
                        Jogador jogador = JogadorConsole.criarJogador();
                        croupier.adicionarNovoJogador(jogador);

                        System.out.println("\nDeseja cadastrar mais?\n0 - Não\n1 - Sim");
                        cadastrarMais = getValorNumericoAsShortFromConsole("Resposata");
                    }while (cadastrarMais != 0);
                    croupier.iniciarJogo();
                    break;
                }
                case 2: {
                    if(croupier.getJogadores().isEmpty()){
                        System.out.println("Precisa de pelo menos um jogador para usar outras funcionalidades!");
                        break;
                    }
                    System.out.println(croupier.getJogadores());
                    System.out.println("\nProximo jogador: " + croupier.getJogadorActual());

                    break;
                }
                case 3: {

                }
                default:{

                }
            }
        }while(alternativa != -1);
    }
}
