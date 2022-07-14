/*
 * Copyright (c) Edilson Alexandre Cuamba - 4 - 6 - 2022
 */

package com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.server.data_transfer_object;

import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.exceptions.ProximaCartaDeveSerNumeroDois;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models.Carta;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models.CartaJogador;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models.Jogador;
import com.eacuamba.dev.utils.ObjectMapperUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class EstadoMesaDTO {
    private String mensagem;

    @Builder.Default
    private boolean isJogoGoing = false;
    @Builder.Default
    private List<Carta> cartasDisponiveis = new ArrayList<>();
    @Builder.Default
    private List<Carta> todasCartas = new ArrayList<>();
    @Builder.Default
    private List<CartaJogador> cartasJogadas = new ArrayList<>();


    @Builder.Default
    private List<JogadorDTO> jogadorDTOList = new ArrayList<>();

    private JogadorDTO jogadorDTOActual;

    @Builder.Default
    private boolean isPedidoInicioJogo = false;
    @Builder.Default
    private boolean pedidoDeInicioJogo = false;

    @Builder.Default
    private boolean isJogarCarta = false;
    private Carta cartaJogada;

    @Builder.Default
    private boolean isPedidoCarta = false;

    private Integer cartasAPedir;

    @Builder.Default
    private boolean isBroadcastMessage = false;
    private String broadcastMessage;

    @Builder.Default
    private boolean isRegistarJogador = false;

    @Builder.Default
    private boolean pediuCarta = false;
    private JogadorDTO quemPediuCarta;

    private ProximaCartaDeveSerNumeroDois proximaCartaDeveSerNumeroDois;

    private JogadorDTO jogadorDTO;

    public EstadoMesaDTO reset(){
        isPedidoInicioJogo = false;
        isBroadcastMessage = false;
        isJogarCarta = false;
        isPedidoCarta = false;
        isRegistarJogador = false;
        return this;
    }

    @JsonIgnore
    public String getJSON() throws JsonProcessingException {
        return ObjectMapperUtil.getObjectMapper().writeValueAsString(this);
    }
}
