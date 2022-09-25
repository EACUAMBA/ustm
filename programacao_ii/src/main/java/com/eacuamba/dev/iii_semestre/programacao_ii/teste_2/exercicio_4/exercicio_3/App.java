package com.eacuamba.dev.iii_semestre.programacao_ii.teste_2.exercicio_4.exercicio_3;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;

public class App extends Applet {
    @Override
    public void paint(Graphics g) {
        g.fillRect(0,0,500, 500);

        //head
        g.setColor(Color.yellow);
        g.fillOval(25, 25, 200, 200);

        //eyes
        g.setColor(Color.BLACK);
        g.fillOval(50, 75, 25, 50);
        g.fillOval(175, 75, 25, 50);

        //nose
        Polygon polygon = new Polygon();
        polygon.addPoint(125, 100);
        polygon.addPoint(135, 125);
        polygon.addPoint(115, 125);
        g.drawPolygon(polygon);

        //boca
        g.drawArc(60, 160, 140,40, 0, -180);
//        g.drawLine(50, 170, 70, 150);
//        g.drawLine(200, 170, 160, 150);
    }
}
