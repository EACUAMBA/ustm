package com.eacuamba.dev.iii_semestre.trabalho_individual_2.calculadora.services;

import com.eacuamba.dev.iii_semestre.trabalho_individual_2.calculadora.services.strategies.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CalculadoraService {
    public Double calcular(String operacao) {
        String execucao = operacao;
        List<String> tokens = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(execucao, "*|/|+|-", true);
        while (stringTokenizer.hasMoreTokens()) {
            tokens.add(stringTokenizer.nextToken());
        }

        List<String> strings = tokens;


        int indexOfMultiply = 0;
        int indexOfDivision = 0;
        do {
             indexOfMultiply = strings.indexOf("*");
             indexOfDivision = strings.indexOf("/");

            if(indexOfDivision <= indexOfMultiply && (indexOfDivision != -1)){
                strings = this.resultadoDivisao(tokens);
            }else if (indexOfMultiply <= indexOfDivision && (indexOfMultiply != -1)) {
                strings = this.resultadoMultiplicacoes(tokens);
            }else  {
                strings = this.resultadoMultiplicacoes(tokens);
                strings = this.resultadoDivisao(tokens);
            }

            indexOfMultiply = strings.indexOf("*");
            indexOfDivision = strings.indexOf("/");
        } while ((indexOfMultiply != -1 || indexOfDivision != -1));

        do {
            strings = this.resultadoSoma(strings);
            strings = this.resultadoSubtracao(strings);
        } while (strings.size() != 1);

        JOptionPane.showMessageDialog(null, strings.get(0));
        return Double.parseDouble(strings.get(0));
    }

    public Double operar(Operacao operacao) {
        return operacao.operar();
    }

    public List<String> resultadoMultiplicacoes(List<String> tokens) {
        Double resultado = 0D;
            int indexOfMultiply = tokens.indexOf("*");
            if (indexOfMultiply != -1) {
                String valor1 = tokens.get(indexOfMultiply - 1);
                String valor2 = tokens.get(indexOfMultiply + 1);
                MultiplicacaoStrategy multiplicacaoStrategy = new MultiplicacaoStrategy(Double.parseDouble(valor1), Double.parseDouble(valor2));
                resultado = this.operar(multiplicacaoStrategy);
                tokens.set(indexOfMultiply, resultado.toString());
                tokens.remove(indexOfMultiply - 1);
                tokens.remove(indexOfMultiply);

            } else {
            }

        return tokens;
    }

    public List<String> resultadoDivisao(List<String> tokens) {
        Double resultado = 0D;
            int indexOfMultiply = tokens.indexOf("/");
            if (indexOfMultiply != -1) {
                String valor1 = tokens.get(indexOfMultiply - 1);
                String valor2 = tokens.get(indexOfMultiply + 1);
                DivisaoStrategy multiplicacaoStrategy = new DivisaoStrategy(Double.parseDouble(valor1), Double.parseDouble(valor2));
                resultado = this.operar(multiplicacaoStrategy);
                tokens.set(indexOfMultiply, resultado.toString());
                tokens.remove(indexOfMultiply - 1);
                tokens.remove(indexOfMultiply);

            } else {
            }

        return tokens;
    }

    public List<String> resultadoSoma(List<String> tokens) {
        Double resultado = 0D;
        int indexOfMultiply = tokens.indexOf("+");
        if (indexOfMultiply != -1) {
            String valor1 = tokens.get(indexOfMultiply - 1);
            String valor2 = tokens.get(indexOfMultiply + 1);
            SomaStrategy multiplicacaoStrategy = new SomaStrategy(Double.parseDouble(valor1), Double.parseDouble(valor2));
            resultado = this.operar(multiplicacaoStrategy);
            tokens.set(indexOfMultiply, resultado.toString());
            tokens.remove(indexOfMultiply - 1);
            tokens.remove(indexOfMultiply);
        } else {
        }


        return tokens;
    }

    public List<String> resultadoSubtracao(List<String> tokens) {
        Double resultado = 0D;
        int indexOfMultiply = tokens.indexOf("-");
        if (indexOfMultiply != -1) {
            String valor1 = tokens.get(indexOfMultiply - 1);
            String valor2 = tokens.get(indexOfMultiply + 1);
            SubtracaoStrategy multiplicacaoStrategy = new SubtracaoStrategy(Double.parseDouble(valor1), Double.parseDouble(valor2));
            resultado = this.operar(multiplicacaoStrategy);
            tokens.set(indexOfMultiply, resultado.toString());
            tokens.remove(indexOfMultiply - 1);
            tokens.remove(indexOfMultiply);
        } else {

        }

        return tokens;
    }
}
