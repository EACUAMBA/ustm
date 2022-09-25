Algoritmo "1. Media Aritmetrica, Ponderada e Harmonica"
// Autor(a)    : Edilson Alexandre Cuamba
// Data atual  : 6/4/2021
Var
   // Seção de Declarações das variáveis
   notas: vetor [1..3] de real

procedimento start()
var
   contador:inteiro

inicio
   para contador de 1 ate 3 faca
      notas[contador] <- pedeValor(contador)
   fimpara
   queMedia()

fimprocedimento

procedimento queMedia()
var
   verificacao:logico
   forma:caracter
inicio
   repita
      verificacao <- verdadeiro
      Escreval("Deseja calcular como a media?")
      Escreval("A - Media Aritimetica")
      Escreval("P - Media Ponderada")
      Escreval("H - Media Harmonica")
      Leia(forma)

      escolha forma
      caso "A"
         mediaAritmetica()
      caso "P"
         mediaPonderada()
      caso "H"
         mediaHarmonica()
      outrocaso
         verificacao <- falso
      fimescolha
   ate verificacao
fimprocedimento

funcao pedeValor(numero:inteiro):real
var
   nota:real
inicio
   repita
      Escreva("Digita a nota do ")
      Escreva(numero)
      Escreval(" aluno: (Não digitar nada equivale a 0)")
      Leia(nota)
   ate nota >= 0
   retorne nota
fimfuncao

procedimento mediaAritmetica()
var
   media:real
inicio
   media <- (notas[1]+notas[2]+notas[3])/3;
   Escreva("A media Aritmetica é ")
   Escreva(media)
fimprocedimento

procedimento mediaPonderada()
var
   media:real
inicio
   media <- ((notas[1]*5)+(notas[2]*3)+(notas[3]*2))/(5+3+2)
   Escreva("A media Ponderada é ")
   Escreva(media)
fimprocedimento

procedimento mediaHarmonica()
var
   media:real
inicio
   media <- 3/((1/notas[1]) + (1/notas[2]) + (1/notas[3]))
   Escreva("A media Harmonica é ")
   Escreva(media)
fimprocedimento



Inicio
   // Seção de Comandos, procedimento, funções, operadores, etc...
   start()

Fimalgoritmo