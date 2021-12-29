package com.eacuamba.dev.domain.dto;

import com.eacuamba.dev.domain.model.Localizacao;
import com.eacuamba.dev.domain.model.Propriedade;
import com.eacuamba.dev.domain.model.TipoPropriedade;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class PropriedadeEstatisticaDataTable {
    private Propriedade propriedadeFather;
    private Localizacao localizacao;
    private TipoPropriedade tipoPropriedade;
    private Integer numeroQuartos;
    private BigDecimal valor;
    private BigDecimal desconto;
    private BigDecimal valorPago;

    public PropriedadeEstatisticaDataTable() {
    }

    public PropriedadeEstatisticaDataTable(Propriedade propriedadeFather, Localizacao localizacao, TipoPropriedade tipoPropriedade, Integer numeroQuartos, BigDecimal valor, BigDecimal desconto, BigDecimal valorPago) {
        this.propriedadeFather = propriedadeFather;
        this.localizacao = localizacao;
        this.tipoPropriedade = tipoPropriedade;
        this.numeroQuartos = numeroQuartos;
        this.valor = valor;
        this.desconto = desconto;
        this.valorPago = valorPago;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public TipoPropriedade getTipoPropriedade() {
        return tipoPropriedade;
    }

    public void setTipoPropriedade(TipoPropriedade tipoPropriedade) {
        this.tipoPropriedade = tipoPropriedade;
    }

    public Integer getNumeroQuartos() {
        return numeroQuartos;
    }

    public void setNumeroQuartos(Integer numeroQuartos) {
        this.numeroQuartos = numeroQuartos;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public String[] getDataRow(){
        return new String[]{
                propriedadeFather.getId().toString(),
                this.localizacao.getDesignacao(),
                this.tipoPropriedade.getNome(),
                this.numeroQuartos.toString(),
                NumberFormat.getCurrencyInstance(Locale.getDefault()).format(this.valor),
                NumberFormat.getCurrencyInstance(Locale.getDefault()).format(this.propriedadeFather.getDescontoEmMT()),
                NumberFormat.getCurrencyInstance(Locale.getDefault()).format(this.valorPago)
        };
    }
}
