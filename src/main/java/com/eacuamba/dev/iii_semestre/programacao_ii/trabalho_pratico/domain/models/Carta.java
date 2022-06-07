package com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models;

import lombok.Getter;

@Getter
public enum Carta {
    //Espada
    ESPADA_A("A",  1, Naipe.ESPADA),
    ESPADA_2("2", 10, Naipe.ESPADA),
    ESPADA_3("3",  3, Naipe.ESPADA),
    ESPADA_4("4", 4, Naipe.ESPADA),
    ESPADA_5("5", 5, Naipe.ESPADA),
    ESPADA_6("6", 6, Naipe.ESPADA),
    ESPADA_7("7", 10, Naipe.ESPADA),
    ESPADA_8("8", 50, Naipe.ESPADA),
    ESPADA_9("9", 9, Naipe.ESPADA),
    ESPADA_10("10", 10, Naipe.ESPADA),
    ESPADA_J("J",  10, Naipe.ESPADA),
    ESPADA_Q("Q", 2, Naipe.ESPADA),
    ESPADA_K("K", 4, Naipe.ESPADA),
    
    //Ouros
    OUROS_A("A",  1, Naipe.OUROS),
    OUROS_2("2", 10, Naipe.OUROS),
    OUROS_3("3",  3, Naipe.OUROS),
    OUROS_4("4", 4, Naipe.OUROS),
    OUROS_5("5", 5, Naipe.OUROS),
    OUROS_6("6", 6, Naipe.OUROS),
    OUROS_7("7", 10, Naipe.OUROS),
    OUROS_8("8", 50, Naipe.OUROS),
    OUROS_9("9", 9, Naipe.OUROS),
    OUROS_10("10", 10, Naipe.OUROS),
    OUROS_J("J",  10, Naipe.OUROS),
    OUROS_Q("Q", 2, Naipe.OUROS),
    OUROS_K("K", 4, Naipe.OUROS),

    //Copas
    COPAS_A("A",  1, Naipe.COPAS),
    COPAS_2("2", 10, Naipe.COPAS),
    COPAS_3("3",  3, Naipe.COPAS),
    COPAS_4("4", 4, Naipe.COPAS),
    COPAS_5("5", 5, Naipe.COPAS),
    COPAS_6("6", 6, Naipe.COPAS),
    COPAS_7("7", 10, Naipe.COPAS),
    COPAS_8("8", 50, Naipe.COPAS),
    COPAS_9("9", 9, Naipe.COPAS),
    COPAS_10("10", 10, Naipe.COPAS),
    COPAS_J("J",  10, Naipe.COPAS),
    COPAS_Q("Q", 2, Naipe.COPAS),
    COPAS_K("K", 4, Naipe.COPAS),

    //Paus
    PAUS_A("A",  1, Naipe.PAUS),
    PAUS_2("2", 10, Naipe.PAUS),
    PAUS_3("3",  3, Naipe.PAUS),
    PAUS_4("4", 4, Naipe.PAUS),
    PAUS_5("5", 5, Naipe.PAUS),
    PAUS_6("6", 6, Naipe.PAUS),
    PAUS_7("7", 10, Naipe.PAUS),
    PAUS_8("8", 50, Naipe.PAUS),
    PAUS_9("9", 9, Naipe.PAUS),
    PAUS_10("10", 10, Naipe.PAUS),
    PAUS_J("J",  10, Naipe.PAUS),
    PAUS_Q("Q", 2, Naipe.PAUS),
    PAUS_K("K", 4, Naipe.PAUS),
    ;

    Carta(String designacao, Integer valor, Naipe naipe){
        this.designacao = designacao;
        this.naipe = naipe;
        this.valor = valor;
    }

    private String designacao;
    private Integer valor;
    private Naipe naipe;

    public String getDetalhe(){
        return String.format("Carta: %s, Naipe: %s, Valor: %s", this.designacao, this.naipe.toString(), this.valor);
    }

    @Override
    public String toString() {
        return String.format("Carta{Designação: %s, Naipe: %s}", this.designacao, this.naipe);
    }
}
