package com.eacuamba.dev.command_line_interface;

import com.eacuamba.dev.command_line_interface.utils.ConsoleUtils;
import com.eacuamba.dev.config.ApplicationConfig;
import com.eacuamba.dev.domain.dto.LocalizacaoFacturouMais;
import com.eacuamba.dev.domain.exception.ListaSemDadosException;
import com.eacuamba.dev.domain.exception.ValorInvalidoException;
import com.eacuamba.dev.domain.model.TipoPropriedade;
import com.eacuamba.dev.domain.repository.LocalizacaoRepositoryFAKE;
import com.eacuamba.dev.domain.repository.PropriedadeRepositoryFAKE;
import com.eacuamba.dev.domain.statistics.PropriedadeEstatistica;
import com.eacuamba.dev.utils.FormatterUtils;
import com.github.freva.asciitable.AsciiTable;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;

import static com.eacuamba.dev.config.ApplicationConfig.imprimeDadosDoDesenvolvedor;
import static com.eacuamba.dev.config.ApplicationConfig.terminarExecucao;

public class StatisticCLI {
    private static final PropriedadeRepositoryFAKE propriedadeRepositoryFAKE = PropriedadeRepositoryFAKE.getInstance();
    private static final LocalizacaoRepositoryFAKE localizacaoRepositoryFAKE = LocalizacaoRepositoryFAKE.getInstance();

