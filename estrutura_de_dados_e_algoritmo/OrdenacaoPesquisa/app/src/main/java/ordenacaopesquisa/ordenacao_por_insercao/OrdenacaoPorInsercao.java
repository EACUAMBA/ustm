package ordenacaopesquisa.ordenacao_por_insercao;

import java.util.Arrays;

public class OrdenacaoPorInsercao {
  public static void main(String[] args) {
    System.out.println("Ordenação por Inserção!");
    int[] valores = { 1000, 1, 4, 3, 2, 5, 6, 7, 8, 3, 0, 1, 4, 5, 2, 4, 8, };

    System.out.println("Ordenação crescente!");
    System.out.println(Arrays.toString(valores));
    System.out.println("Array ordenado: " + Arrays.toString(ordenacaoCrescente(valores)));

    System.out.println();
    System.out.println("Ordenação decrescente!");
    System.out.println(Arrays.toString(valores));
    System.out.println("Array ordenado: " + Arrays.toString(ordenacaoDecrescente(valores)));
  }

  public static int[] ordenacaoCrescente(int[] valores) {

    //Iterando o array.
    for (int i = 0; i < valores.length; i++) {
      //Iterando o sub array entre a primeira posição até a posição actual. 
      for (int j = 0; j < i; j++) {

        //verificando se o elemento anterior é maior que o elemento actual.
        if (valores[j] > valores[i]) {

          //Se for maior vamos armazenar em uma variável temporária para troca.
          int valorTemporario = valores[i];

          //Vamos colocar o elemento maior na posição actual do i
          valores[i] = valores[j];

          //vamos colocar na posição j o elemento temporário que consagrou-se o menor.
          valores[j] = valorTemporario;
        }
        System.out.printf("[%d : %d] %s%n", i, j, Arrays.toString(valores));
      }
    }
    return valores;
  }

  public static int[] ordenacaoDecrescente(int[] valores) {

    //Iterando o array.
    for (int i = 0; i < valores.length; i++) {
      //Iterando o sub array entre a primeira posição até a posição actual. 
      for (int j = 0; j < i; j++) {

        //verificando se o elemento anterior é maior que o elemento actual.
        if (valores[j] < valores[i]) {

          //Se for maior vamos armazenar em uma variável temporária para troca.
          int valorTemporario = valores[i];

          //Vamos colocar o elemento maior na posição actual do i
          valores[i] = valores[j];

          //vamos colocar na posição j o elemento temporário que consagrou-se o menor.
          valores[j] = valorTemporario;
        }
        System.out.printf("[%d : %d] %s%n", i, j, Arrays.toString(valores));
      }
    }
    return valores;
  }
}
