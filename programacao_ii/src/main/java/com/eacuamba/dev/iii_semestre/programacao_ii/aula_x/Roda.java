package com.eacuamba.dev.iii_semestre.programacao_ii.aula_x;

import java.applet.Applet;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Roda extends Applet {
    public void paint(Graphics graphics){
        final Integer numero_de_raios = 10;
        final Integer valor_de_incremento_do_raio = 10;
        final Integer raio_inicial = 10;
        final Integer largura = getSize().width;
        final Integer altura = getSize().height;
        final Integer x_cordenada_do_centro = largura /2;
        final Integer y_cordenada_do_centro = largura /2;
        int angulo_de_rotacao = 360 / numero_de_raios;
        int raio = raio_inicial;
        int incremento = valor_de_incremento_do_raio;
        int xf, yf, angolo, angolo_rot = 0;

        while(true){
            if(raio <= 0 || raio >= largura){
                incremento -= 1;
                raio += incremento;
            }
            graphics.drawOval(x_cordenada_do_centro - raio, y_cordenada_do_centro - raio, 2 *raio, 2* raio);
            for(angolo = 0; angolo< 360; angolo += angolo_rot){
                xf = x_cordenada_do_centro + (int)(raio * Math.cos(angolo * Math.PI/180));
                yf = y_cordenada_do_centro + (int)(raio * Math.cos(angolo * Math.PI/180));
                graphics.drawLine(x_cordenada_do_centro, y_cordenada_do_centro, xf, yf);
            }
            graphics.fillArc(x_cordenada_do_centro - raio, y_cordenada_do_centro - raio, 2 * raio, 2*raio, angolo_rot, incremento);
            angolo_rot = (angolo_rot + incremento)%360;

                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(2));
                } catch (InterruptedException e) {
                    System.out.println("Erro na thread: " + e.getMessage());
                }

        }
    }
}
