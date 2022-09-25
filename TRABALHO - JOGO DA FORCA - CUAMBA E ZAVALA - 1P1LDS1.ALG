Algoritmo "Trabalho - Jogo Da Forca - Cuamba e Zavala - 1P1LDS1"
// Disciplina   : [Linguagem e Lógica de Programação]
// Professor   : Antonio Carlos Nicolodi
// Descrição   : Jogo da Forca
// Autor(a)    : Edilson Alexandre Cuamba - 2021101069
// Autor(a)    : Edilson Zavala
// Data atual  : 6/14/2021
tipo

   jogador = registro
      nome: caracter
      vitorias, derrotas, desistencias, id: inteiro
   fimregistro


Var
   // Seção de Declarações das variáveis
   jogadores: vetor[1..5] de jogador
   velho_testamento_1, velho_testamento_2, novo_testamento: vetor[1..10] de caractere
   contador, opcao_menu, jogador_jogando_actual_id, conta_jogador, numero_de_jogadores: inteiro
   palavra_actual, jogador_jogando_actual_nome, tema_actual:caracter
   debugging:logico

procedimento iniciando()
var
inicio
   debugging<- falso
   Escreval("Inciando o Jogo...")
   carregando_palavras()
   numero_de_jogadores<-5
   repita
      opcao_menu <- menu()
      escolha opcao_menu
      caso 1
         preparando_jogo()
      caso 2
         ver_estatistica()
      caso 3
         escreval("Escolheste Sobre Nós")
         escreval()
         sobre_nos()
      fimescolha
   ate opcao_menu = 4
   sobre_nos()
fimprocedimento

procedimento ver_estatistica()
var
   total_geral, total, total_vitorias, total_derrotas, total_desistencias:inteiro

inicio
   escreval()
   escreval("Escolheste ver estatisticas")
   escreval("Area de Estatisticas!")
   escreval()
   para contador de 1 ate numero_de_jogadores faca
      se jogadores[contador].id <> 0 entao
         total<- (jogadores[contador].vitorias + jogadores[contador].derrotas + jogadores[contador].desistencias)
         escreval("Numero: ",jogadores[contador].id)
         escreval("Nome: ", jogadores[contador].nome)
         escreval("Vitorias(%): ", jogadores[contador].vitorias, " (",percentagem(jogadores[contador].vitorias,total),")")
         escreval("Derrotas(%): ", jogadores[contador].derrotas, " (",percentagem(jogadores[contador].derrotas, total),")")
         escreval("Desistencias(%): ", jogadores[contador].desistencias, " (",percentagem(jogadores[contador].desistencias, total),")")
         escreval("Total de Jogos dele: ", total)
         escreval()

         total_vitorias <- total_vitorias + jogadores[contador].vitorias
         total_derrotas <- total_derrotas + jogadores[contador].derrotas
         total_desistencias <- total_desistencias + jogadores[contador].desistencias
         total_geral <- total_geral + total
      fimse
   fimpara


   escreval("Totais Gerais")
   escreval("Vitorias(%): (",percentagem(total_vitorias, total_geral),")")
   escreval("Derrotas(%): (",percentagem(total_derrotas, total_geral),")")
   escreval("Desistencias(%): (",percentagem(total_desistencias, total_geral),")")
   escreval("Total de Jogos: ", total_geral)

fimprocedimento

funcao percentagem(parte, total:inteiro):real
inicio
   se total <> 0 entao
      retorne ((parte * 100) / total)
   senao
      retorne 0
   fimse

fimfuncao

procedimento preparando_jogo()
inicio
   escreval()
   escreval("Preparando Jogo...")
   jogador_jogando_actual_id <- 0
   //Listar jogadores registrados
   //Permitir o jogador selecionar um jogador que ja foi usado
   //permitir o jogador adicionar novo utilizador
   //depois iniciar o jogo
   limpatela
   se debugging entao
      desenha_pessoa_1()
      desenha_pessoa_2()
      desenha_pessoa_3()
      desenha_pessoa_4()
      desenha_pessoa_5()
      desenha_pessoa_6()
      desenha_pessoa_7()
      desenha_pessoa_8()
      desenha_pessoa_9()
      desenha_pessoa_10()
      desenha_pessoa_fim()
   fimse

   seleciona_ou_cria_jogador() //Obrigatorio...
   escolhendo_tema_palavra()
   limpatela
   se debugging entao
      escreval("Jogador selecionado é ", jogador_jogando_actual_nome, " com o id ", jogador_jogando_actual_id)
      escreval("Tema: " + tema_actual + ", palavra: " + palavra_actual)
   fimse
   jogando()

