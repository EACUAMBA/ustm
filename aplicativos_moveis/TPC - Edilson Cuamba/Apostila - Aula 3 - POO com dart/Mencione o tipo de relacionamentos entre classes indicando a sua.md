# Mencione o tipo de relacionamentos entre classes indicando a sua aplicabilidade?

Os tipos de relacionamentos entre classes são:

    Herança - este relacionamento permite a uma classe herdar atributos e comportamentos de outra classe, no DART utilizamos a palavra reservada extends para impor uma herança. 
    Exemplo: Funcionario extetnds Pessoa{}, neste caso todo funcionario tem os comportamentos e atributos de uma pessoa porque um funcionario é uma pessoa, mas note que nem todas as pessoas são funcionarios então coisas como codigo de funcionario nem todos terão.

    Implements - este relacionamento é entre classes e interfaces (tipo de classes que não tem metodos concretos e nem atributos) quando implementamo uma interface em uma classe não abstracta devemos implementar os metodos, em DART não temos a palavra interface para definir interface na verdade todas as classes são iplicitamente interfaces, mas como uma classe abstracta podemos definir uma interface basta não colocar atributos somente metodos abstractos. Exemplo: abstract class Voador{ void baterAsas()} class Passaro extends Animal implements Voador  {}

    Mixin - em DART podes utilizar oa mixins para reusar codigo de mais de uma classe, utilizamos apalavra WITH para dizer que uma classe reusa comportamentos de um mixin. Exemplo: mixin RealizaPagamentos{} Cliente with RealizaPagamentos{}