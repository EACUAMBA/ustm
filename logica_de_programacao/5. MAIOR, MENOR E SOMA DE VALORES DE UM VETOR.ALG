Algoritmo "5. MAIOR, MENOR E SOMA DE VALORES DE UM VETOR"
// Autor(a)    : Edilson Alexandre Cuamba
// Data atual  : 6/7/2021
Var
   valores: vetor [1..5] de inteiro
   maior, menor, somatorio: inteiro
   posicao: inteiro

procedimento start()
var
inicio
para posicao de 1 ate 5 faca
       Escreval("Digite o valor " + numpcarac(posicao))
       Leia(valores[posicao])
fimpara
posicao<-1
   menor<- valores[1]
   processamento()
fimprocedimento

procedimento processamento()
var
inicio
   se posicao > 5 entao
   Escreval("O maior numero é " + numpcarac(maior))
   Escreval("O menor numero é " + numpcarac(menor))
   Escreval("O somatorio é " + numpcarac(somatorio))
   senao
      somatorio<- somatorio + valores[posicao]
      se valores[posicao] > maior entao
         maior <- valores[posicao]
      fimse
      se (valores[posicao] < menor) entao
         menor <- valores[posicao]
      fimse
      posicao<- posicao + 1
      processamento()
   fimse
fimprocedimento

Inicio
   start()
Fimalgoritmo