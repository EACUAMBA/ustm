package com.eacuamba.dev.domain.model;

public class Localizacao {
    private Long id;
    private String designacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public String imprimir() {
        return
                 id + " - " + designacao;
    }

    @Override
    public String toString() {
        return "Localizacao{" +
                "id=" + id +
                ", designacao='" + designacao + '\'' +
                '}';
    }
}
