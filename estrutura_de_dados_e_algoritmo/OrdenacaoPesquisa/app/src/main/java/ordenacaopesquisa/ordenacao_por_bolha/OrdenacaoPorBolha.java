package ordenacaopesquisa.ordenacao_por_bolha;

import java.util.Arrays;

public class OrdenacaoPorBolha {
  public static void main(String[] args) {
    System.out.println("Ordenação por Bolha!");
    int[] valores = { 1000, 1, 4, 3, 2, 5, 6, 7, 8, 3, 0, 1, 4, 5, 2, 4, 8, };

    System.out.println("Ordenação crescente!");
    System.out.println(Arrays.toString(valores));
    System.out.println("Array ordenado: " + Arrays.toString(ordenacaoCrescente(valores)));

    // System.out.println();
    // System.out.println("Ordenação decrescente!");
    // System.out.println(Arrays.toString(valores));
    // System.out.println("Array ordenado: " +
    // Arrays.toString(ordenacaoDecrescente(valores)));
  }

  public static int[] ordenacaoCrescente(int[] valores) {
    //VIterando sobre a lista
    for (int i = 0; i < valores.length; i++) {

      //Percorrendo toda a lista novamente, para colocarmos o menor na primeira posição.
      for (int j = 0; j < valores.length; j++) {

        //Verificando se existe algum elemento que seja menor que o pivô.
        if(valores[i] < valores[j]){

          //Se tiver (um elemento menor que o pivô) armazena em uma variável temporária (para realizar a troca de posições).
          int valorMenorQueOSupostamenteMenorQueTodos = valores[j];

          //Pega o  elemento pivô e coloca na posição do elemento menor encontrado.
          valores[j] = valores[i];

          //Pega o valor menor encontrado coloca na posição do pivô, passa a ser pivô da comparação.
          valores[i] = valorMenorQueOSupostamenteMenorQueTodos;  
        }
        System.out.printf("[%d < %d] %s%n", valores[i], valores[j], Arrays.toString(valores));
      }
    }
    return valores;
  }
}
