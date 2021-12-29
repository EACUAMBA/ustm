package com.eacuamba.dev.config;

import com.diogonunes.jcolor.AnsiFormat;
import com.diogonunes.jcolor.Attribute;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ApplicationConfig {

    public static AnsiFormat getConsoleNormalText(){
        return new AnsiFormat(Attribute.WHITE_TEXT());
    }
    public static AnsiFormat getConsoleNormalTextBold(){
        return new AnsiFormat(Attribute.WHITE_TEXT(), Attribute.BOLD());
    }

    public static AnsiFormat getConsoleErrorText(){
        return new AnsiFormat(Attribute.RED_TEXT());
    }
    public static AnsiFormat getConsoleErrorTextBold(){
        return new AnsiFormat(Attribute.RED_TEXT(), Attribute.BOLD());
    }
    public static AnsiFormat getConsoleLinkNormalText(){
        return new AnsiFormat(Attribute.BLUE_TEXT());
    }
    public static AnsiFormat getConsoleSuccessTextBold(){
        return new AnsiFormat(Attribute.GREEN_TEXT(), Attribute.BOLD());
    }

    public static void setDefaults(){
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
        System.out.println("\nInformações de contacto:");
        System.out.println("\t Telemóvel: (+258) 842 473 772");
        System.out.println("\t Telemóvel: (+258) 822 565 148");
        System.out.println("\t Email: " + ApplicationConfig.getConsoleLinkNormalText().format("mailto:edilsoncuamba@gmail.com"));
        System.out.println("\t Residência: Marracuene");
        System.out.println("\n***** Muito Obrigado *****");
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("'Aos' dd 'de' MMMM 'de' yyyy 'as' HH:mm:ss", Locale.getDefault())));
        System.out.println();
    }

    public static void terminarExecucao(){
        System.out.println("Obrigado por usar o sistema!");
        System.out.println("Adeus!");
        System.out.println();
        imprimeDadosDoDesenvolvedor();
        System.exit(0);
    }
    }
