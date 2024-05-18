package com.eacuamba.dev.aspectj_getting_started;

import java.util.Scanner;

public class ConsoleUtilities {
    private static final Scanner scanner = new Scanner(System.in);

    public static Integer getIntegerOrMinusOneFromScanner() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            return -1;
        }
    }

    public static Double getDoubleOrNullFromScanner() {
        try {
            return scanner.nextDouble();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getStringFromScanner() {
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            return null;
        }
    }
}
