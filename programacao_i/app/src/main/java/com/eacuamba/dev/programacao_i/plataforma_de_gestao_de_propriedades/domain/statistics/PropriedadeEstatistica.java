package com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.domain.statistics;


import com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.domain.dto.LocalizacaoFacturouMais;
import com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.domain.dto.PropriedadeEstatisticaDataTable;
import com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.domain.exception.ListaSemDadosException;
import com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.domain.exception.ValorInvalidoException;
import com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.domain.model.Localizacao;
import com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.domain.model.Propriedade;
import com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.domain.model.TipoPropriedade;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class PropriedadeEstatistica {
    public static Long getQuantidadeTotalVendida(List<Propriedade> propriedadeList) {
        return (long) propriedadeList.size();
    }

    public static Long getQuantidadeVendidaByTipoPropriedade(TipoPropriedade tipoPropriedade, List<Propriedade> propriedadeList) throws ValorInvalidoException {
        if (tipoPropriedade == null) {
            throw new ValorInvalidoException("Impossível obter valor total recebido pela venda de propriedades de um tipo sem informar esse tipo de propriedade.");
        }
        List<Propriedade> propriedadesDoTipoSelecionado = propriedadeList.stream().filter((p) -> p.getTipoPropriedade().equals(tipoPropriedade)).collect(Collectors.toList());
        return getQuantidadeTotalVendida(propriedadesDoTipoSelecionado);
    }

    public static BigDecimal getValorTotalRecebido(List<Propriedade> propriedadeList) throws ListaSemDadosException {
        if (propriedadeList == null || propriedadeList.isEmpty()) {
            throw new ListaSemDadosException("Não temos nenhuma propriedade registada ainda, impossível processar o pedido, por favor registe pelo menos uma propriedade.");
        }

        BigDecimal valorTotalRecebido = new BigDecimal(0);
        for (Propriedade propriedade : propriedadeList) {
            valorTotalRecebido = valorTotalRecebido.add(propriedade.getValorPago());
        }
        return valorTotalRecebido;
    }

    public static BigDecimal getValorTotalRecebidoByTipoPropriedade(TipoPropriedade tipoPropriedade, List<Propriedade> propriedadeList) throws ListaSemDadosException, ValorInvalidoException {
        if (propriedadeList == null || propriedadeList.isEmpty()) {
            throw new ListaSemDadosException("Não temos nenhuma propriedade registada ainda, impossível processar o pedido, por favor registe pelo menos uma propriedade.");
        }
        if (tipoPropriedade == null) {
            throw new ValorInvalidoException("Impossível obter valor total recebido pela venda de propriedades de um tipo sem informar esse tipo de propriedade.");
        }

        List<Propriedade> propriedadesDoTipoSelecionado = propriedadeList.stream().filter((p) -> p.getTipoPropriedade().equals(tipoPropriedade)).collect(Collectors.toList());
        return getValorTotalRecebido(propriedadesDoTipoSelecionado);
    }

    public static String[][] getDadosEmTabela(List<Propriedade> propriedadeList) {
        return propriedadeList.stream()
                .sorted()
                .map((p) -> new PropriedadeEstatisticaDataTable(p, p.getLocalizacao(), p.getTipoPropriedade(), p.getNumeroQuartos(), p.getValor(), p.getDescontoEmMT(), p.getValorPago()))
                .map(PropriedadeEstatisticaDataTable::getDataRow)
                .toArray(String[][]::new);
    }

    public static String[] getRodapeParaTabela(List<Propriedade> propriedadeList) {

        Double valor = propriedadeList.stream()
                .map(Propriedade::getValor)
                .map(BigDecimal::doubleValue)
                .collect(Collectors.summarizingDouble(value -> value))
                .getSum();

        Double desconto = propriedadeList.stream()
                .map(Propriedade::getDescontoEmMT)
                .map(BigDecimal::doubleValue)
                .collect(Collectors.summarizingDouble(Double::doubleValue))
                .getSum();

        Double valorPago = propriedadeList.stream()
                .map(Propriedade::getValorPago)
                .map(BigDecimal::doubleValue)
                .collect(Collectors.summarizingDouble(Double::doubleValue))
                .getSum();

        return new String[]
                {
                        "",
                        "",
                        "",
                        "Totais:",
                        NumberFormat.getCurrencyInstance(Locale.getDefault()).format(valor),
                        NumberFormat.getCurrencyInstance(Locale.getDefault()).format(desconto),
                        NumberFormat.getCurrencyInstance(Locale.getDefault()).format(valorPago)
                };
    }

    public static BigDecimal getLucroTendoEmContaDespesas(List<Propriedade> propriedadeList, float percentagemDasDespesas) throws ListaSemDadosException {
        BigDecimal valorTotalRecebido = getValorTotalRecebido(propriedadeList);
        return valorTotalRecebido.subtract(valorTotalRecebido.multiply(BigDecimal.valueOf(percentagemDasDespesas)));
    }

    public static List<LocalizacaoFacturouMais> getLocalizacaoFacturouMais(List<Propriedade> propriedadeList, List<Localizacao> localizacaoList) throws ListaSemDadosException {
        if (propriedadeList == null || propriedadeList.isEmpty()) {
            throw new ListaSemDadosException("Não temos nenhuma propriedade registada ainda, impossível processar o pedido, por favor registe pelo menos uma propriedade.");
        }
        if (localizacaoList == null || localizacaoList.isEmpty()) {
            throw new ListaSemDadosException("Não temos nenhuma localização registada ainda, impossível processar o pedido, por favor registe pelo menos uma localização.");
        }

        List<LocalizacaoFacturouMais> localizacaoFacturouMaisList = new ArrayList<>();
        for (Localizacao localizacao : localizacaoList) {
            for (Propriedade propriedade : propriedadeList) {
                if (propriedade.getLocalizacao().equals(localizacao)) {
                    LocalizacaoFacturouMais localizacaoFacturouMais = new LocalizacaoFacturouMais();
                    localizacaoFacturouMais.setLocalizacao(localizacao);
                    if (localizacaoFacturouMais.getValorFacturado() == null)
                        localizacaoFacturouMais.setValorFacturado(propriedade.getValorPago());
                    else
                        localizacaoFacturouMais.setValorFacturado(localizacaoFacturouMais.getValorFacturado().add(propriedade.getValorPago()));
                    localizacaoFacturouMaisList.add(localizacaoFacturouMais);
                }
            }
        }
        if(propriedadeList.size() > 1){
            localizacaoFacturouMaisList = localizacaoFacturouMaisList.stream().sorted(new Comparator<LocalizacaoFacturouMais>() {
                @Override
                public int compare(LocalizacaoFacturouMais o1, LocalizacaoFacturouMais o2) {
                    return o2.getValorFacturado().compareTo(o1.getValorFacturado());
                }
            }).collect(Collectors.toList());
        }

        return localizacaoFacturouMaisList;
    }

    public static BigDecimal getTotalRecebidoIncluindoDescontos(List<Propriedade> propriedadeList) throws ListaSemDadosException {
        if (propriedadeList == null || propriedadeList.isEmpty()) {
            throw new ListaSemDadosException("Não temos nenhuma propriedade registada ainda, impossível processar o pedido, por favor registe pelo menos uma propriedade.");
        }

        double valorTotal = propriedadeList.stream()
                .map(Propriedade::getValor)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();

        return BigDecimal.valueOf(valorTotal);
    }

    public static BigDecimal getValorTotalDescontos(List<Propriedade> propriedadeList) throws ListaSemDadosException {
        if (propriedadeList == null || propriedadeList.isEmpty()) {
            throw new ListaSemDadosException("Não temos nenhuma propriedade registada ainda, impossível processar o pedido, por favor registe pelo menos uma propriedade.");
        }

        BigDecimal valorTotalDescontos = new BigDecimal(0);
        for (Propriedade propriedade : propriedadeList) {
            valorTotalDescontos = valorTotalDescontos.add(propriedade.getDescontoEmMT());
        }
        return valorTotalDescontos;
    }
}
