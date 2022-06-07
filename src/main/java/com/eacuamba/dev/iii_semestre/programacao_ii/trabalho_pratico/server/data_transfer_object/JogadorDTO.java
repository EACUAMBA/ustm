/*
 * Copyright (c) Edilson Alexandre Cuamba - 4 - 6 - 2022
 */

package com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.server.data_transfer_object;

import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models.Carta;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models.CartaJogador;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models.Jogador;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models.Pessoa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class JogadorDTO {
    private PessoaDTO pessoaDTO;
    @Builder.Default
    private Boolean jogando = true;
    @Builder.Default
    private Boolean actual = false;
    @Builder.Default
    private Boolean pronto = false;
    private Integer posicao;
    private Integer pontosSomados;
    @Builder.Default
    private List<Carta> cartas = new ArrayList<>();
    @Builder.Default
    private List<CartaJogador> cartasJogadas = new ArrayList<>();

    public JogadorDTO(Jogador novoJogador) {
        this.pessoaDTO = new PessoaDTO(novoJogador.getPessoa());
        this.jogando = novoJogador.getJogando();
        this.actual = novoJogador.getActual();
        this.pontosSomados = novoJogador.getPontosSomados();
        this.pronto = novoJogador.getPronto();
        this.cartas = novoJogador.getCartas();
        this.posicao = novoJogador.getPosicao();
        this.cartasJogadas = novoJogador.getCartasJogadas();
    }

    @JsonIgnore
    public Jogador getJogador(){
        return Jogador.builder()
                .jogando(jogando)
                .actual(actual)
                .cartasJogadas(cartasJogadas)
                .pontosSomados(pontosSomados)
                .pronto(pronto)
                .posicao(posicao)
                .cartas(cartas)
                .pessoa(Pessoa.builder().nome(this.pessoaDTO.getNome()).build()).build();
    }

    @Override
    public String toString() {
        return Objects.toString(this.posicao).concat(" - ").concat(this.pessoaDTO.getNome());
    }
}
