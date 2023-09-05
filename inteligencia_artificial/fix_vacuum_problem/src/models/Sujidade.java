package models;

public class Sujidade {
    public int comprimentoX;
    public int larguraY;

    public int comprimento;
    public int largura;

    public int vezesNecessariasParaLimpar = 1;
    public Comodo comodo;

    public Sujidade estasAqui(int comprimentoX, int larguraY, int aspiradorCompimento, int aspiradorLargura) {
        //System.out.printf("Verificando se a sujidade está aqui (X = %d, Y = %d)!%n", comprimentoX, larguraY);
        if (this.comprimentoX < comprimentoX
                && this.comprimentoX >= comprimentoX - aspiradorCompimento
                && this.larguraY < larguraY
                && this.larguraY >= larguraY - aspiradorLargura) {

            if(this.vezesNecessariasParaLimpar == 1) {
                System.out.printf("Sujidade encontrada no ponto X %d Y %d, limpando...%n", comprimentoX, larguraY);
                return this;
            }else{
                System.out.printf("Preciso de mais %d vezes para limpar;%n", this.vezesNecessariasParaLimpar);
                vezesNecessariasParaLimpar--;
            }
        }

        return null;
    }
}
