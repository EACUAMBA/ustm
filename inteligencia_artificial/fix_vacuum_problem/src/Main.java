import models.Aspirador;
import models.Comodo;
import models.Sujidade;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        //definir a area do nosso compo, pelo teclado
        Comodo comodo = new Comodo();
        comodo.comprimento = 200;
        comodo.largura = 100;

        //colocar aspirador no comodo
        Aspirador aspirador = new Aspirador();
        aspirador.comprimento = 10;
        aspirador.largura = 10;
        aspirador.nome = "Samsung";
        comodo.addAspirador(aspirador);

        Sujidade sujidade = new Sujidade();
        sujidade.comprimentoX = 25;
        sujidade.larguraY = 50;
        sujidade.comprimento = 5;
        sujidade.vezesNecessariasParaLimpar = 10;
        sujidade.largura = 5;
        comodo.addSujidade(sujidade);

        Sujidade sujidade5 = new Sujidade();
        sujidade5.comprimentoX = 60;
        sujidade5.larguraY = 90;
        sujidade5.comprimento = 5;
        sujidade5.vezesNecessariasParaLimpar = 3;
        sujidade5.largura = 5;
        comodo.addSujidade(sujidade5);

        Sujidade sujidade2 = new Sujidade();
        sujidade2.comprimentoX = 20;
        sujidade2.larguraY = 10;
        sujidade2.comprimento = 5;
        sujidade2.largura = 5;
        comodo.addSujidade(sujidade2);

        Sujidade sujidade3 = new Sujidade();
        sujidade3.comprimentoX = 180;
        sujidade3.larguraY = 80;
        sujidade3.comprimento = 5;
        sujidade3.largura = 5;
        comodo.addSujidade(sujidade3);

        //ao activar o aspirador limpa
        aspirador.activar();
    }
}