    public static void start() {
        System.out.println("Bem vindo a area de Estatisticas");
        System.out.println();


        int proximoPasso;
        boolean naoTerminarExecucao = true;


        do {
            imprimeMenu();
            proximoPasso = ConsoleUtils.receberValorInteiroDoUtilizador();

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
                    calcularQuantidadesVendidas();
                    break;
                }
                case 2: {
                    calcularTotalRecebido();
                    break;
                }
                case 3: {
                    calcularLocalFacturouMais();
                    break;
                }
                case 4: {
                    calcularTotalRecebidoIncluindoDescontos();
                    break;
                }
                case 5: {
                    calcularLucroDaEmpresaTendoEmContaAsDespesas();
                    break;
                }
                case 6: {
                    calcularTotalDescontos();
                    break;
                }
                case 7: {
                    imprimirDados();
                    break;
                }
                case 10: {
                    return;
                }
                default: {
                    System.out.println(ConsoleUtils.getConsoleErrorTextBold().format("Erro: Valor introduzido não corresponde a nenhuma das opções na lista."));
                    break;
                }
            }
        } while (naoTerminarExecucao);

    }

    private static void calcularTotalDescontos() {
        System.out.println("Calculando totais dos descontos.");
        try {
            BigDecimal valor = PropriedadeEstatistica.getValorTotalDescontos(propriedadeRepositoryFAKE.findAll());
            System.out.printf("O valor total de desconto é %s.%n", FormatterUtils.converteBigDecimalToString(valor));
            System.out.println();
        } catch (ListaSemDadosException e) {
            System.out.println(ConsoleUtils.getConsoleErrorTextBold().format(e.getMessage()));
        }
    }

    private static void calcularTotalRecebidoIncluindoDescontos() {
        System.out.println("Calculando a localização que facturou mais.");
        try {
            BigDecimal valor = PropriedadeEstatistica.getTotalRecebidoIncluindoDescontos(propriedadeRepositoryFAKE.findAll());
            System.out.printf("O valor total recebido tendo em conta os descontos é %s.%n", FormatterUtils.converteBigDecimalToString(valor));
            System.out.println();
        } catch (ListaSemDadosException e) {
            System.out.println(ConsoleUtils.getConsoleErrorTextBold().format(e.getMessage()));
        }
    }

    private static void calcularLocalFacturouMais() {
        System.out.println("Calculando a localização que facturou mais.");
        try {
            List<LocalizacaoFacturouMais> localizacaoFacturouMais = PropriedadeEstatistica.getLocalizacaoFacturouMais(propriedadeRepositoryFAKE.findAll(), localizacaoRepositoryFAKE.findAll());
            System.out.printf("Resumo: A localização que facturou mais é a %s com um facturamento de %s. %n",
                    localizacaoFacturouMais.get(0).getLocalizacao().getDesignacao(),
                    FormatterUtils.converteBigDecimalToString(localizacaoFacturouMais.get(0).getValorFacturado()));
            System.out.println();
            System.out.println("Imprimindo tabela das localizações e seus facturamentos");
            String[] cabecalho = new String[]{"Localização", "Facturamento"};
            String[][] data = localizacaoFacturouMais.stream().map(LocalizacaoFacturouMais::getRow).toArray(String[][]::new);
            String[] rodape = new String[]{"Total: ", FormatterUtils.converteDoubleToString(localizacaoFacturouMais.stream().map(LocalizacaoFacturouMais::getValorFacturado).mapToDouble(BigDecimal::doubleValue).sum())};

            System.out.println(AsciiTable.getTable(AsciiTable.BASIC_ASCII, cabecalho, rodape, data));

            System.out.printf("Resumo: A localização que facturou mais é a %s com um facturamento de %s. %n",
                    localizacaoFacturouMais.get(0).getLocalizacao().getDesignacao(),
                    FormatterUtils.converteBigDecimalToString(localizacaoFacturouMais.get(0).getValorFacturado()));
            System.out.println();
        } catch (ListaSemDadosException e) {
            System.out.println(ConsoleUtils.getConsoleErrorTextBold().format(e.getMessage()));
        }
    }

    private static void calcularLucroDaEmpresaTendoEmContaAsDespesas() {
        System.out.println("Calculando o lucro da empresa tendo em conta as despesas (As despesas são 70%, caso queira mudar este valor fale com o programador).");
        System.out.println();
        try {
            BigDecimal lucroTendoEmContaDespesas = PropriedadeEstatistica.getLucroTendoEmContaDespesas(propriedadeRepositoryFAKE.findAll(), 0.70F);
            BigDecimal despesas = PropriedadeEstatistica.getValorTotalRecebido(propriedadeRepositoryFAKE.findAll()).subtract(lucroTendoEmContaDespesas);
            System.out.printf("O lucro da empresa foi de %s sem as despesas, as despesas da empresa somaram %s até agora.%n", FormatterUtils.converteBigDecimalToString(lucroTendoEmContaDespesas), FormatterUtils.converteBigDecimalToString(despesas));
            System.out.println();
        } catch (ListaSemDadosException e) {
            System.out.println(ConsoleUtils.getConsoleErrorTextBold().format(e.getMessage()));
        }
    }

    private static void calcularTotalRecebido() {
        boolean naoVoltar = true;

        do {
            int proximoPasso;

            System.out.println("Deseja saber os valores totais recebidos:");
            System.out.println("1 - Em todas os tipos de propriedades;");
            EnumSet.allOf(TipoPropriedade.class).forEach((tp) -> System.out.printf("%d - %s;%n", tp.getNumero(), tp.getNome()));
            System.out.println("10 - Voltar;");
            System.out.println();
            proximoPasso = ConsoleUtils.receberValorInteiroDoUtilizador();

            switch (proximoPasso) {
                case -2: {
                    break;
                }
                case 1: {
                    try {
                        BigDecimal valorTotalRecebido;
                        valorTotalRecebido = PropriedadeEstatistica.getValorTotalRecebido(propriedadeRepositoryFAKE.findAll());
                        System.out.printf("O valor total recebido pela venda de todas as propriedades é %s propriedades.%n", NumberFormat.getCurrencyInstance(Locale.getDefault()).format(valorTotalRecebido));
                        System.out.println();
                    } catch (ListaSemDadosException e) {
                        System.out.println(ConsoleUtils.getConsoleErrorTextBold().format(e.getMessage()));
                    }
                    break;
                }
                case 2: {
                    try {
                        BigDecimal valorTotalRecebido;
                        valorTotalRecebido = PropriedadeEstatistica.getValorTotalRecebidoByTipoPropriedade(TipoPropriedade.VIVENDA, propriedadeRepositoryFAKE.findAll());
                        System.out.printf("O valor total recebido pela venda de todas as propriedades do tipo %s é %s propriedades.%n", TipoPropriedade.VIVENDA.getNome(), NumberFormat.getCurrencyInstance(Locale.getDefault()).format(valorTotalRecebido));
                        System.out.println();
                    } catch (ListaSemDadosException | ValorInvalidoException e) {
                        System.out.println(ConsoleUtils.getConsoleErrorTextBold().format(e.getMessage()));

                    }
                    break;
                }
                case 3: {
                    try {
                        BigDecimal valorTotalRecebido;
                        valorTotalRecebido = PropriedadeEstatistica.getValorTotalRecebidoByTipoPropriedade(TipoPropriedade.FLAT, propriedadeRepositoryFAKE.findAll());
                        System.out.printf("O valor total recebido pela venda de todas as propriedades do tipo %s é %s propriedades.%n", TipoPropriedade.VIVENDA.getNome(), NumberFormat.getCurrencyInstance(Locale.getDefault()).format(valorTotalRecebido));
                        System.out.println();
                    } catch (ListaSemDadosException | ValorInvalidoException e) {
                        System.out.println(ConsoleUtils.getConsoleErrorTextBold().format(e.getMessage()));

                    }
                    break;
                }
                case 10: {
                    naoVoltar = false;
                    break;
                }
                default: {
                    System.out.println(ConsoleUtils.getConsoleErrorTextBold().format("Erro: Valor introduzido não corresponde a nenhuma das opções na lista."));
                    break;
                }
            }

        } while (naoVoltar);
    }

    private static void calcularQuantidadesVendidas() {
        boolean naoVoltar = true;

        do {
            int proximoPasso;

            System.out.println("Deseja saber as quantidades vendidas para:");
            System.out.println("1 - Todos os tipos de propriedades;");
            EnumSet<TipoPropriedade> tipoPropriedadeEnumSet = EnumSet.allOf(TipoPropriedade.class);
            tipoPropriedadeEnumSet.forEach((tp) -> System.out.printf("%d - %s;%n", tp.getNumero(), tp.getNome()));
            System.out.println("10 - Voltar;");
            System.out.println();
            proximoPasso = ConsoleUtils.receberValorInteiroDoUtilizador();

            switch (proximoPasso) {
                case -2: {
                    break;
                }
                case 1: {
                    Long quantidadeTotalVendida = PropriedadeEstatistica.getQuantidadeTotalVendida(propriedadeRepositoryFAKE.findAll());
                    System.out.printf("A quantidade total de propriedades vendidas é %d propriedades.%n", quantidadeTotalVendida);
                    System.out.println();
                    break;
                }
                case 2: {
                    try {
                        Long quantidadeTotalVendida = PropriedadeEstatistica.getQuantidadeVendidaByTipoPropriedade(TipoPropriedade.VIVENDA, propriedadeRepositoryFAKE.findAll());
                        System.out.printf("A quantidade total de propriedades vendidas do tipo %s é %d propriedades.%n", TipoPropriedade.VIVENDA.getNome(), quantidadeTotalVendida);
                        System.out.println();
                    } catch (ValorInvalidoException valorInvalidoException) {
                        System.out.println(ConsoleUtils.getConsoleErrorTextBold().format(valorInvalidoException.getMessage()));
                    }
                    break;
                }
                case 3: {
                    try {
                        Long quantidadeTotalVendida = PropriedadeEstatistica.getQuantidadeVendidaByTipoPropriedade(TipoPropriedade.FLAT, propriedadeRepositoryFAKE.findAll());
                        System.out.printf("A quantidade total de propriedades vendidas do tipo %s é %d propriedades.%n", TipoPropriedade.FLAT.getNome(), quantidadeTotalVendida);
                        System.out.println();
                    } catch (ValorInvalidoException valorInvalidoException) {
                        System.out.println(ConsoleUtils.getConsoleErrorTextBold().format(valorInvalidoException.getMessage()));
                    }
                    break;
                }
                case 10: {
                    naoVoltar = false;
                    break;
                }
                default: {
                    System.out.println(ConsoleUtils.getConsoleErrorTextBold().format("Erro: Valor introduzido não corresponde a nenhuma das opções na lista."));
                    break;
                }
            }

        } while (naoVoltar);
    }

    static void imprimirDados() {
        System.out.println("Imprimindo dados das propriedades em forma de tabela:");
        String[] cabecalhos = {"", "Local da Propriedade", "Tipo de Propriedade", "Quartos", "Valor em MT", "Desconto em MT", "Preço Pago pelo Cliente"};
        String[] footer = PropriedadeEstatistica.getRodapeParaTabela(propriedadeRepositoryFAKE.findAll());
        String[][] dados = PropriedadeEstatistica.getDadosEmTabela(propriedadeRepositoryFAKE.findAll());
        System.out.println(AsciiTable.getTable(cabecalhos, footer, dados));
        System.out.println();
    }

    private static void imprimeMenu() {
        System.out.println("Menu de Estatisticas");
        Arrays.stream(StatisticMenuItems.values()).map((StatisticMenuItems::getItemName)).forEach(System.out::println);
        System.out.println();
    }
}