fimprocedimento

procedimento jogando()
var
   estado_do_jogo, letra_certa, numero_letras, letra_errada, contador_letras_digitadas, acertos_chute: inteiro
   palavra, espacos, letras_digitadas: vetor [1..100] de caracter
   jogada, resposta, letra_teste: caracter
   perdeu, ganhou, desistiu, terminou, ta_errada:logico
inicio
   limpatela
   estado_do_jogo<-1
   contador_letras_digitadas <- 1
   repita

      perdeu<-falso
      ganhou<-falso
      desistiu<-falso
      ta_errada<- verdadeiro
      letra_errada <- 0
      numero_letras <- compr(palavra_actual)
      imprimi_desenho_correspondente_ao_estado_do_jogo(estado_do_jogo)

      //Preenchendo os underscores correspondentes
      para contador de 1 ate  numero_letras faca
         se  espacos[contador] = "" entao
            espacos[contador] <- "_"
         fimse
         palavra[contador] <- copia(palavra_actual, contador, 1)
      fimpara

      //Escrevendo a dica
      escreval("Dica: " + tema_actual)

      //escrevendo as palavras digitadas
      escreva("Palavras digitadas: " )
      para contador de 1 ate 100 faca
         se isLetter(letras_digitadas[contador]) entao
            escreva(letras_digitadas[contador] + " ")
         fimse
      fimpara

      //Escrevendo os espaços _ _ _ _
      escreval()
      escreva("            ")
      para contador de 1 ate  numero_letras faca
         escreva(espacos[contador] + " ")
      fimpara
      escreval()


      //Lendo a jogada
      jogada <- ler_jogada()



      //Verificando se jogada corresponde a alguma letra
      para contador de 1 ate numero_letras faca
         se palavra[contador] = jogada entao
            espacos[contador] <- maiusc(jogada)
            ta_errada <- falso
         fimse
      fimpara

      //Configura a situacao no erro da letra
      se ta_errada entao
         se debugging entao
            escreval("Errou?: ", ta_errada)
            escreval("Letra: ", maiusc(jogada))
         fimse
         letras_digitadas[contador_letras_digitadas] <- maiusc(jogada)
         estado_do_jogo <- estado_do_jogo + 1
         contador_letras_digitadas <- contador_letras_digitadas + 1
      fimse

      //Verrifica se perdeu, errou o sufuciente
      se estado_do_jogo = 11 entao
         perdeu <- verdadeiro
      fimse
      perdeu_despedida(perdeu)

      //Verifica se Ganhou
      letra_certa <- 0
      para contador de 1 ate  numero_letras faca
         se espacos[contador] = "_" entao
            letra_certa <- letra_certa +1
         fimse
      fimpara
      ganhou <- (letra_certa = 0)
      ganhou_despedida(ganhou)

      //Verifica a palavra que chuou
      se caracpnum(jogada) = 1 entao
         escreval("Você escolheu chutar...")
         escreva("Escreva a palavra: ")
         leia(resposta)

         para contador de 1 ate  numero_letras faca
            se debugging entao
               escreval(copia(resposta, contador, 1))
            fimse

            letra_teste <-  copia(resposta, contador, 1)
            se palavra[contador] = letra_teste entao
               acertos_chute <- acertos_chute + 1
            fimse
         fimpara

         se numero_letras = acertos_chute entao
            ganhou <- verdadeiro
            ganhou_despedida(ganhou)
         senao
            perdeu <- verdadeiro
            perdeu_despedida(perdeu)
         fimse

         ta_errada<-falso
      fimse

      se caracpnum(jogada) = 2 entao
         desistiu<-verdadeiro
         desistiu_despedida(desistiu)
         ta_errada<-falso
      fimse


      terminou <- (perdeu ou ganhou ou desistiu)
      se debugging entao
         escreval("Numero de letras: " , numero_letras)
         escreval("Verfica letra: " , jogada)
         escreval("Terminou ", terminou)
      fimse
   ate terminou
fimprocedimento

