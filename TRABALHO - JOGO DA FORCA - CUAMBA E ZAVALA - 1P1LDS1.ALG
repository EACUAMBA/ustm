Algoritmo "Trabalho - Jogo Da Forca - Cuamba e Zavala - 1P1LDS1"
// Disciplina   : [Linguagem e Lógica de Programação]
// Professor   : Antonio Carlos Nicolodi
// Descrição   : Jogo da Forca
// Autor(a)    : Edilson Alexandre Cuamba - 2021101069
// Autor(a)    : Edilson Zavala
// Data atual  : 6/14/2021



Var
   // Seção de Declarações das variáveis
   temas: vetor[1..5] de caractere
   velho_testamento_1, velho_testamento_2, novo_testamento: vetor[1..10] de caractere
   contador, tema, palavra, opcao_menu: inteiro

procedimento iniciando()
var
inicio
   Escreval("Inciando o Jogo...")
   carregando_palavras()
   escolhendo_tema_palavra()
   opcao_menu <- menu()

fimprocedimento

funcao menu():inteiro
var
teste_1, teste_2, teste: logico
opcao:inteiro
inicio
   opcao <- 0
   Escreval()
   repita
      escreval("1 - Jogar")
      escreval("2 - Ver estatisticas")
      escreval("3 - Sobre nós")
      escreval("4 - Sair")

      Leia(opcao)
      
      teste_1 <- (opcao > 0)
      teste_2 <- (opcao < 5)
      teste <-  teste_1 e teste_2
      se nao teste entao
         limpatela
         Escreval()
         escreval("Insira um valor que consta na lista!")
      fimse
   ate teste
   limpatela
   escreva("Voce escolheu: " + numpcarac(opcao))
   retorne opcao
fimfuncao

procedimento escolhendo_tema_palavra()
inicio
   tema<- randi(5) + 1
   palavra<- randi(10) + 1
fimprocedimento

procedimento carregando_palavras()
var
inicio
   escreval("Carregando palavras...")
   carregando_velho_testamento_1()
   carregando_velho_testamento_2()
   carregando_novo_testamento()
fimprocedimento

procedimento carregando_velho_testamento_1()
var
inicio
   Escreval("Carregando palavras para 'Velho Testamento 1'...")
   velho_testamento_1[1] <- "Deuteronomio"
   velho_testamento_1[2] <- "Numeros"
   velho_testamento_1[3] <- "Neemias"
   velho_testamento_1[4] <- "Levitico"
   velho_testamento_1[5] <- "Genesis"
   velho_testamento_1[6] <- "Salmos"
   velho_testamento_1[7] <- "Esdras"
   velho_testamento_1[8] <- "Rute"
   velho_testamento_1[9] <- "Josue"
   velho_testamento_1[10] <- "Juizes"
fimprocedimento

procedimento carregando_velho_testamento_2()
var
inicio
   Escreval("Carregando palavras para 'Velho Testamento 2'...")
   velho_testamento_2[1] <- "Miqueias"
   velho_testamento_2[2] <- "Lamentacoes"
   velho_testamento_2[3] <- "Habacuque"
   velho_testamento_2[4] <- "Sofonias"
   velho_testamento_2[5] <- "Malaquias"
   velho_testamento_2[6] <- "Ezequiel"
   velho_testamento_2[7] <- "Oseias"
   velho_testamento_2[8] <- "Jeremias"
   velho_testamento_2[9] <- "Zacarias"
   velho_testamento_2[10] <- "Eclesiastes"
fimprocedimento

procedimento carregando_novo_testamento()
var
inicio
   Escreval("Carregando palavras para 'Novo Testamento'...")
   novo_testamento[1] <- "Miqueias"
   novo_testamento[2] <- "Lamentacoes"
   novo_testamento[3] <- "Habacuque"
   novo_testamento[4] <- "Sofonias"
   novo_testamento[5] <- "Malaquias"
   novo_testamento[6] <- "Ezequiel"
   novo_testamento[7] <- "Oseias"
   novo_testamento[8] <- "Jeremias"
   novo_testamento[9] <- "Zacarias"
   novo_testamento[10] <- "Eclesiastes"
fimprocedimento

procedimento fechando()
inicio
   limpatela
   escreval("Copywrite 2021")
   escreval("Edilson Alexandre Cuamba - Edilson Zavala")
   escreval("Fechando o jogo...")
fimprocedimento

Inicio
   // Seção de Comandos, procedimento, funções, operadores, etc...
   iniciando()




Fimalgoritmo