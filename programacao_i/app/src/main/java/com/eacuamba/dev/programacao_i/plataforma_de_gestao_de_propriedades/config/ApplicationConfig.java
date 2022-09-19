package com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.config;

import com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.command_line_interface.utils.ConsoleUtils;
import com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.domain.exception.ValorNaoEncontradoException;
import com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.domain.model.Propriedade;
import com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.domain.model.TipoPropriedade;
import com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.domain.repository.LocalizacaoRepositoryFAKE;
import com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.domain.repository.PropriedadeRepositoryFAKE;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ApplicationConfig {

    public static void setDefaults() {
        Locale.setDefault(new Locale("pt", "MZ"));
    }

    public static void imprimeDadosDoDesenvolvedor() {
        System.out.println("Informações do Desenvolvedor:");
        System.out.println("\t Desenvolvido por Edilson Alexandre Cuamba (Código do estudante: 2021 1010 69);");
        System.out.println("\t Estudante na Universidade São Tomás de Moçambique;");
        System.out.println("\t Integrante da turma 1P2LDS1;");
        System.out.println("\t Curso de Licenciatura em Desenvolvimento de Software;");
        System.out.println("\nInformações Profissionais:");
        System.out.println("\t Desenvolvedor Java (versão 1.8), PHP (versão 8.*), JavaScript (EcmaScript versão 6.*);");
        System.out.println("\t Frameworks Java: Spring, ZKoss, Jakarta EE (JPA, JSF e etc.);");
        System.out.println("\t Frameworks PHP: Laravel;");
        System.out.println("\t Frameworks JavaScript: VueJS;");
        System.out.println("\t *Disponível para trabalhar*");
        System.out.println("\nInformações de contacto:");
        System.out.println("\t Telemóvel: (+258) 842 473 772");
        System.out.println("\t Telemóvel: (+258) 822 565 148");
        System.out.println("\t Email: " + ConsoleUtils.getConsoleLinkNormalText().format("mailto:edilsoncuamba@gmail.com"));
        System.out.println("\t Residência: Marracuene");
        System.out.println("\n** Muito Obrigado **");
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("'Aos' dd 'de' MMMM 'de' yyyy 'as' HH:mm:ss", Locale.getDefault())));
        System.out.println();
    }

    public static void terminarExecucao() {
        System.out.println("Obrigado por usar o sistema!");
        System.out.println("Adeus!");
        System.out.println();
        imprimeDadosDoDesenvolvedor();
        System.exit(0);
    }

    public static void loadData() {
        LocalizacaoRepositoryFAKE localizacaoRepositoryFAKE = LocalizacaoRepositoryFAKE.getInstance();
        PropriedadeRepositoryFAKE propriedadeRepositoryFAKE = PropriedadeRepositoryFAKE.getInstance();
        try {
            Propriedade propriedade = new Propriedade();
            propriedade.setTipoPropriedade(TipoPropriedade.FLAT);
            propriedade.setLocalizacao(localizacaoRepositoryFAKE.findById(2L).orElseThrow(() -> new ValorNaoEncontradoException(String.format("A Localizaçâo com o id \"%d\" não foi encontrada.", 2L))));
            propriedade.setValor(BigDecimal.valueOf(960_000D));
            propriedade.setDesconto(20);
            propriedade.setValorPago(BigDecimal.valueOf(100_000D));
            propriedade.setNumeroQuartos(1);
            propriedadeRepositoryFAKE.save(propriedade);

            Propriedade propriedade1 = new Propriedade();
            propriedade1.setTipoPropriedade(TipoPropriedade.VIVENDA);
            propriedade1.setLocalizacao(localizacaoRepositoryFAKE.findById(1L).orElseThrow(() -> new ValorNaoEncontradoException(String.format("A Localizaçâo com o id \"%d\" não foi encontrada.", 2L))));
            propriedade1.setValor(BigDecimal.valueOf(960_000D));
            propriedade1.setDesconto(20);
            propriedade1.setValorPago(BigDecimal.valueOf(500_000D));
            propriedade1.setNumeroQuartos(2);
            propriedadeRepositoryFAKE.save(propriedade1);

            Propriedade propriedade2 = new Propriedade();
            propriedade2.setTipoPropriedade(TipoPropriedade.VIVENDA);
            propriedade2.setLocalizacao(localizacaoRepositoryFAKE.findById(2L).orElseThrow(() -> new ValorNaoEncontradoException(String.format("A Localizaçâo com o id \"%d\" não foi encontrada.", 2L))));
            propriedade2.setValor(BigDecimal.valueOf(960_000D));
            propriedade2.setDesconto(20);
            propriedade2.setValorPago(BigDecimal.valueOf(950_000D));
            propriedade2.setNumeroQuartos(2);
            propriedadeRepositoryFAKE.save(propriedade2);

            Propriedade p4 = new Propriedade();
            p4.setTipoPropriedade(TipoPropriedade.FLAT);
            p4.setLocalizacao(localizacaoRepositoryFAKE.findById(1L).orElseThrow(() -> new ValorNaoEncontradoException(String.format("A Localizaçâo com o id \"%d\" não foi encontrada.", 2L))));
            p4.setValor(BigDecimal.valueOf(800_000D));
            p4.setDesconto(20);
            p4.setValorPago(BigDecimal.valueOf(750_000D));
            p4.setNumeroQuartos(2);
            propriedadeRepositoryFAKE.save(p4);
        } catch (Exception exception) {
            System.err.println(exception.getLocalizedMessage());
            System.err.flush();
            System.err.close();
        }
    }
}
