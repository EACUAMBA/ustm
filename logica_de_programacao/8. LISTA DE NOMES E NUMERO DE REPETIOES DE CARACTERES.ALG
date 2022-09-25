Algoritmo "8. LISTA DE NOMES E NUMERO DE REPETIOES DE CARACTERES"
// Disciplina  :  [Linguagem e Lógica de Programação]
// Professor   : Antonio Carlos Nicolodi
// Descrição   : Aqui você descreve o que o programa faz! (função)
// Autor(a)    : Nome do(a) aluno(a)
// Data atual  : 06/08/21
Var
   // Seção de Declarações das variáveis
   valores: vetor [1..6] de caracter
   comprimento, posicao, repeticao, repeticaoTotal: inteiro
   alvo:caracter

procedimento start()
var
inicio
   comprimento <- 5
   input()
   verificaRepeticao()
fimprocedimento

procedimento verificaRepeticao()
var
inicio
   alvo<- valores[comprimento+1]
   percoreVetor()
fimprocedimento

procedimento percoreVetor()
var
   nome: caracter
   posicaoTemporaria:inteiro
inicio
   para posicao de 1 ate comprimento faca
      nome<- valores[posicao]
      repeticao<-0
      para posicaoTemporaria de 1 ate compr(nome) faca
         se copia(nome, posicaoTemporaria,1 ) = alvo entao
            repeticao <- repeticao+1
         fimse
      fimpara
      repeticaoTotal <- repeticaoTotal + repeticao
      Escreval("O caracter '" + alvo + "' aparece " + numpcarac(repeticao) + " vezes no nome " + nome)
   fimpara
   Escreval()
   Escreval("O caracter '" + alvo + "' aparece " + numpcarac(repeticaoTotal) + " em todo vetor.")
fimprocedimento

procedimento input()
var
   posicao:inteiro
inicio
   para posicao de 1 ate comprimento faca
      Escreva("Digita o nome do " + numpcarac(posicao) + " aluno: ")
      Leia(valores[posicao])
   fimpara
   Escreva("Digite o caracter que deseja verificar a repeticao nos nomes:")
   Leia(valores[comprimento+1])
fimprocedimento

Inicio
   // Seção de Comandos, procedimento, funções, operadores, etc...
   start()

Fimalgoritmo