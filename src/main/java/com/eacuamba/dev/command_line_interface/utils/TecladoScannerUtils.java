package com.eacuamba.dev.command_line_interface.utils;

import com.eacuamba.dev.config.ApplicationConfig;
import com.eacuamba.dev.domain.exception.ValorInvalidoException;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Locale;
import java.util.Scanner;

public class TecladoScannerUtils {
    private static Scanner teclado = new Scanner(System.in);

    public static int receberValorInteiroDoUtilizador() {
        System.out.print("Resposta: ");
        String texto = teclado.nextLine();
        try{
            System.out.println();
            return Integer.parseInt(texto);
        }catch (NumberFormatException numberFormatException){
            System.out.println(ApplicationConfig.getConsoleErrorTextBold().format("Erro: Ocorreu um erro ao ler o valor que informou."));
            return -2;
        }
    }

    public static int receberValorInteiroDoUtilizadorSemImpressaoErro() {
        System.out.print("Resposta: ");
        String texto = teclado.nextLine();
        try{
            System.out.println();
            return Integer.parseInt(texto);
        }catch (NumberFormatException numberFormatException){
            return -2;
        }
    }

    public static double receberValorDecimalDoUtilizador() {
        System.out.println("Separe os centavos com virgula, exemplo \"20.500.000,75\": ");
        System.out.print("Resposta: ");
        String texto = teclado.nextLine();
        try{
            System.out.println();
            texto = texto.trim().replace(".", "").replace(",", ".");
            return Double.parseDouble(texto);
        }catch (NumberFormatException | NullPointerException exception){
            System.out.println(ApplicationConfig.getConsoleErrorTextBold().format("Erro: Ocorreu um erro ao ler o valor que informou."));
            return -2;
        }
    }
    public static double receberValorDecimalDoUtilizadorSemImpressaoErro() {
        System.out.println("Separe os centavos com virgula, exemplo \"10.250.000.50\": ");
        System.out.print("Resposta: ");
        String texto = teclado.nextLine();
        try{
            System.out.println();
            texto = texto.trim().replace(".", "").replace(",", ".");
            return Double.parseDouble(texto);
        }catch (NumberFormatException | NullPointerException exception){
            return -2;
        }
    }

    public static String receberCaracteresDoUtilizador() {
        System.out.print("Resposta: ");
        String texto = teclado.nextLine();
        try{
            System.out.println();
            texto = texto.trim();
            if(texto.length() < 3){
                throw new ValorInvalidoException("Valor introduzido extremamente inferior, introduza valore iguais ou superiores a 3 \"valor => 3\"!");
            }
            return texto;
        }catch (ValorInvalidoException valorInvalidoException){
            System.out.println(ApplicationConfig.getConsoleErrorTextBold().format(valorInvalidoException.getMessage()));
            return null;
        }
    }
}
