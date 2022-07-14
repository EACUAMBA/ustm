package com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models;

public enum Naipe {
    ESPADA("Espada"),
    OUROS("Ouros"),
    COPAS("Copas"),
    PAUS("Paus"),
    ;

    Naipe(String designacao){
        this.designacao = designacao;
    }

    private String designacao;

    @Override
    public String toString() {
        return this.designacao;
    }
}
