Algoritmo "3. VERIFICAR SE UM NUMERO É PERFEITO"
// Autor(a)    : Edilson Alexnadre Cuamba
// Data atual  : 6/7/2021
Var
   // Seção de Declarações das variáveis
   divisorActual:inteiro
   somatorio:inteiro
   numero: inteiro

procedimento start()
var
   resultado: logico
inicio
   numero <- pedirValor()
   resultado <- verificaPerfeicao(numero)
   imprimirResultado(resultado)
fimprocedimento

funcao pedirValor():inteiro
var
   numero: inteiro
inicio
   Escreval("Por favor digite um valor seja ele inteiro ou inteiro")
   Escreva("para verificar se é perfeito: ")
   Leia(numero)
   retorne numero
fimfuncao

funcao verificaPerfeicao(numero:inteiro):logico
var
inicio
   divisorActual <- divisorActual+1
   se divisorActual <> numero entao
      se (numero MOD divisorActual) = 0 entao
         somatorio <- somatorio + divisorActual
         retorne verificaPerfeicao(numero)
      senao
         retorne verificaPerfeicao(numero)
      fimse
   senao
      se somatorio = numero entao
         retorne verdadeiro
      senao
         retorne falso
      fimse
   fimse
fimfuncao

procedimento imprimirResultado(resultado:logico)
var
inicio
   se resultado entao
      Escreval("O valor " + numpcarac(numero) + " é perfeito!")
   senao
      Escreval("O valor " + numpcarac(numero) + " não é perfeito!")
   fimse
fimprocedimento
Inicio
   // Seção de Comandos, procedimento, funções, operadores, etc...
   start()
Fimalgoritmo