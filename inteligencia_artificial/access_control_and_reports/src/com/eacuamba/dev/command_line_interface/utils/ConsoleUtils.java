package com.eacuamba.dev.command_line_interface.utils;

import com.diogonunes.jcolor.AnsiFormat;
import com.diogonunes.jcolor.Attribute;
import com.eacuamba.dev.domain.exception.ValorInvalidoException;

import java.io.Console;
import java.util.Scanner;

public class ConsoleUtils {
    private static Scanner teclado = new Scanner(System.in);
    private static Console console = System.console();

    public static AnsiFormat getConsoleNormalText() {
        return new AnsiFormat(Attribute.WHITE_TEXT());
    }

    public static AnsiFormat getConsoleNormalTextBold() {
        return new AnsiFormat(Attribute.WHITE_TEXT(), Attribute.BOLD());
    }

    public static AnsiFormat getConsoleErrorText() {
        return new AnsiFormat(Attribute.RED_TEXT());
    }

    public static AnsiFormat getConsoleErrorTextBold() {
        return new AnsiFormat(Attribute.RED_TEXT(), Attribute.BOLD());
    }

    public static AnsiFormat getConsoleLinkNormalText() {
        return new AnsiFormat(Attribute.BLUE_TEXT());
    }

    public static AnsiFormat getConsoleSuccessTextBold() {
        return new AnsiFormat(Attribute.GREEN_TEXT(), Attribute.BOLD());
    }

    public static int receberValorInteiroDoUtilizador() {
        System.out.print("Resposta: ");
        String texto = teclado.nextLine();
        try {
            System.out.println();
            return Integer.parseInt(texto);
        } catch (NumberFormatException numberFormatException) {
            System.out.println(ConsoleUtils.getConsoleErrorTextBold().format("Erro: Ocorreu um erro ao ler o valor que informou."));
            return -2;
        }
    }

    public static int receberValorInteiroDoUtilizadorSemImpressaoErro() {
        System.out.print("Resposta: ");
        String texto = teclado.nextLine();
        try {
            System.out.println();
            return Integer.parseInt(texto);
        } catch (NumberFormatException numberFormatException) {
            return -2;
        }
    }

    public static double receberValorDecimalDoUtilizador() {
        System.out.println("Separe os centavos com virgula, exemplo \"20.500.000,75\": ");
        System.out.print("Resposta: ");
        String texto = teclado.nextLine();
        try {
            System.out.println();
            texto = texto.trim().replace(".", "").replace(",", ".");
            return Double.parseDouble(texto);
        } catch (NumberFormatException | NullPointerException exception) {
            System.out.println(ConsoleUtils.getConsoleErrorTextBold().format("Erro: Ocorreu um erro ao ler o valor que informou."));
            return -2;
        }
    }

    public static double receberValorDecimalDoUtilizadorSemImpressaoErro() {
        System.out.println("Separe os centavos com virgula, exemplo \"10.250.000.50\": ");
        System.out.print("Resposta: ");
        String texto = teclado.nextLine();
        try {
            System.out.println();
            texto = texto.trim().replace(".", "").replace(",", ".");
            return Double.parseDouble(texto);
        } catch (NumberFormatException | NullPointerException exception) {
            return -2;
        }
    }

    public static String receberCaracteresDoUtilizador() {
        System.out.print("Resposta: ");
        String texto = teclado.nextLine();
        try {
            System.out.println();
            texto = texto.trim();
            if (texto.length() < 3) {
                throw new ValorInvalidoException("Valor introduzido extremamente inferior, introduza valore iguais ou superiores a 3 \"valor => 3\"!");
            }
            return texto;
        } catch (ValorInvalidoException valorInvalidoException) {
            System.out.println(ConsoleUtils.getConsoleErrorTextBold().format(valorInvalidoException.getMessage()));
            return null;
        }
    }
}
