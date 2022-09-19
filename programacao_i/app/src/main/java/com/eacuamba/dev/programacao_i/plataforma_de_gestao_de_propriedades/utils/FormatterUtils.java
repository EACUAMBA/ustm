package com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.utils;

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
