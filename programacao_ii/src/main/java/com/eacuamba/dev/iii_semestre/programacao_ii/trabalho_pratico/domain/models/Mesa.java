/*
 * Copyright (c) Edilson Alexandre Cuamba - 4 - 6 - 2022
 */

package com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models;

import com.eacuamba.dev.utils.Utils;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Mesa {
    private List<Carta> cartasDisponiveis = new ArrayList<>();
    private List<Carta> cartasIndisponiveis = new ArrayList<>();
    private List<CartaJogador> cartasJogadas = new ArrayList<>();
    private List<CartaJogador> cartasJogadasAoLongoDoJogo = new ArrayList<>();
    private List<Jogador> jogadoresRestantes = new ArrayList<>();
    private List<Jogador> jogadoresVencedores = new ArrayList<>();
    private Jogador jogador;
    private Integer rondas = 1;
    private boolean jogoEmAndamento = false;

    public Mesa() {
        this.cartasDisponiveis.addAll(Arrays.asList(Carta.values()));
        this.baralhar(this.cartasDisponiveis, 10);
    }

    public void proximoJogador(int salto) {
        int indexOf = this.jogadoresRestantes.indexOf(this.jogador);
        int size = this.jogadoresRestantes.size();


        if(salto >= 0) {
            int passos = 1 + salto;
            int posicao = 0;
            for (int i = indexOf; i <= (size - 1); i++) {

                Jogador jogador = this.jogadoresRestantes.get(i);
                if (jogador.getJogando()) {
                    if (posicao == passos) {
                        this.jogadoresRestantes.forEach(jogador1 -> jogador1.setActual(false));
                        this.jogador = jogador;
                        this.jogador.setActual(true);
                        break;
                    } else
                        posicao++;
                }
                if (i == (size-1)) {
                    i = -1;
                }
            }
        }else {
            int posicao = 0;
            for (int i = indexOf; i <= size - 1; i--) {

                Jogador jogador = this.jogadoresRestantes.get(i);
                if (jogador.getJogando()) {
                    if (posicao == salto) {
                        this.jogadoresRestantes.forEach(jogador1 -> jogador1.setActual(false));
                        this.jogador = jogador;
                        this.jogador.setActual(true);
                        break;
                    } else
                        posicao--;
                }

                if (i == (size -1)) {
                    i = size-1;
                }
            }
        }


    }

    public Jogador getProximoJogador() {
        return this.jogador;
    }

    public void baralhar(List<Carta> cartas, int vezes) {
        if (vezes <= 0)
            throw new IllegalArgumentException("O valor de vezes deve ser maior ou igual a 1");

        for (int i = 1; i <= vezes; i++)
            this.baralhar(cartas);
    }

    private void baralhar(List<Carta> cartas) {
        List<Carta> cartasBarralhadas = Utils.baralhar(cartas);
        cartas.clear();
        cartas.addAll(cartasBarralhadas);
    }

    public Jogador escolherPrimeiroJogador(List<Jogador> jogadorList) {
        Jogador first = new LinkedList<>(Utils.baralhar(jogadorList, 10)).getFirst();
        this.jogador = first;
        this.jogador.setActual(true);
        return first;
    }

    public List<Jogador> getJogadoresRestantesJogando() {
        return jogadoresRestantes.stream().filter(Jogador::getJogando).collect(Collectors.toList());
    }

    public void setJogoEmAndamento(boolean state){
        this.jogoEmAndamento = state;
    }
}
