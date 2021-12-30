package com.eacuamba.dev.domain.statistics;

import com.eacuamba.dev.domain.dto.LocalizacaoFacturouMais;
import com.eacuamba.dev.domain.dto.PropriedadeEstatisticaDataTable;
import com.eacuamba.dev.domain.exception.ListaSemDadosException;
import com.eacuamba.dev.domain.exception.ValorInvalidoException;
import com.eacuamba.dev.domain.model.Localizacao;
import com.eacuamba.dev.domain.model.Propriedade;
import com.eacuamba.dev.domain.model.TipoPropriedade;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;
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

    public static List<LocalizacaoFacturouMais> getLocalizacaoFacturouMais(List<Propriedade> propriedadeList, List<Localizacao> localizacaoList) throws ListaSemDadosException {
        if (propriedadeList == null || propriedadeList.isEmpty()) {
            throw new ListaSemDadosException("Não temos nenhuma propriedade registada ainda, impossível processar o pedido, por favor registe pelo menos uma propriedade.");
        }
        List<LocalizacaoFacturouMais> localizacaoFacturouMaisList = new ArrayList<>();
        for (Localizacao localizacao : localizacaoList) {
            double valorTotalFacturado = propriedadeList.stream().filter((p) -> p.getLocalizacao().equals(localizacao)).collect(Collectors.summarizingDouble((p) -> p.getValorPago().doubleValue())).getSum();
            LocalizacaoFacturouMais localizacaoFacturouMais = new LocalizacaoFacturouMais();
            localizacaoFacturouMais.setLocalizacao(localizacao);
            localizacaoFacturouMais.setValorFacturado(BigDecimal.valueOf(valorTotalFacturado));
            localizacaoFacturouMaisList.add(localizacaoFacturouMais);
        }

        localizacaoFacturouMaisList.sort(Comparator.comparing(LocalizacaoFacturouMais::getValorFacturado));

        return localizacaoFacturouMaisList;
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
}
