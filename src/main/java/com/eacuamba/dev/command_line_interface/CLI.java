package com.eacuamba.dev.command_line_interface;

import com.eacuamba.dev.command_line_interface.utils.TecladoScannerUtils;
import com.eacuamba.dev.config.ApplicationConfig;
import com.eacuamba.dev.domain.exception.ValorInvalidoException;
import com.eacuamba.dev.domain.model.Localizacao;
import com.eacuamba.dev.domain.model.Propriedade;
import com.eacuamba.dev.domain.model.TipoPropriedade;
import com.eacuamba.dev.domain.repository.LocalizacaoRepositoryFAKE;
import com.eacuamba.dev.domain.repository.PropriedadeRepositoryFAKE;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static com.eacuamba.dev.config.ApplicationConfig.imprimeDadosDoDesenvolvedor;
import static com.eacuamba.dev.config.ApplicationConfig.terminarExecucao;

public class CLI {
    private static final PropriedadeRepositoryFAKE propriedadeRepositoryFAKE = PropriedadeRepositoryFAKE.getInstance();
    private static final LocalizacaoRepositoryFAKE localizacaoRepositoryFAKE = LocalizacaoRepositoryFAKE.getInstance();

    public static void start() {
        System.out.println("Bem vindo ao sistema de Gestão de Propriedades");
        System.out.println("Trabalho Prático de Programação I");
        System.out.println();

        int proximoPasso;
        boolean naoTerminarExecucao = true;

        do {
            imprimeMenu();
            proximoPasso = TecladoScannerUtils.receberValorInteiroDoUtilizador();

            switch (proximoPasso) {
                case -2: {
                    break;
                }
                case -1: {
                    naoTerminarExecucao = false;
                    break;
                }
                case 0: {
                    naoTerminarExecucao = false;
                    terminarExecucao();
                    break;
                }
                case 1: {
                    registarPropriedade();
                    break;
                }
                case 2: {
                    StatisticCLI.start();
                    break;
                }
                case 3: {
                    imprimeDadosDoDesenvolvedor();
                    break;
                }
                default: {
                    System.out.println(ApplicationConfig.getConsoleErrorTextBold().format("Erro: Valor introduzido não corresponde a nenhuma das opções na lista."));
                    break;
                }
            }
        } while (naoTerminarExecucao);
    }

    private static void registarPropriedade() {
        System.out.println("Registo de Propriedade");
        System.out.println();
        Propriedade propriedade = new Propriedade();

        Optional<Localizacao> optionalLocalizacao = selecionaLocalizacao();
        if (optionalLocalizacao.isPresent()) {
            propriedade.setLocalizacao(optionalLocalizacao.get());
        } else {
            return;
        }

        Optional<TipoPropriedade> optionalTipoPropriedade = selecionaTipoPropriedade();
        if (optionalTipoPropriedade.isPresent()) {
            propriedade.setTipoPropriedade(optionalTipoPropriedade.get());
        } else {
            return;
        }

        boolean numeroQuartosNaoValido = true;
        do {
            System.out.println("Introduza o número de quartos da casa.");
            int numeroQuartos = TecladoScannerUtils.receberValorInteiroDoUtilizador();
            try {
                propriedade.setNumeroQuartos(numeroQuartos);
                numeroQuartosNaoValido = false;
            } catch (ValorInvalidoException e) {
                System.out.println(ApplicationConfig.getConsoleErrorTextBold().format(e.getMessage()));
                System.out.println();
            }
        } while (numeroQuartosNaoValido);

        boolean valorNaoValidado = true;
        do {
            System.out.println("Introduza o preço da propriedade (O preço que esta na montra)");
            double valor = TecladoScannerUtils.receberValorDecimalDoUtilizador();
            try {
                propriedade.setValor(BigDecimal.valueOf(valor));
                valorNaoValidado = false;
            } catch (ValorInvalidoException e) {
                System.out.println(ApplicationConfig.getConsoleErrorTextBold().format(e.getMessage()));
                System.out.println();
            }
        } while (valorNaoValidado);

        boolean decontoNaoValidado = true;
        do {
            System.out.println("Introduza o desconto da propriedade em percentagem (0 - 100, valores inteiros), caso não introduzir o sistema assumira os valores padrão.");
            int desconto = TecladoScannerUtils.receberValorInteiroDoUtilizadorSemImpressaoErro();

            if (desconto == -2) {
                desconto = propriedade.getDescontoPadrao();
                System.out.println(ApplicationConfig.getConsoleErrorTextBold().format("Você introduziu um valor ínvalido ou nenhum valor o sistema vai assumir o valor padrão de desconto."));
                System.out.println(ApplicationConfig.getConsoleErrorTextBold().format(String.format("Valor de desconto assumido é %s%s.", desconto, "%")));
                System.out.println();
            } else if (desconto < 0) {
                System.out.println(ApplicationConfig.getConsoleErrorTextBold().format("Valor do desconto invalido, introduza um valor superior ou igual a 0 \"desconto >= 0\"."));
                System.out.println();
                continue;
            }
            propriedade.setDesconto(desconto);
            decontoNaoValidado = false;
        } while (decontoNaoValidado);

        boolean valorPagoNaoValidado = true;
        do {
            System.out.println("Introduza o valor que o cliente pagou (o valor que ele depositou na conta da empresa).\nSe não introduzir o sistema vai assumir o preço menos o valor do desconto.");
            double valor = TecladoScannerUtils.receberValorDecimalDoUtilizadorSemImpressaoErro();
            try {
                if(valor == -2){
                    valor = propriedade.getValor().subtract(propriedade.getDescontoEmMT()).doubleValue();
                }
                propriedade.setValorPago(BigDecimal.valueOf(valor));
                valorPagoNaoValidado = false;
            } catch (ValorInvalidoException e) {
                System.out.println(ApplicationConfig.getConsoleErrorTextBold().format(e.getMessage()));
                System.out.println();
            }
        } while (valorPagoNaoValidado);


        try {
            Optional<Propriedade> optionalPropriedade = propriedadeRepositoryFAKE.save(propriedade);
            if (optionalPropriedade.isPresent()) {
                propriedade = optionalPropriedade.get();
                System.out.println("Propriedade: ");
                System.out.println(ApplicationConfig.getConsoleSuccessTextBold().format(propriedade.imprimir()));
                System.out.println("Propriedade inserida com sucesso.");
                System.out.println();
            }
        } catch (ValorInvalidoException e) {
            System.out.println(ApplicationConfig.getConsoleErrorTextBold().format(e.getMessage()));
            System.out.println();
        }
    }

