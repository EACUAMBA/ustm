package com.eacuamba.dev.iii_semestre.trabalho_individual_2.conversor_de_moedas;

import com.eacuamba.dev.iii_semestre.trabalho_individual_2.conversor_de_moedas.models.Moeda;
import com.eacuamba.dev.iii_semestre.trabalho_individual_2.conversor_de_moedas.views.MainView;

import java.util.Comparator;
import java.util.LinkedList;

public class App {
    public static void main(String[] args) {
        MainView mainView = new MainView();
        mainView.setVisible(true);

        LinkedList<Moeda> linkedList = new LinkedList<>();
        linkedList.add(new Moeda("Dollar Americano", 63.2));
        linkedList.add(new Moeda("Rand", 4.0));
        linkedList.add(new Moeda("Euro", 67.0));
        linkedList.add(new Moeda("Libra", 81.0));
        linkedList.sort(new Comparator<Moeda>() {
            @Override
            public int compare(Moeda o1, Moeda o2) {
                return o1.getNome().compareTo(o2.getNome());
            }
        });
        mainView.setMoedaJComboBoxValue(linkedList);
    }
}
