package com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_individual_2.conversor_de_moedas.views;

import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_individual_2.conversor_de_moedas.models.Moeda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Locale;

public class MainView extends JFrame {
    private JTextField valor = new JTextField();
    private JTextField resultado = new JTextField();
    private JComboBox<Moeda> moedaJComboBox = new JComboBox<>();

    public MainView() {
        this.setLayout(new FlowLayout());
        this.setSize(450, 220);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3, 2, 16, 16));
        jPanel.setSize(430, 200);

        jPanel.add(new JLabel("Valor em Meticais"));
        this.valor.setText(1 + "");
        jPanel.add(this.valor);

        jPanel.add(new JLabel("Moeda"));
        jPanel.add(this.moedaJComboBox);
        this.moedaJComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Moeda selectedItem = (Moeda) ((JComboBox<Moeda>) e.getSource()).getSelectedItem();
                if(!selectedItem.getPrecoEmMeticais().equals(0D)) {
                    double v = Double.parseDouble(MainView.this.valor.getText()) / selectedItem.getPrecoEmMeticais();
                    MainView.this.resultado.setText(NumberFormat.getCurrencyInstance(new Locale("pt")).format(v));
                }else {
                    MainView.this.resultado.setText("");
                }
            }
        });

        jPanel.add(new JLabel("Resultado"));
        this.resultado.setEnabled(false);
        this.resultado.setForeground(Color.BLACK);
        jPanel.add(this.resultado);

        this.add(jPanel);
    }
    public void setMoedaJComboBoxValue(LinkedList<Moeda> moedas){
        this.moedaJComboBox.removeAllItems();
        moedas.push(new Moeda("Selecione a moeda!", 0D));

        moedas.forEach(moeda -> this.moedaJComboBox.addItem(moeda));
    }
}
