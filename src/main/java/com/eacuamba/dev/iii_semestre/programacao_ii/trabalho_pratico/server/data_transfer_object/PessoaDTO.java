/*
 * Copyright (c) Edilson Alexandre Cuamba - 4 - 6 - 2022
 */

package com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.server.data_transfer_object;

import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models.Pessoa;
import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PessoaDTO {
    private String nome;

    public PessoaDTO(Pessoa pessoa) {
        this.nome = pessoa.getNome();
    }
}
