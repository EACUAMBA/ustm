package ordenacaopesquisa.ordenacao_por_selecao;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class OrdenacaoPorSelecao {
  public static void main(String[] args) {
    System.out.println("Ordenação por seleção!");
    int[] valores = {1000, 1, 4, 3, 2, 5, 6, 7, 8, 3, 0, 1, 4, 5, 2, 4, 8,};

    System.out.println("Ordenação crescente!");
    System.out.println(Arrays.toString(valores));
    System.out.println("Array ordenado: " + Arrays.toString(valoresOrdenadosCrescente(valores)));

    System.out.println();
    System.out.println("Ordenação decrescente!");
    System.out.println(Arrays.toString(valores));
    System.out.println("Array ordenado: " + Arrays.toString(valoresOrdenadosDecrescente(valores)));
  }

  //Utilizando recursão.
  public static int[] valoresOrdenadosCrescente(int[] valores){
    //Percorrer todos os elementos da lista
    for(int i = 0; i < valores.length; i++){

      //Percorrer  a partir do elemento actual do for superior.
      for (int j = i; j < valores.length; j++) {

        //Verificando se existe algum elemento que seja menor que o pivô.
        if (valores[i] > valores[j]) {

          //Se tiver (um elemento menor que o pivô) armazena em uma variável temporária (para realizar a troca de posições).
          int valorMenorQueOSupostamenteMenorQueTodos = valores[j];

          //Pega o  elemento pivô e coloca na posição do elemento menor encontrado.
          valores[j] = valores[i];

          //Pega o valor menor encontrado coloca na posição do pivô, passa a ser pivô da comparação.
          valores[i] = valorMenorQueOSupostamenteMenorQueTodos;    
        }
        System.out.printf("[%d : %d] %s%n", i, j, Arrays.toString(valores));
      }
    }
    return valores;
  }


  public static int[] valoresOrdenadosDecrescente(int[] valores) {
    for(int i = (valores.length -1); i >=0; i--){
      for (int j = i; j >= 0; j--) {
        if (valores[i] > valores[j]) {
          int valorMaiorQueOSupostamenteMaiorQueTodos = valores[j];
          valores[j] = valores[i];
          valores[i] = valorMaiorQueOSupostamenteMaiorQueTodos;
        }
      }
    }
    return valores;
  }
}
