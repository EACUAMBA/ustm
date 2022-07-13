package com.eacuamba.dev.iii_semestre.programacao_ii.teste_2.exercicio_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class App {
    public static void main(String[] args) {
        JFrame tela = new JFrame("Calculadora");
        tela.setSize(500, 500);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLayout(new BorderLayout());

        JPanel screen = new JPanel();
        screen.setLayout(new BorderLayout());
        JTextField resultado = new JTextField();
        screen.add(resultado, BorderLayout.CENTER);
        tela.add(screen, BorderLayout.NORTH);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(4, 3));
        JButton um = new JButton("1");
        buttons.add(um);
        JButton dois = new JButton("2");
        buttons.add(dois);
        JButton tres = new JButton("3");
        buttons.add(tres);
        JButton quatro = new JButton("4");
        buttons.add(quatro);
        JButton cinco = new JButton("5");
        buttons.add(cinco);
        JButton seis = new JButton("6");
        buttons.add(seis);
        JButton sete = new JButton("7");
        buttons.add(sete);
        JButton oito = new JButton("8");
        buttons.add(oito);
        JButton nove = new JButton("9");
        buttons.add(nove);
        JButton zero = new JButton("0");
        buttons.add(zero);
        tela.add(buttons, BorderLayout.CENTER);

        JPanel operacoes = new JPanel();
        operacoes.setLayout(new FlowLayout());
        JButton soma = new JButton("+");
        operacoes.add(soma);
        JButton subtracao = new JButton("-");
        operacoes.add(subtracao);
        JButton divisao = new JButton("/");
        operacoes.add(divisao);
        JButton multiplicacao = new JButton("x");
        operacoes.add(multiplicacao);
        JButton igual = new JButton("=");
        operacoes.add(igual);
        tela.add(operacoes, BorderLayout.SOUTH);


        tela.setVisible(true);

        Component[] components = operacoes.getComponents();
        for(Component c : components){
            if(c instanceof JButton){
                JButton operacao = (JButton) c;
                operacao.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(operacao.getText().equalsIgnoreCase("+")){
                            resultado.setText(resultado.getText() + "+");
                        }else if(operacao.getText().equalsIgnoreCase("-")){
                            resultado.setText(resultado.getText() + "-");
                        } if(operacao.getText().equalsIgnoreCase("/")){
                            resultado.setText(resultado.getText() + "/");
                        } if(operacao.getText().equalsIgnoreCase("x")){
                            resultado.setText(resultado.getText() + "*");
                        } if(operacao.getText().equalsIgnoreCase("=")){
                            List<String> tokens = new ArrayList<>();
                            StringTokenizer stringTokenizer = new StringTokenizer(resultado.getText(), "+|/|*|-", true);
                           while (stringTokenizer.hasMoreTokens())
                               tokens.add(stringTokenizer.nextToken());
                            double a = Double.parseDouble(tokens.get(0));
                            double b = Double.parseDouble(tokens.get(2));
                            resultado.setText((a+b)+"");
                        }
                    }
                });
            }
        }
    }


}
