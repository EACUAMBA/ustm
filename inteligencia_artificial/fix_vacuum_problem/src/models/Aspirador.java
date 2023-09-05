package models;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class Aspirador {
    public String nome;
    public boolean onOff = false;
    public int comprimento;
    public int largura;

    public Comodo comodo;

    public void activar() {
        this.onOff = true;

        if (nonNull(comodo)) {
            this.comodo.limpar();
        }

        //
        int comprimentoDoComodo = this.comodo.comprimento;
        int larguraDoComodo = this.comodo.largura;

        int posicaoX = this.comprimento;
        int posicaoY = this.largura;

        comprimentoDoComodo -= this.comprimento;
        larguraDoComodo -= this.largura;

        while(comodo.temSujidades()){
            for (int l = this.largura; l <= this.comodo.largura; l = l + this.largura) {
                for (int c = this.comprimento; c <= this.comodo.comprimento; c = c + this.comprimento) {
                    List<Sujidade> sujidadesParaRemover = new ArrayList<>();
                    for (Sujidade sujidade : this.comodo.sujidades) {
                        sujidadesParaRemover.add(sujidade.estasAqui(c, l, this.comprimento, this.largura));
                    }
                    comodo.sujidades.removeAll(sujidadesParaRemover);
                }
            }

            for (int l = this.comodo.largura; l >= 10; l = l - this.largura) {
                for (int c = this.comodo.comprimento; c >= 10; c = c - this.comprimento) {
                    List<Sujidade> sujidadesParaRemover = new ArrayList<>();
                    for (Sujidade sujidade : this.comodo.sujidades) {
                        sujidadesParaRemover.add(sujidade.estasAqui(c, l, this.comprimento, this.largura));
                    }
                    comodo.sujidades.removeAll(sujidadesParaRemover);
                }
            }
        }


    }
}
