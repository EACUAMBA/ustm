package models;

import java.util.ArrayList;
import java.util.List;

public class Comodo {
    public int comprimento;
    public int largura;


    public final List<Aspirador> aspiradores = new ArrayList<>();
    public final List<Sujidade> sujidades = new ArrayList<>();

    public void addAspirador(Aspirador aspirador) {
        aspirador.comodo = this;
        this.aspiradores.add(aspirador);
    }

    protected void limpar() {
        String reduced = this.aspiradores
                .stream()
                .map(aspirador -> aspirador.nome)
                        .reduce("", (s, s2) -> s.concat(", " + s2) );
        System.out.println("Limpando! com os aspiradores " + reduced);


    }

    public void addSujidade(Sujidade sujidade) {
        sujidade.comodo = this;
        this.sujidades.add(sujidade);
    }

    public void limparSujidade(Sujidade sujidade) {
        this.sujidades.remove(sujidade);
    }

    public boolean temSujidades(){
        return !this.sujidades.isEmpty();
    }
}
