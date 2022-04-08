package com.eacuamba.dev.iii_semestre.trabalho_individual_1.exercicio_18;

import java.io.PrintStream;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Scanner;

public class Ler {
    protected static final Scanner input;
    protected static final PrintStream output;
    private static boolean naoFecharEntradaDeDadosNoTerminal;

    static {
        input = new Scanner(System.in);
        output = System.out;
    }

    public static String umaString() {
        String valor = null;
        do {
            try {
                String texto = input.nextLine();
                naoFecharEntradaDeDadosNoTerminal = false;
            } catch (NumberFormatException | NullPointerException e) {
                output.printf("Por favor insira um valor válido.%n");
                naoFecharEntradaDeDadosNoTerminal = true;
            }
        } while (naoFecharEntradaDeDadosNoTerminal);
        return valor;
    }

    public static char umaChar() {
        char valor = Character.UNASSIGNED;
        do {
            try {
                String texto = input.nextLine();
                valor= texto.charAt(0);
                naoFecharEntradaDeDadosNoTerminal = false;
            } catch (NumberFormatException | NullPointerException e) {
                output.printf("Por favor insira um valor válido.%n");
                naoFecharEntradaDeDadosNoTerminal = true;
            }
        } while (naoFecharEntradaDeDadosNoTerminal);
        return valor;
    }

    public static int umaInt() {
        int valor = Integer.MIN_VALUE;
        do {
            try {
                String texto = input.nextLine();
                valor= Integer.parseInt(texto);
                naoFecharEntradaDeDadosNoTerminal = false;
            } catch (NumberFormatException | NullPointerException e) {
                output.printf("Por favor insira um valor válido.%n");
                naoFecharEntradaDeDadosNoTerminal = true;
            }
        } while (naoFecharEntradaDeDadosNoTerminal);
        return valor;
    }

    public static long umLong() {
        long valor = Long.MIN_VALUE;
        do {
            try {
                String texto = input.nextLine();
                valor= Long.parseLong(texto);
                naoFecharEntradaDeDadosNoTerminal = false;
            } catch (NumberFormatException | NullPointerException e) {
                output.printf("Por favor insira um valor válido.%n");
                naoFecharEntradaDeDadosNoTerminal = true;
            }
        } while (naoFecharEntradaDeDadosNoTerminal);
        return valor;
    }

    public static short umShort() {
        short valor = Short.MIN_VALUE;
        do {
            try {
                String texto = input.nextLine();
                valor= Short.parseShort(texto);
                naoFecharEntradaDeDadosNoTerminal = false;
            } catch (NumberFormatException | NullPointerException e) {
                output.printf("Por favor insira um valor válido.%n");
                naoFecharEntradaDeDadosNoTerminal = true;
            }
        } while (naoFecharEntradaDeDadosNoTerminal);
        return valor;
    }

    public static float umFloat() {
        float valor = Float.MIN_VALUE;
        do {
            try {
                String texto = input.nextLine();
                valor= Float.parseFloat(texto);
                naoFecharEntradaDeDadosNoTerminal = false;
            } catch (NumberFormatException | NullPointerException e) {
                output.printf("Por favor insira um valor válido.%n");
                naoFecharEntradaDeDadosNoTerminal = true;
            }
        } while (naoFecharEntradaDeDadosNoTerminal);
        return valor;
    }

    public static double umDouble() {
        double valor = Double.MIN_VALUE;
        do {
            try {
                String texto = input.nextLine();
                valor= Double.parseDouble(texto);
                naoFecharEntradaDeDadosNoTerminal = false;
            } catch (NumberFormatException | NullPointerException e) {
                output.printf("Por favor insira um valor válido.%n");
                naoFecharEntradaDeDadosNoTerminal = true;
            }
        } while (naoFecharEntradaDeDadosNoTerminal);
        return valor;
    }

    public static boolean umBoolean() {
        boolean valor = Boolean.FALSE;
        do {
            try {
                String texto = input.nextLine();
                valor= Boolean.parseBoolean(texto);
                naoFecharEntradaDeDadosNoTerminal = false;
            } catch (NumberFormatException | NullPointerException e) {
                output.printf("Por favor insira um valor válido.%n");
                naoFecharEntradaDeDadosNoTerminal = true;
            }
        } while (naoFecharEntradaDeDadosNoTerminal);
        return valor;
    }

}
