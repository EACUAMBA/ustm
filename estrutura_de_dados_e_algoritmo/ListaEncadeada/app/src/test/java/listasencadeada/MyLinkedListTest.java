package listasencadeada;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import listaencadeada.ListaSimplementeEncadeada;

public class MyLinkedListTest {
  @Test
  public void myLinkedListEmptyReturn0() {
    var mll = new ListaSimplementeEncadeada<>();
    assertEquals("Size should be equal to 0 with no data inserted.", mll.tamanho(), 0);
  }

  public void myLinkedListEmptyReturnTrue() {
    var mll = new ListaSimplementeEncadeada<>();
    assertEquals("Empty should should return true with no data inserted.", mll.vazio(), true);
  }
}
