package listaencadeada;

public interface Lista<T> {
  void inserir(T value);

  void inserirNaPosicao(int index, T value);

  void inserirNoFim(T value);

  T obter(int index);

  T remover();

  T removerNoFim();

  Boolean vazio();

  int tamanho();
}
