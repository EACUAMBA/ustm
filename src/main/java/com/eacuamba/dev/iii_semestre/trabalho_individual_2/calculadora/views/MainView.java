package com.eacuamba.dev.iii_semestre.trabalho_individual_2.calculadora.views;

import com.eacuamba.dev.iii_semestre.trabalho_individual_2.calculadora.services.CalculadoraService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

public class MainView extends JFrame {
    private CalculadoraService calculadoraService = new CalculadoraService();
    private JButton[] botoes = new JButton[19];
    private JPanel inputArea = new JPanel();
    private NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("pt", "MZ"));

    private Double memoria = null;


    public MainView() {
        this.setVisible(true);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.numberFormat.setMaximumFractionDigits(2);

        JPanel jPanel = new JPanel();
        jPanel.setSize(500,150);
        jPanel.setLayout(new BorderLayout());
        JTextArea jTextArea = new JTextArea();
        jTextArea.setFont(new Font("Open Sans", Font.PLAIN,16));
        jPanel.add(jTextArea, BorderLayout.CENTER);
        this.add(jPanel, BorderLayout.NORTH);

        //adicionando botões
        this.inputArea.setLayout(new GridLayout(5, 4));
        for (int i = 0; i < this.botoes.length; i++) {
            botoes[i] = new JButton();
            switch (i) {
                case 0: {
                    JButton jButton = new JButton("C");
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jTextArea.setText("");
                        }
                    });
                    this.inputArea.add(jButton);
                    break;
                }
                case 1: {
                    JButton jButton = new JButton("<-");
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                jTextArea.setText(jTextArea.getText().trim().substring(0, jTextArea.getText().length() - 1));
                            }catch (StringIndexOutOfBoundsException ex){

                            }
                        }
                    });

                    this.inputArea.add(jButton);
                    break;
                }
                case 2: {
                    JButton jButton = new JButton("M");
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String valor = jTextArea.getText().trim();
                            if(valor.isEmpty()){
                                if(memoria != null)
                                    jTextArea.append((String.format("%.2f", MainView.this.memoria) + "").replace('.', ','));
                                return;
                            }
                            char lastChar = valor.toCharArray()[valor.length() - 1];
                            if(Arrays.asList('x','/','+','-').contains(lastChar)){
                                jTextArea.append((String.format("%.2f", MainView.this.memoria) + "").replace('.', ','));
                            }else{
                                if(!(valor.contains("x") || valor.contains("/") || valor.contains("+") || valor.contains("-"))){
                                    memoria = Double.parseDouble(valor.replace(',', '.'));
                                }
                            }
                        }
                    });

                    this.inputArea.add(jButton);
                    break;
                }
                case 3: {
                    JButton jButton = new JButton("/");
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jTextArea.append(((JButton) e.getSource()).getText());
                        }
                    });

                    this.inputArea.add(jButton);
                    break;
                }
                case 4: {
                    JButton jButton = new JButton("7");
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jTextArea.append(((JButton) e.getSource()).getText());
                        }
                    });

                    this.inputArea.add(jButton);
                    break;
                }

                case 5: {
                    JButton jButton = new JButton("8");
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jTextArea.append(((JButton) e.getSource()).getText());
                        }
                    });

                    this.inputArea.add(jButton);
                    break;
                }

                case 6: {
                    JButton jButton = new JButton("9");
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jTextArea.append(((JButton) e.getSource()).getText());
                        }
                    });

                    this.inputArea.add(jButton);
                    break;
                }

                case 7: {
                    JButton jButton = new JButton("x");
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jTextArea.append(((JButton) e.getSource()).getText());
                        }
                    });

                    this.inputArea.add(jButton);
                    break;
                }
                case 8: {
                    JButton jButton = new JButton("4");
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jTextArea.append(((JButton) e.getSource()).getText());
                        }
                    });

                    this.inputArea.add(jButton);
                    break;
                }
                case 9: {
                    JButton jButton = new JButton("5");
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jTextArea.append(((JButton) e.getSource()).getText());
                        }
                    });

                    this.inputArea.add(jButton);
                    break;
                }
                case 10: {
                    JButton jButton = new JButton("6");
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jTextArea.append(((JButton) e.getSource()).getText());
                        }
                    });

                    this.inputArea.add(jButton);
                    break;
                }
                case 11: {
                    JButton jButton = new JButton("-");
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jTextArea.append(((JButton) e.getSource()).getText());
                        }
                    });

                    this.inputArea.add(jButton);
                    break;
                }
                case 12: {
                    JButton jButton = new JButton("1");
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jTextArea.append(((JButton) e.getSource()).getText());
                        }
                    });

                    this.inputArea.add(jButton);
                    break;
                }
                case 13: {
                    JButton jButton = new JButton("2");
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jTextArea.append(((JButton) e.getSource()).getText());
                        }
                    });

                    this.inputArea.add(jButton);
                    break;
                }

                case 14: {
                    JButton jButton = new JButton("3");
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jTextArea.append(((JButton) e.getSource()).getText());
                        }
                    });

                    this.inputArea.add(jButton);
                    break;
                }
                case 15: {
                    JButton jButton = new JButton("+");
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jTextArea.append(((JButton) e.getSource()).getText());
                        }
                    });

                    this.inputArea.add(jButton);
                    break;
                }

                case 16: {
                    JButton jButton = new JButton("0");
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jTextArea.append(((JButton) e.getSource()).getText());
                        }
                    });

                    this.inputArea.add(jButton);
                    break;
                }

                case 17: {
                    JButton jButton = new JButton(",");
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jTextArea.append(((JButton) e.getSource()).getText());
                        }
                    });

                    this.inputArea.add(jButton);
                    break;
                }

                case 18: {
                    JButton jButton = new JButton("=");
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String area = jTextArea.getText().trim();
                            if(area.contains("x") || area.contains("/") || area.contains("+")  || area.contains("-")){
                                char lastChar = area.toCharArray()[area.length() - 1];
                                if(Arrays.asList('x','/','+','-').contains(lastChar)){
                                    JOptionPane.showMessageDialog(MainView.this, "O último valor da operação não pode ser um operador!", "Aviso!", JOptionPane.ERROR_MESSAGE);

                                    return;
                                }
                                Double calcular = MainView.this.calculadoraService.calcular(area.replaceAll("[,]", ".").replaceAll("[x]", "*"));
                                jTextArea.setText(String.format("%.2f", calcular));

                            }
                        }
                    });

                    this.inputArea.add(jButton);
                    break;
                }
            }
        }
        this.add(this.inputArea, BorderLayout.CENTER);


    }
}