    private static Optional<Localizacao> selecionaLocalizacao() {
        boolean naoSelecionado = true;
        Optional<Localizacao> localizacaoSelecionada = Optional.empty();
        do {
            System.out.println("Localizações disponiveis:");
            localizacaoRepositoryFAKE.findAll().forEach(l -> System.out.println(l.imprimir()));
            System.out.println("0 - Cancelar Registro");
            System.out.println();

            int valorSelecionado = TecladoScannerUtils.receberValorInteiroDoUtilizador();
            for (Localizacao localizacao : localizacaoRepositoryFAKE.findAll()) {
                localizacaoSelecionada = localizacaoRepositoryFAKE.findById((long) valorSelecionado);
            }
            if (valorSelecionado == 0) {
                naoSelecionado = false;
            } else if (!localizacaoSelecionada.isPresent()) {
                System.out.println(ApplicationConfig.getConsoleErrorTextBold().format("Erro: Valor introduzido não corresponde a nenhuma das opções na lista."));
            } else {
                naoSelecionado = false;
            }
        } while (naoSelecionado);
        return localizacaoSelecionada;
    }

    public static Optional<TipoPropriedade> selecionaTipoPropriedade() {
        boolean naoSelecionado = true;
        Optional<TipoPropriedade> optionalTipoPropriedadeSelecionado = Optional.empty();
        do {
            System.out.println("Tipos de Propriedades");
            Arrays.stream(TipoPropriedade.values()).forEach((tp) -> System.out.printf("%d - %s%n", tp.getNumero() - 1, tp.getNome()));
            System.out.println("0 - Cancelar Registro");
            System.out.println();

            int valorSelecionado = TecladoScannerUtils.receberValorInteiroDoUtilizador();
            switch (valorSelecionado) {
                case 0: {
                    naoSelecionado = false;
                    break;
                }
                case 1: {
                    optionalTipoPropriedadeSelecionado = Optional.of(TipoPropriedade.VIVENDA);
                    naoSelecionado = false;
                    break;
                }

                case 2: {
                    optionalTipoPropriedadeSelecionado = Optional.of(TipoPropriedade.FLAT);

                    naoSelecionado = false;
                    break;
                }
                default: {
                    System.out.println(ApplicationConfig.getConsoleErrorTextBold().format("Erro: Valor introduzido não corresponde a nenhuma das opções na lista."));
                    System.out.println();
                }
            }
        } while (naoSelecionado);
        return optionalTipoPropriedadeSelecionado;
    }

    private static void imprimeMenu() {
        System.out.println("Menu Principal");
        Arrays.stream(MainMenuItems.values()).map((MainMenuItems::getItemName)).forEach(System.out::println);
        System.out.println();
    }

}
