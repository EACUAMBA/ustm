Algoritmo "6. UMA FUNCAO PARA A,B,C,D,E"
// Autor(a)    : Edilson Alexandre Cuamba
// Data atual  : 6/7/2021
Var
   // Seção de Declarações das variáveis
   valores: vetor [1..5] de inteiro
   comprimento, contador, somatorio, posicao, produto: inteiro
   
procedimento start()
var
   res, res_produto:inteiro
inicio
   comprimento<-5
   para contador de 1 ate comprimento faca
      Escreva("Escreva o valor do " + numpcarac(contador) + " elemento: ")
      Leia(valores[contador])
   fimpara
   escreve_de_zero_ate_n(1)
   escreval()
   escreve_de_n_ate_zero(1)
   escreval()
   res <- escreve_a_soma_de_numeros_inteiros(0)
   escreval("O resultado do somatorio dos inteiro é " + numpcarac(res))
   
   res_produto <- escreve_a_produto_de_numeros_inteiros(0)
   escreval("O resultado do produto dos inteiro é " + numpcarac(res_produto))
   
   res_produto <- escreve_a_produto_do_quadrado_dos_numeros_inteiros(0)
   escreval("O resultado do produto do quadrado dos inteiros é " + numpcarac(res_produto))
fimprocedimento

procedimento escreve_de_n_ate_zero(posicao:inteiro)
var
   varloreTemporario:inteiro
inicio
   se posicao = comprimento entao
      para contador de 1 ate comprimento faca
         Escreva(numpcarac(valores[contador]) + " - ")
      fimpara
   senao
      para contador de posicao ate comprimento faca
         se valores[posicao] < valores[contador] entao
            varloreTemporario <- valores[posicao]
            valores[posicao]<- valores[contador]
            valores[contador]<- varloreTemporario
         fimse
      fimpara
      posicao<- posicao+1
      escreve_de_n_ate_zero(posicao)
   fimse
fimprocedimento

funcao escreve_a_soma_de_numeros_inteiros(posicao:inteiro):inteiro //Retornamos a soma total dos valores do vetor
var
inicio
//Incrementamos uma unidade na nossa variavel de escape
   posicao <- posicao + 1
   se posicao = comprimento entao
   //Retornamos o ultimo valor do array/vetor valores[limite_superior]
      retorne valores[posicao]
   senao
   //Somamos o valor retornado e o valor da posicao actual
      produto <- escreve_a_soma_de_numeros_inteiros(posicao) + valores[posicao]
      //retornamos o resultado da soma para voltar a somar e retornar
      retorne produto
   fimse
fimfuncao

funcao escreve_a_produto_de_numeros_inteiros(posicao:inteiro):inteiro
var
inicio
   posicao <- posicao + 1
   se posicao = comprimento entao
      retorne valores[posicao]
   senao
      somatorio <- escreve_a_produto_de_numeros_inteiros(posicao) * valores[posicao]
      retorne somatorio
   fimse
fimfuncao

funcao escreve_a_produto_do_quadrado_dos_numeros_inteiros(posicao:inteiro):inteiro
var
inicio
   posicao <- posicao + 1
   se posicao = comprimento entao
      retorne Quad(valores[posicao])
   senao
      somatorio <- escreve_a_produto_do_quadrado_dos_numeros_inteiros(posicao) * Quad(valores[posicao])
      retorne somatorio
   fimse
fimfuncao

procedimento escreve_de_zero_ate_n(posicao:inteiro)
var
   varloreTemporario:inteiro
inicio
   se posicao = comprimento entao
      para contador de 1 ate comprimento faca
         Escreva(numpcarac(valores[contador]) + " - ")
      fimpara
   senao
      para contador de posicao ate comprimento faca
         se valores[posicao] > valores[contador] entao
            varloreTemporario <- valores[posicao]
            valores[posicao]<- valores[contador]
            valores[contador]<- varloreTemporario
         fimse
      fimpara
      posicao<- posicao+1
      escreve_de_zero_ate_n(posicao)
   fimse
fimprocedimento

Inicio
   start()
Fimalgoritmo