procedimento desistiu_despedida(teste:logico)
inicio
   se teste entao
      escreval("")
      escreval("******************************************")
      escreval("     A palavra era ",maiusc(palavra_actual))
      escreval("******************************************")
      escreval("")
      escreval("*************************************************************************")
      escreval("     ",jogador_jogando_actual_nome," você desistiu e me deixou aqui!")
      escreval("*************************************************************************")
      escreval("")

      para contador de 1 ate numero_de_jogadores faca
         se jogadores[contador].id = jogador_jogando_actual_id entao
            jogadores[contador].desistencias <- jogadores[contador].desistencias +1
         fimse
      fimpara
   fimse
fimprocedimento

procedimento ganhou_despedida(teste:logico)
inicio
   se teste entao
      escreval("*")
      escreval("******************************************")
      escreval("     A palavra é ",maiusc(palavra_actual))
      escreval("******************************************")
      escreval()
      escreval("*************************************************************************")
      escreval("     ",jogador_jogando_actual_nome," você ganhou, parabens, você me salvou!")
      escreval("*************************************************************************")
      escreval("")

      para contador de 1 ate numero_de_jogadores faca
         se jogadores[contador].id = jogador_jogando_actual_id entao
            jogadores[contador].vitorias <- jogadores[contador].vitorias +1
         fimse
      fimpara
   fimse
fimprocedimento

procedimento perdeu_despedida(teste:logico)
inicio
   se teste entao
      imprimi_desenho_correspondente_ao_estado_do_jogo(11)
      escreval("*")
      escreval("******************************************")
      escreval("     A palavra era ",maiusc(palavra_actual))
      escreval("******************************************")
      escreval()
      escreval("*************************************************************************")
      escreval("     ",jogador_jogando_actual_nome," você perdeu!")
      escreval("*************************************************************************")
      escreval("")
      para contador de 1 ate numero_de_jogadores faca
         se jogadores[contador].id = jogador_jogando_actual_id entao
            jogadores[contador].derrotas <- jogadores[contador].derrotas +1
         fimse
      fimpara
   fimse
fimprocedimento

procedimento imprimi_desenho_correspondente_ao_estado_do_jogo(desenho:inteiro)
inicio
   limpatela
   escolha(desenho)
   caso 1
      desenha_pessoa_1()
   caso 2
      desenha_pessoa_2()
   caso 3
      desenha_pessoa_3()
   caso 4
      desenha_pessoa_4()
   caso 5
      desenha_pessoa_5()
   caso 6
      desenha_pessoa_6()
   caso 7
      desenha_pessoa_7()
   caso 8
      desenha_pessoa_8()
   caso 9
      desenha_pessoa_9()
   caso 10
      desenha_pessoa_10()
   caso 11
      desenha_pessoa_fim()
   fimescolha
fimprocedimento

funcao ler_jogada():caracter
var
   teste, teste_1, teste_2:logico
   resposta:caracter
inicio
   repita
      teste<- falso
      se debugging entao
         escreval(palavra_actual)
      fimse
      escreval()
      escreval("Quem esta jogando? R: ** ", jogador_jogando_actual_nome," **,")
      escreval("Você pode digitar a letra que falta OU")
      escreval("1 - Chutar (Caso erre perdeste o jogo):")
      escreval("2 - Desistir")
      escreva("Resposta: ")
      Leia(resposta)

      se resposta <> "" entao
         teste <- isLetter(resposta)
      fimse

      se caracpnum(resposta) = 1 entao
         teste<- verdadeiro
      fimse
      se caracpnum(resposta) = 2 entao
         teste<- verdadeiro
      fimse
   ate teste

   se debugging entao
      escreval("Sua resposta foi: ", resposta)
   fimse

   retorne resposta
fimfuncao

funcao isLetter(letra:caracter):logico
var
   teste, teste_1, teste_2, teste_3, teste_4, resposta:logico
inicio
   resposta<- falso

   teste_1 <- (asc(letra) > 64)
   teste_2 <- (asc(letra) < 90)
   teste_3 <- (asc(letra) > 96)
   teste_4 <- (asc(letra) < 123)


   se letra <> "" entao
      teste <- (teste_1 e teste_2) ou (teste_3 e teste_4)
      se teste entao
         resposta <- verdadeiro
      fimse
   fimse
   retorne resposta
fimfuncao

procedimento seleciona_ou_cria_jogador()
var
   teste_1, teste_2, teste, verifica_selecao:logico
   numero:inteiro
   valor:caracter
