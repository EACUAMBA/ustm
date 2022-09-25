Algoritmo "4. CALCULADORA"
// Autor(a)    : Edilson Alexandre Cuamba
// Data atual  : 6/7/2021
Var
   valores: vetor [1..3] de caracter

procedimento start()
var
   valor_1, valor_2: real
inicio
   input()
   valor_1 <- caracpnum(valores[1])
   valor_2 <- caracpnum(valores[2])

   escolha valores[3]
   caso "+"
      soma(valor_1, valor_2)
   caso "-"
      subtracao(valor_1, valor_2)
   caso "*"
      multiplicacao(valor_1, valor_2)
   caso "/"
      divisao(valor_1, valor_2)
   outrocaso
      Escreva("O sinal digitado é invalido!")
   fimescolha
fimprocedimento

procedimento input()
var
   //valores: vetor [1..3] de caracter
   contador: inteiro
inicio
   para contador de 1 ate 3 faca
      se contador < 3 entao
         Escreva("Insira o "+ numpcarac(contador) +" valor: ")
         Leia(valores[contador])
      senao
         Escreva(numpcarac(contador) + " - Insira o sinal da operação '+, -, /, *' :")
         Leia(valores[contador])
      fimse
   fimpara
fimprocedimento


procedimento soma(valor_1, valor_2:real)
var
inicio
   Escreval("O resultado da soma entre " + numpcarac(valor_1) + " e " + numpcarac(valor_2) + " é " + numpcarac(valor_1 + valor_2))
fimprocedimento

procedimento subtracao(valor_1, valor_2:real)
var
inicio
   Escreval("O resultado da subtração entre " + numpcarac(valor_1) + " e " + numpcarac(valor_2) + " é " + numpcarac(valor_1 - valor_2))
fimprocedimento

procedimento multiplicacao(valor_1, valor_2:real)
var
inicio
   Escreval("O resultado da multiplicação entre " + numpcarac(valor_1) + " e " + numpcarac(valor_2) + " é " + numpcarac(valor_1 * valor_2))
fimprocedimento

procedimento divisao(valor_1, valor_2:real)
var
inicio
   Escreval("O resultado da divisão entre " + numpcarac(valor_1) + " e " + numpcarac(valor_2) + " é " + numpcarac(valor_1 / valor_2))
fimprocedimento

Inicio
   start()
Fimalgoritmo