package com.eacuamba.dev.utils;

import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public abstract class JOptionPaneUtils {
    public static final Scanner input;
    public static final PrintStream output;
    private static boolean naoFecharEntradaDeDadosNoTerminal;
    private static final Locale myLocale = new Locale("pt", "MZ");
    public static final MathContext mathContext = new MathContext(10, RoundingMode.HALF_UP);

    static {
        input = new Scanner(System.in);
        output = System.out;
    }

    public static BigDecimal getValorNumericoAsBigDecimalFromConsole(Component owner, String campo) {
        BigDecimal valor = null;
        do {
            try {
                output.printf("%s: ", campo);
                String texto = JOptionPane.showInputDialog(owner, campo);
                double valorEmDouble = Double.parseDouble(texto);
                valor = BigDecimal.valueOf(valorEmDouble);
                naoFecharEntradaDeDadosNoTerminal = false;
            } catch (NumberFormatException | NullPointerException e) {
                JOptionPane.showMessageDialog(owner, String.format("Por favor insira um \"%s\" válido.%n", campo));
                naoFecharEntradaDeDadosNoTerminal = true;
            }
        } while (naoFecharEntradaDeDadosNoTerminal);
        return valor;
    }
    
    public static Double getValorNumericoAsDoubleFromConsole(Component owner, String campo) {
        Double valor = null;
        do {
            try {
                output.printf("%s: ", campo);
                String texto = JOptionPane.showInputDialog(owner, campo);
                valor = Double.parseDouble(texto);
                naoFecharEntradaDeDadosNoTerminal = false;
            } catch (NumberFormatException | NullPointerException e) {
                JOptionPane.showMessageDialog(owner, String.format("Por favor insira um \"%s\" válido.%n", campo));
                naoFecharEntradaDeDadosNoTerminal = true;
            }
        } while (naoFecharEntradaDeDadosNoTerminal);
        return valor;
    }

    public static Integer getValorNumericoAsIntegerFromConsole(Component owner, String campo) {
        Integer valor = null;

            try {
                output.printf("%s: ", campo);
                String texto = JOptionPane.showInputDialog(owner, campo);
                valor = Integer.parseInt(texto);
                naoFecharEntradaDeDadosNoTerminal = false;
            } catch (NumberFormatException | NullPointerException e) {
                JOptionPane.showMessageDialog(owner, String.format("Por favor insira um \"%s\" válido.%n", campo));
                naoFecharEntradaDeDadosNoTerminal = false;
            }

        return valor;
    }

    public static Short getValorNumericoAsShortFromConsole(Component owner, String campo) {
        Short valor = null;
        do {
            try {
                output.printf("%s: ", campo);
                String texto = JOptionPane.showInputDialog(owner, campo);
                valor = Short.parseShort(texto);
                naoFecharEntradaDeDadosNoTerminal = false;
            } catch (NumberFormatException | NullPointerException e) {
                JOptionPane.showMessageDialog(owner, String.format("Por favor insira um \"%s\" válido.%n", campo));
                naoFecharEntradaDeDadosNoTerminal = true;
            }
        } while (naoFecharEntradaDeDadosNoTerminal);
        return valor;
    }

    public static String getTextoFromConsole(Component owner, String campo) {
        String valor = "";
        do {
            try {
                output.printf("%s: ", campo);
                String texto = JOptionPane.showInputDialog(owner, campo);
                if (texto.trim().isEmpty())
                    throw new IllegalArgumentException();
                valor = texto;
                naoFecharEntradaDeDadosNoTerminal = false;
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(owner, String.format("Por favor insira um \"%s\" válido.%n", campo));
                naoFecharEntradaDeDadosNoTerminal = true;
            }
        } while (naoFecharEntradaDeDadosNoTerminal);
        return valor;
    }

    public static String convertBigDecimalToStringConcurrency(BigDecimal valor) {
        return NumberFormat.getCurrencyInstance(myLocale).format(valor);
    }

    public static String convertIntegerToString(Integer valor) {
        return DecimalFormat.getNumberInstance(myLocale).format(valor);
    }

    public static String convertDoubleToString(Double valor) {
        return DecimalFormat.getNumberInstance(myLocale).format(valor);
    }
}
