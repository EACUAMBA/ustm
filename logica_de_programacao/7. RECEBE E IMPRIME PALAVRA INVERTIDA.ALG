Algoritmo "7. RECEBE E IMPRIME PALAVRA INVERTIDA"
// Autor(a)    : Edilson Alexandre Cuamba
// Data atual  : 06/08/21
Var
   // Se��o de Declara��es das vari�veis
   palavra:caracter
   contador: inteiro

procedimento start()
var
inicio
   Escreva("Digite a palavra que deseja inverter: ")
   Leia(palavra)
   invertePalavra(palavra)
fimprocedimento

procedimento invertePalavra(palavra:caracter)
var
   posicao:inteiro
inicio
   posicao <- compr(palavra)
   para contador de 1 ate compr(palavra) faca
      Escreva(copia(palavra, posicao, 1))
      posicao <- posicao - 1
   fimpara
fimprocedimento

Inicio
   // Se��o de Comandos, procedimento, fun��es, operadores, etc...
   start()
Fimalgoritmo