package com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models;


import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Jogador {
    @Builder.Default
    private Boolean jogando = true;
    @Builder.Default
    private Boolean pronto = false;
    @Builder.Default
    private Boolean actual = false;
    private Integer posicao;
    private Integer pontosSomados;
    @EqualsAndHashCode.Include
    private Pessoa pessoa;
    @Builder.Default
    private List<Carta> cartas = new ArrayList<>();
    @Builder.Default
    private List<CartaJogador> cartasJogadas = new ArrayList<>();

    @Override
    public String toString() {
        return "Jogador{posicao = " + Objects.toString(posicao, "Indefinida") + "; pessoa: "+ Objects.toString(this.pessoa) +";cartas: " + Objects.toString(this.cartas) + "; cartasJogadas: " + cartasJogadas + "}";
    }
}
