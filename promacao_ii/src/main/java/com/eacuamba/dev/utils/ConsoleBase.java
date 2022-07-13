package com.eacuamba.dev.utils;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public abstract class ConsoleBase {
    protected static final Scanner input;
    protected static final PrintStream output;
    private static boolean naoFecharEntradaDeDadosNoTerminal;
    private static final Locale myLocale = new Locale("pt", "MZ");
    protected static final MathContext mathContext = new MathContext(10, RoundingMode.HALF_UP);

    static {
        input = new Scanner(System.in);
        output = System.out;
    }

    protected static BigDecimal getValorNumericoAsBigDecimalFromConsole(String campo) {
        BigDecimal valor = null;
        do {
            try {
                output.printf("%s: ", campo);
                String texto = input.nextLine();
                double valorEmDouble = Double.parseDouble(texto);
                valor = BigDecimal.valueOf(valorEmDouble);
                naoFecharEntradaDeDadosNoTerminal = false;
            } catch (NumberFormatException | NullPointerException e) {
                output.printf("Por favor insira um \"%s\" válido.%n", campo);
                naoFecharEntradaDeDadosNoTerminal = true;
            }
        } while (naoFecharEntradaDeDadosNoTerminal);
        return valor;
    }
    
    protected static Double getValorNumericoAsDoubleFromConsole(String campo) {
        Double valor = null;
        do {
            try {
                output.printf("%s: ", campo);
                String texto = input.nextLine();
                valor = Double.parseDouble(texto);
                naoFecharEntradaDeDadosNoTerminal = false;
            } catch (NumberFormatException | NullPointerException e) {
                output.printf("Por favor insira um \"%s\" válido.%n", campo);
                naoFecharEntradaDeDadosNoTerminal = true;
            }
        } while (naoFecharEntradaDeDadosNoTerminal);
        return valor;
    }

    protected static Integer getValorNumericoAsIntegerFromConsole(String campo) {
        Integer valor = null;
        do {
            try {
                output.printf("%s: ", campo);
                String texto = input.nextLine();
                valor = Integer.parseInt(texto);
                naoFecharEntradaDeDadosNoTerminal = false;
            } catch (NumberFormatException | NullPointerException e) {
                output.printf("Por favor insira um \"%s\" válido.%n", campo);
                naoFecharEntradaDeDadosNoTerminal = true;
            }
        } while (naoFecharEntradaDeDadosNoTerminal);
        return valor;
    }

    protected static String getTextoFromConsole(String campo) {
        String valor = "";
        do {
            try {
                output.printf("%s: ", campo);
                String texto = input.nextLine();
                if (texto.trim().isEmpty())
                    throw new IllegalArgumentException();
                valor = texto;
                naoFecharEntradaDeDadosNoTerminal = false;
            } catch (IllegalArgumentException e) {
                output.printf("Por favor insira um \"%s\" válido.%n", campo);
                naoFecharEntradaDeDadosNoTerminal = true;
            }
        } while (naoFecharEntradaDeDadosNoTerminal);
        return valor;
    }

    protected static String convertBigDecimalToStringConcurrency(BigDecimal valor) {
        return NumberFormat.getCurrencyInstance(myLocale).format(valor);
    }

    protected static String convertIntegerToString(Integer valor) {
        return DecimalFormat.getNumberInstance(myLocale).format(valor);
    }

    protected static String convertDoubleToString(Double valor) {
        return DecimalFormat.getNumberInstance(myLocale).format(valor);
    }
}
