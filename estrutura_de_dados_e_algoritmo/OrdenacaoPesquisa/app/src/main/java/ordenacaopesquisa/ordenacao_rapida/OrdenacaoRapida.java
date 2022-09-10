package ordenacaopesquisa.ordenacao_rapida;

import java.security.SecureRandom;
import java.util.Arrays;

/**
 * OrdenaçãoRápida
 * Implementation from Code with John YouTube Channel.
 */
public class OrdenacaoRapida {
  private static SecureRandom secureRandom = new SecureRandom();

  public static void quicksort(int[] array) {
    quicksort(array, 0, (array.length - 1));
  }

  /*
   * Como este método será utilizado recursivamente, temos de passar o intervalo
   * que o quick sorte deverá utilizar para fazer a partição e a troca de
   * posições.
   */
  public static void quicksort(int[] array, int lowIndex, int highIndex) {
    if (lowIndex >= highIndex) {
      return;
    }
    int pivotIndex = secureRandom.nextInt(highIndex - lowIndex) + lowIndex;
    int pivot = array[pivotIndex];
    swap(array, pivotIndex, highIndex);
    int leftPointer = partition(array, lowIndex, highIndex, pivot);
    quicksort(array, lowIndex, (leftPointer - 1));
    quicksort(array, (leftPointer + 1), highIndex);
  }

  /*
   * Este método é responsável por pegar o array e dividir em duas partes, onde na parte a esquerda temos os valores menores que o pivô e na parte direita temos s valores maiores que o pivô.
   * Ele utiliza uma abordagem de apontadores, que apontam para as posições máximas e minímas. Ele vai comparando os valores, primeiro compara o valor a esquerda se é maior que  pivô depois verifica se o valor a direita é menor que o pivot, se ele achar esses valores, ele faz a troca dos valores utilizando outro método chamado swap;
   * 
   * Ele verifica se o ponteiro esquerdo ainda é menor que o ponteiro direito, se for, ele continua a procurar, nos próximos ciclos whiles ele verifica se o valor na posição do ponteiro esquerdo é menor ou igual que o pivô se não for ele fica nesse for ate ser, quando é ele passa para o próximo.
   * Agora com o outro ponteiro faz o mesmo procura o elemento que seja menor que o pivô quando acha ele troca as posição dos ponteiros.
   * Depois de tudo isso ele troca a posição do ponteiro a esquerda com o útimo elemento que é sempre o nosso pivô.
   */
  private static int partition(int[] array, int lowIndex, int highIndex, int pivot) {
    int leftPointer = lowIndex;
    int rightPointer = highIndex;
    while (leftPointer < rightPointer) {
      while (array[leftPointer] <= pivot && leftPointer < rightPointer) {
        leftPointer++;
      }
      while (array[rightPointer] >= pivot && leftPointer < rightPointer) {
        rightPointer--;
      }
      swap(array, leftPointer, rightPointer);
    }
    swap(array, leftPointer, highIndex);
    return leftPointer;
  }

  /**
   * Método utilizado para executar a troca de valores em um array.
   * @param array
   * @param sourceIndex
   * @param targetIndex
   */
  private static void swap(int[] array, int sourceIndex, int targetIndex) {
    int temporary = array[sourceIndex];
    array[sourceIndex] = array[targetIndex];
    array[targetIndex] = temporary;
  }

  public static void main(String[] args) {
    int size = 10_0000_000;
    int[] array = new int[size];
    for (int i = 0; i < size; i++) {
      array[i] = secureRandom.nextInt(size);
    }

    System.out.println("Unsorted: " + Arrays.toString(array));
    quicksort(array);
    System.out.println("Sorted: " + Arrays.toString(array));
  }
}