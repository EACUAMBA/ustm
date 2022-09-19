package com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.domain.model;

import com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.domain.exception.ValorInvalidoException;
import com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.domain.validator.PropriedadeValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class Propriedade implements Comparable<Propriedade> {
    private Long id;
    private TipoPropriedade tipoPropriedade;
    private Localizacao localizacao;
    private Integer numeroQuartos;
    private BigDecimal valor;
    private Integer desconto;
    private BigDecimal valorPago;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPropriedade getTipoPropriedade() {
        return tipoPropriedade;
    }

    public void setTipoPropriedade(TipoPropriedade tipoPropriedade) {
        this.tipoPropriedade = tipoPropriedade;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public Integer getNumeroQuartos() {
        return numeroQuartos;
    }

    public void setNumeroQuartos(Integer numeroQuartos) throws ValorInvalidoException {
        this.numeroQuartos = numeroQuartos;
        PropriedadeValidator.validaNumeroQuartos(this);
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) throws ValorInvalidoException {
        this.valor = valor;
        PropriedadeValidator.validaValor(this);
    }

    public Integer getDesconto() {
        return desconto;
    }

    public void setDesconto(Integer desconto) {
        this.desconto = desconto;
    }

    public Integer getDescontoPadrao() {
        if (this.getTipoPropriedade().equals(TipoPropriedade.FLAT)) {
            if (numeroQuartos == 3)
                return 10;
            else if (numeroQuartos == 2)
                return 5;
        }
        return 0;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) throws ValorInvalidoException {
        this.valorPago = valorPago;
        PropriedadeValidator.validaValorPago(this);
    }

    public BigDecimal getDescontoEmMT() {
        return this.valor.multiply(BigDecimal.valueOf(this.desconto)).divide(BigDecimal.valueOf(100), 2, RoundingMode.CEILING);
    }

    public String imprimir() {
        return "Índice: " + id +
                "\nTipo de propriedade: " + tipoPropriedade.getNome() +
                "\nLocalização: " + localizacao.getDesignacao() +
                "\nNúmero de quartos: " + numeroQuartos +
                "\nValor: " + NumberFormat.getCurrencyInstance(Locale.getDefault()).format(valor) +
                "\nDesconto: " + desconto + "%" +
                "\nValor pago: " + NumberFormat.getCurrencyInstance(Locale.getDefault()).format(valorPago);
    }

    @Override
    public String toString() {
        return "Propriedade{" +
                "id=" + id +
                ", tipoPropriedade=" + tipoPropriedade +
                ", localizacao=" + localizacao +
                ", numeroQuartos=" + numeroQuartos +
                ", valor=" + valor +
                ", desconto=" + desconto +
                ", valorPago=" + valorPago +
                '}';
    }

    @Override
    public int compareTo(Propriedade o) {
        return this.getId().compareTo(o.getId());
    }
}
