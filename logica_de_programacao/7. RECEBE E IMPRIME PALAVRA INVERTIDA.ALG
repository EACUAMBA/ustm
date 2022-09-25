Algoritmo "7. RECEBE E IMPRIME PALAVRA INVERTIDA"
// Autor(a)    : Edilson Alexandre Cuamba
// Data atual  : 06/08/21
Var
   // Seção de Declarações das variáveis
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
   // Seção de Comandos, procedimento, funções, operadores, etc...
   start()
Fimalgoritmo