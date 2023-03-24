package listaencadeada;

import java.util.Objects;

public class ListaSimplementeEncadeada<T> implements Lista<T> {
  private No no;
  private int contador = 0;

  public ListaSimplementeEncadeada() {
    no = new No<T>();
  }

  public void inserir(T value) {
    if (Objects.isNull(this.no.value)) {
      this.no.value = value;
    } else {
      if (Objects.isNull(this.no.proximo)) {
        this.no.proximo = new No<>(value);
      } else {
        No node = this.no.proximo;
        boolean mycontinue = true;
        do {
          if (Objects.isNull(node.proximo)) {
            node.proximo = new No<>(value);
          } else {
            node = node.proximo;
          }
        } while (mycontinue);
      }
    }
  }

  @Override
  public void inserirNaPosicao(int index, T value) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void inserirNoFim(T value) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public T obter(int index) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public T remover() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public T removerNoFim() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean vazio() {
    return contador == 0;
  }

  @Override
  public int tamanho() {
    // TODO Auto-generated method stub
    return contador;
  }
}
