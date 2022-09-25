Algoritmo "2. verificar se é numero primo"
// Autor(a)    : Edilson Alexandre Cuamba
// Data atual  : 6/7/2021
Var
   valor, divisor: inteiro

procedimento start()
var
   resultado: logico
inicio
   divisor <- 2
   valor <- pedirValor()
   resultado <- verificaSeEhPrimo(valor)
   Escreval()
   se resultado entao
      Escreval("V")
      Escreval("O valor " + numpcarac(valor) + " é Primo")
   senao
      Escreval("F")
      Escreval("O valor " + numpcarac(valor) + " não é Primo")
   fimse
fimprocedimento

funcao pedirValor():inteiro
var
   numero, divisor: inteiro
inicio
   Escreval("Resultado V significa que é primo, F que não é primo.")
   Escreva("Por favor digite uma valor para verificar se é primo: ")
   Leia(numero)
   retorne numero
fimfuncao

//Um valor só é primo se for divisiver por 1 e por se mesmo e caso seja maior
//que 9 será primo se a divisão por valores de 2 até 9 terem o resto diferente
//de zero (0)
funcao verificaSeEhPrimo(numero:inteiro):logico
var
inicio
Escreval(numero)
   se divisor <> numero  entao
      se(numero MOD divisor) <> 0 entao
         divisor <- divisor + 1
         se divisor > 9 entao
            retorne verdadeiro
         senao
            retorne verificaSeEhPrimo(numero)
         fimse
      senao
         retorne falso
      fimse
   senao
      divisor <- divisor + 1
      retorne verificaSeEhPrimo(numero)
   fimse
fimfuncao

Inicio
   // Seção de Comandos, procedimento, funções, operadores, etc...
   start()
Fimalgoritmo