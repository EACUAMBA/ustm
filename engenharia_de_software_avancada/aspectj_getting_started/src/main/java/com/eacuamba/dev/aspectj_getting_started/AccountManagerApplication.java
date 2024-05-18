package com.eacuamba.dev.aspectj_getting_started;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class AccountManagerApplication {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AccoutManager accoutManager = AccoutManager.getInstance();

    public static void main(String[] args) {
        BigDecimal value = BigDecimal.valueOf(1000.0);
        BigDecimal percentage = BigDecimal.valueOf(75.0);


        BigDecimal percentageInDecimals = percentage.divide(BigDecimal.valueOf(100.0), MathContext.DECIMAL64);
        System.out.println("percentage " + percentage + " percentage in decimals " + percentageInDecimals);

        System.out.println("value with percentgae applied " + value.multiply(percentageInDecimals));
    }

    public static void showMenu() {
        StringBuilder menuStringBuilder = new StringBuilder();
        menuStringBuilder.append("Menu:").append("\n");
        menuStringBuilder.append("1. Transferir").append("\n");
        menuStringBuilder.append("2. Levantar").append("\n");
        menuStringBuilder.append("3. Ver saldo").append("\n");
        menuStringBuilder.append("0. Sair").append("\n");

        Integer selectedOption = null;

        while (selectedOption == null) {

            System.out.println(menuStringBuilder);
            selectedOption = ConsoleUtilities.getIntegerOrMinusOneFromScanner();

            switch (selectedOption) {
                case 0: {
                    break;
                }
                case 1: {
                    accoutManager.transfer();
                    showMenu();
                    break;
                }
                case 2: {
                    accoutManager.withdraw();
                    showMenu();
                    break;
                }
                case 3: {
                    accoutManager.viewBalance();
                    showMenu();
                    break;
                }
                default: {
                    System.out.println("Invalid option");
                    showMenu();
                }
            }
        }
    }

}
