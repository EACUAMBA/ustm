package listaencadeada;

public class No<T> {
  protected T value;
  protected No proximo; // Guarda a referencia ao próximo elemento;

  public No() {

  }

  public No(T t) {
    this.value = t;
    this.proximo = null;
  }

  public No(T t, No next) {
    this.value = t;
    this.proximo = next;
  }
}
