package com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.domain.dto;


import com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.domain.model.Localizacao;
import com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.utils.FormatterUtils;

import java.math.BigDecimal;

public class LocalizacaoFacturouMais implements Comparable<LocalizacaoFacturouMais>{
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

    @Override
    public int compareTo(LocalizacaoFacturouMais o) {
        return this.valorFacturado.compareTo(o.getValorFacturado());
    }

    public String[] getRow(){
        return new String []{this.localizacao.getDesignacao(), FormatterUtils.converteBigDecimalToString(this.getValorFacturado())};
    }
}