inicio
   jogador_jogando_actual_id <- 0
   jogador_jogando_actual_nome <- ""
   repita
      verifica_selecao <- verdadeiro
      Escreval("Você pode selecionar um jogador existente ou criar um novo!")
      conta_jogador<-1
      para contador de 1 ate numero_de_jogadores faca
         se jogadores[contador].id <> 0 entao
            conta_jogador<-conta_jogador+1
            escreval(jogadores[contador].id ," - " , jogadores[contador].nome)
         fimse
      fimpara
      escreval()

      se conta_jogador  = 1  entao
         Escreval("Parece que não tem nenhum jogador registrado, você é o primeiro.")
         cria_jogador(conta_jogador)
      fimse

      se conta_jogador > 1 entao
         escreval("Deseja usar um dos jogadores registrados?")
         escreva("Insira o numero dele ou o sinal mais (+) para inserir novo: ")
         valor <- ler_inteiro()
         se valor = "+" entao
            se conta_jogador > numero_de_jogadores entao
               verifica_selecao<- falso
               escreval()
               escreval("Limite de jogadores alcancado! Não é mais possivel adicionar jogador.")
               escreval()
            senao
               escreval("Adicionando novo jogador...")
               cria_jogador(conta_jogador)
            fimse
         fimse
      fimse
   ate verifica_selecao

fimprocedimento

funcao ler_inteiro():caracter
var
   valor:caracter
   teste_1, teste_2, teste:logico
   conta:inteiro
inicio
   teste<- verdadeiro
   repita
      se nao teste entao
         escreval()
         escreval("Insira um valor válido!")
      fimse
      teste<- falso

      Leia(valor)
      teste_1<- (caracpnum(valor) > 0)
      teste_2<- (caracpnum(valor) < (numero_de_jogadores + 1))
      teste<- (teste_1 e teste_2)

      se teste entao
         teste<-falso
         para contador de 1 ate numero_de_jogadores faca
            se jogadores[contador].id = caracpnum(valor) entao
               teste <- verdadeiro
               jogador_jogando_actual_id <- jogadores[contador].id
               jogador_jogando_actual_nome <- jogadores[contador].nome
               escreval("Jogador ", jogadores[contador].nome, " selecionnado com sucesso.")
            fimse
         fimpara
      fimse
      se valor = "+" entao
         teste <- verdadeiro
      fimse
   ate teste
   retorne valor
fimfuncao

procedimento cria_jogador(numero: inteiro)
inicio
   escreval("Insira os dados do Jogador ", numero,":")
   escreva("Nome: ")
   Leia(jogadores[numero].nome)
   jogadores[numero].id <- numero
   jogador_jogando_actual_id <- numero
   jogador_jogando_actual_nome <- jogadores[numero].nome
   escreval("Obrigado, Registro feito com sucesso")
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
var
   tema: inteiro
   palavra: inteiro
inicio
   tema<- randi(3) + 1
   palavra<- randi(10) + 1

   escolha(tema)
   caso 1
      tema_actual<- "Velho Testamento 1"
      palavra_actual <- velho_testamento_1[palavra]

   caso 2
      tema_actual<- "Velho Testamento 2"
      palavra_actual <- velho_testamento_2[palavra]

   caso 3
      tema_actual<- "Novo Testamento"
      palavra_actual <- novo_testamento[palavra]
   fimescolha

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
   se debugging entao
      Escreval("Carregando palavras para 'Velho Testamento 1'...")
   fimse
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
   se debugging entao
      Escreval("Carregando palavras para 'Velho Testamento 2'...")
   fimse
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
   se debugging entao
      Escreval("Carregando palavras para 'Novo Testamento'...")
   fimse
   novo_testamento[1] <- "Colossenses"
   novo_testamento[2] <- "Marcos"
   novo_testamento[3] <- "Mateus"
   novo_testamento[4] <- "Romanos"
   novo_testamento[5] <- "Atos"
   novo_testamento[6] <- "Filemon"
   novo_testamento[7] <- "Hebreus"
   novo_testamento[8] <- "Tiago"
   novo_testamento[9] <- "Joao"
   novo_testamento[10] <- "Lucas"
fimprocedimento

