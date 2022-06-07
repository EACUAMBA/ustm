/*
 * Copyright (c) Edilson Alexandre Cuamba - 4 - 6 - 2022
 */

package com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.exceptions;

import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models.Carta;
import lombok.Getter;

@Getter
public class ProximaCartaDeveSerNumeroDois extends Exception {
    private Carta anteriorCarta;
    private Carta proximaCarta;
    private int somatorioDasCartasDeValorDoisJogadasAteAgora;
    public ProximaCartaDeveSerNumeroDois(String message, Carta anteriorCarta, Carta proximaCarta, int somatorioDasCartasDeValorDoisJogadasAteAgora){
        super(message);
        this.anteriorCarta = anteriorCarta;
        this.proximaCarta = proximaCarta;
        this.somatorioDasCartasDeValorDoisJogadasAteAgora = somatorioDasCartasDeValorDoisJogadasAteAgora;
    }
}
