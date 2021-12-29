package com.eacuamba.dev.domain.dto;

import com.eacuamba.dev.domain.model.Localizacao;

import java.math.BigDecimal;

public class LocalizacaoFacturouMais {
    private Localizacao localizacao;
    private BigDecimal valorFacturado;

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public BigDecimal getValorFacturado() {
        return valorFacturado;
    }

    public void setValorFacturado(BigDecimal valorFacturado) {
        this.valorFacturado = valorFacturado;
    }
}