procedimento desenha_pessoa_1()
var
inicio
   escreval("        * Primeira tentativa! *")
   escreval()
   escreval("     *==============**")
   escreval("     ||             ||")
   escreval("     ||             ||")
   escreval("     ||        *************")
   escreval("     ||       *             *")
   escreval("     ||      *               *")
   escreval("     ||      *               *")
   escreval("     ||      *               *")
   escreval("     ||      *               *")
   escreval("     ||      *               *")
   escreval("     ||      *               *")
   escreval("     ||       ***************")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||___________________________________________")
   escreval()
fimprocedimento

procedimento desenha_pessoa_2()
var
inicio
   escreval("            * Segunda Tentativa *")
   escreval()
   escreval("     *==============**")
   escreval("     ||             ||")
   escreval("     ||             ||")
   escreval("     ||        ************")
   escreval("     ||       *   _______   *")
   escreval("     ||      *   |       |   *")
   escreval("     ||      *   |       |   *")
   escreval("     ||      *   |       |   *")
   escreval("     ||      *   |       |   *")
   escreval("     ||      *   |_______|   *")
   escreval("     ||       *             *")
   escreval("     ||        *************")
   escreval("     ||               ")
   escreval("     ||      ")
   escreval("     ||      ")
   escreval("     ||      ")
   escreval("     ||      ")
   escreval("     ||      ")
   escreval("     ||      ")
   escreval("     ||       ")
   escreval("     ||           ")
   escreval("     ||          ")
   escreval("     || ")
   escreval("     ||      ")
   escreval("     ||       ")
   escreval("     ||     ")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||___________________________________________")
   escreval()
fimprocedimento

procedimento desenha_pessoa_3()
var
inicio
   escreval("            * Terceira Tentativa, Me ajude! *")
   escreval()
   escreval("     *==============**")
   escreval("     ||             ||")
   escreval("     ||             ||")
   escreval("     ||        *************")
   escreval("     ||       *   _______   *")
   escreval("     ||      *   |       |   *")
   escreval("     ||      *   | 0   0 |   *")
   escreval("     ||      *   |       |   *")
   escreval("     ||      *   |       |   *")
   escreval("     ||      *   |_______|   *")
   escreval("     ||       *             *")
   escreval("     ||        **************")
   escreval("     ||              ")
   escreval("     || ")
   escreval("     || ")
   escreval("     || ")
   escreval("     ||")
   escreval("     || ")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     || ")
   escreval("     || ")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     || ")
   escreval("     ||___________________________________________")
   escreval()
fimprocedimento

procedimento desenha_pessoa_4()
var
inicio
   escreval("            * Quarta Tentativa, Me ajude! *")
   escreval()
   escreval("     *==============**")
   escreval("     ||             ||")
   escreval("     ||             ||")
   escreval("     ||        *************")
   escreval("     ||       *   _______   *")
   escreval("     ||      *   |       |   *")
   escreval("     ||      *   | 0   0 |   *")
   escreval("     ||      *   |   ^   |   *")
   escreval("     ||      *   |       |   *")
   escreval("     ||      *   |_______|   *")
   escreval("     ||       *             *")
   escreval("     ||        **************")
   escreval("     ||              ")
   escreval("     || ")
   escreval("     || ")
   escreval("     || ")
   escreval("     ||")
   escreval("     || ")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     || ")
   escreval("     || ")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     || ")
   escreval("     ||___________________________________________")
   escreval()
fimprocedimento

procedimento desenha_pessoa_5()
var
inicio
   escreval("            * Quinta Tentativa, Me ajude, por favor! *")
   escreval()
   escreval("     *==============**")
   escreval("     ||             ||")
   escreval("     ||             ||")
   escreval("     ||        *************")
   escreval("     ||       *   _______   *")
   escreval("     ||      *   |       |   *")
   escreval("     ||      *   | 0   0 |   *")
   escreval("     ||      *   |   ^   |   *")
   escreval("     ||      *   |  ===  |   *")
   escreval("     ||      *   |_______|   *")
   escreval("     ||       *             *")
   escreval("     ||        **************")
   escreval("     ||              ")
   escreval("     || ")
   escreval("     || ")
   escreval("     || ")
   escreval("     ||")
   escreval("     || ")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     || ")
   escreval("     || ")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     ||")
   escreval("     || ")
   escreval("     ||___________________________________________")
   escreval()
fimprocedimento

