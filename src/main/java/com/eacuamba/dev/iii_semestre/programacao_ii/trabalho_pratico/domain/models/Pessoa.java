package com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {
    @EqualsAndHashCode.Include
    private String nome;

    @Override
    public String toString() {
        return this.nome;
    }
}
