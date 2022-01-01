package com.eacuamba.dev.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatterUtils {
    private static NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(Locale.getDefault());
    public static String  converteBigDecimalToString(BigDecimal valor){
        return currencyInstance.format(valor);
    }
    public static String  converteDoubleToString(Double valor){
        return currencyInstance.format(valor);
    }
}