procedimento desenha_pessoa_6()
var
inicio
   escreval("         * Sexta  tentativa, essa palavra é fácil mano! *")
   escreval()
   escreval("     *==============**")
   escreval("     ||             ||")
   escreval("     ||             ||")
   escreval("     ||        ************")
   escreval("     ||       *   _______   *")
   escreval("     ||      *   |       |   *")
   escreval("     ||      *   | 0   0 |   *")
   escreval("     ||      *   |   ^   |   *")
   escreval("     ||      *   |  ===  |   *")
   escreval("     ||      *   |_______|   *")
   escreval("     ||      *       ||      *")
   escreval("     ||        ******||******")
   escreval("     ||              ||")
   escreval("     ||      =================")
   escreval("     ||          |       |")
   escreval("     ||          |       | ")
   escreval("     ||          |       |")
   escreval("     ||          |       | ")
   escreval("     ||          |       |")
   escreval("     ||          |       |")
   escreval("     ||          |_______|")
   escreval("     ||         ")
   escreval("     ||        ")
   escreval("     ||        ")
   escreval("     ||        ")
   escreval("     ||        ")
   escreval("     ||        ")
   escreval("     ||       ")
   escreval("     ||        ")
   escreval("     ||___________________________________________")
   escreval()
fimprocedimento

procedimento desenha_pessoa_7()
var
inicio
   escreval("            * Setima Tentativa, Vais me deixar aqui? *")
   escreval()
   escreval("     *==============**")
   escreval("     ||             ||")
   escreval("     ||             ||")
   escreval("     ||        ************")
   escreval("     ||       *   _______   *")
   escreval("     ||      *   |       |   *")
   escreval("     ||      *   | 0   0 |   *")
   escreval("     ||      *   |   ^   |   *")
   escreval("     ||      *   |  ===  |   *")
   escreval("     ||      *   |_______|   *")
   escreval("     ||      *       ||      *")
   escreval("     ||        ******||******")
   escreval("     ||              ||")
   escreval("     ||     ===================")
   escreval("     ||     | |  |       | ")
   escreval("     ||     | |  |       | ")
   escreval("     ||     | |  |       | ")
   escreval("     ||     | |  |       |  ")
   escreval("     ||     | |  |       |  ")
   escreval("     ||      \|  |       | ")
   escreval("     ||          |_______|")
   escreval("     ||         ")
   escreval("     ||      ")
   escreval("     ||       ")
   escreval("     ||       ")
   escreval("     ||    ")
   escreval("     ||      ")
   escreval("     ||    ")
   escreval("     ||       ")
   escreval("     ||___________________________________________")
   escreval()
fimprocedimento

procedimento desenha_pessoa_8()
var
inicio
   escreval("* Oitava tentativa, Eu não acredito, vais mesmo fazer isso? *")
   escreval("*************************************************************")
   escreval()
   escreval("     *==============**")
   escreval("     ||             ||")
   escreval("     ||             ||")
   escreval("     ||        ************")
   escreval("     ||       *   _______   *")
   escreval("     ||      *   |       |   *")
   escreval("     ||      *   | 0   0 |   *")
   escreval("     ||      *   |   ^   |   *")
   escreval("     ||      *   |  ===  |   *")
   escreval("     ||      *   |_______|   *")
   escreval("     ||      *       ||      *")
   escreval("     ||        ******||******")
   escreval("     ||              ||")
   escreval("     ||     ===================")
   escreval("     ||     | |  |       |  | |")
   escreval("     ||     | |  |       |  | |")
   escreval("     ||     | |  |       |  | |")
   escreval("     ||     | |  |       |  | |")
   escreval("     ||     | |  |       |  | |")
   escreval("     ||      \|  |       |  |/ ")
   escreval("     ||          |_______|")
   escreval("     ||  ")
   escreval("     ||  ")
   escreval("     ||  ")
   escreval("     ||  ")
   escreval("     || ")
   escreval("     || ")
   escreval("     ||  ")
   escreval("     || ")
   escreval("     ||___________________________________________")
   escreval()
fimprocedimento

procedimento desenha_pessoa_9()
var
inicio
   escreval("* Nona tentativa, so falta uma perna, socorro! SOS! *")
   escreval()
   escreval("     *==============**")
   escreval("     ||             ||")
   escreval("     ||             ||")
   escreval("     ||        ************")
   escreval("     ||       *   _______   *")
   escreval("     ||      *   |       |   *")
   escreval("     ||      *   | 0   0 |   *")
   escreval("     ||      *   |   ^   |   *")
   escreval("     ||      *   |  ===  |   *")
   escreval("     ||      *   |_______|   *")
   escreval("     ||      *       ||      *")
   escreval("     ||        ******||******")
   escreval("     ||              ||")
   escreval("     ||     ===================")
   escreval("     ||     | |  |       |  | |")
   escreval("     ||     | |  |       |  | |")
   escreval("     ||     | |  |       |  | |")
   escreval("     ||     | |  |       |  | |")
   escreval("     ||     | |  |       |  | |")
   escreval("     ||      \|  |       |  |/ ")
   escreval("     ||          |_______|")
   escreval("     ||         / /     ")
   escreval("     ||        / /     ")
   escreval("     ||       | |        ")
   escreval("     ||       | |        ")
   escreval("     ||       | |       ")
   escreval("     ||       | |       ")
   escreval("     ||       | |___    ")
   escreval("     ||       |_____|    ")
   escreval("     ||___________________________________________")
   escreval()
fimprocedimento

procedimento desenha_pessoa_10()
var
inicio
   escreval("* Decima tentativa, Essa é a ultima mano, eu vou morrer depois desta*")
   escreval()
   escreval("     *==============**")
   escreval("     ||             ||")
   escreval("     ||             ||")
   escreval("     ||        ************")
   escreval("     ||       *   _______   *")
   escreval("     ||      *   |       |   *")
   escreval("     ||      *   | 0   0 |   *")
   escreval("     ||      *   |   ^   |   *")
   escreval("     ||      *   |  ===  |   *")
   escreval("     ||      *   |_______|   *")
   escreval("     ||      *       ||      *")
   escreval("     ||        ******||******")
   escreval("     ||              ||")
   escreval("     ||     ===================")
   escreval("     ||     | |  |       |  | |")
   escreval("     ||     | |  |       |  | |")
   escreval("     ||     | |  |       |  | |")
   escreval("     ||     | |  |       |  | |")
   escreval("     ||     | |  |       |  | |")
   escreval("     ||      \|  |       |  |/ ")
   escreval("     ||          |_______|")
   escreval("     ||         / /     \ \")
   escreval("     ||        / /       \ \")
   escreval("     ||       | |        | |")
   escreval("     ||       | |        | |")
   escreval("     ||       | |        | |")
   escreval("     ||       | |        | |")
   escreval("     ||       | |___     | |___")
   escreval("     ||       |_____|    |_____|")
   escreval("     ||___________________________________________")
   escreval()
fimprocedimento


procedimento desenha_pessoa_fim()
var
inicio
   escreval("           * Você perdeu! Eu morri! *")
   escreval()
   escreval("     *==============**")
   escreval("     ||             ||")
   escreval("     ||             ||")
   escreval("     ||        ************")
   escreval("     ||       *   _______   *")
   escreval("     ||      *   |       |   *")
   escreval("     ||      *   | X   X |   *")
   escreval("     ||      *   |   ^   |   *")
   escreval("     ||      *   |  ===  |   *")
   escreval("     ||      *   |_______|   *")
   escreval("     ||      *       ||      *")
   escreval("     ||        ******||******")
   escreval("     ||              ||")
   escreval("     ||     ===================")
   escreval("     ||     | |  |       |  | |")
   escreval("     ||     | |  |       |  | |")
   escreval("     ||     | |  |       |  | |")
   escreval("     ||     | |  |       |  | |")
   escreval("     ||     | |  |       |  | |")
   escreval("     ||      \|  |       |  |/ ")
   escreval("     ||          |       |")
   escreval("     ||         / /     \ \")
   escreval("     ||        / /       \ \")
   escreval("     ||       | |        | |")
   escreval("     ||       | |        | |")
   escreval("     ||       | |        | |")
   escreval("     ||       | |        | |")
   escreval("     ||       | |        | |")
   escreval("     ||       \ \        \ \")
   escreval("     ||        \_\        \_\      ")
   escreval("     ||                                ")
   escreval("     ||___________________________________________")
   escreval()
fimprocedimento


procedimento sobre_nos()
inicio
   limpatela
   escreval("Copywrite 2021")
   escreval("Edilson Alexandre Cuamba - Edilson Zavala")

fimprocedimento

Inicio
   // Seção de Comandos, procedimento, funções, operadores, etc...
   iniciando()



   escreval("Fechando o jogo...")
Fimalgoritmo



