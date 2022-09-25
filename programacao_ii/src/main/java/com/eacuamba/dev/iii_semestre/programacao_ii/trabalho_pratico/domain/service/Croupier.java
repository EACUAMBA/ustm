/*
 * Copyright (c) Edilson Alexandre Cuamba - 4 - 6 - 2022
 */

package com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.service;

import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.exceptions.ProximaCartaDeveSerNumeroDois;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.exceptions.ProximaCartaInvalida;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models.Carta;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models.CartaJogador;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models.Jogador;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models.Mesa;
import com.eacuamba.dev.utils.Utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Croupier {
    private static final Croupier croupier = new Croupier();

    public static Croupier getInstance() {
        return croupier;
    }

    private final Mesa mesa;

    private Croupier() {
        this.mesa = new Mesa();
    }

    public String jogarCarta(CartaJogador cartaJogador) throws ProximaCartaDeveSerNumeroDois, ProximaCartaInvalida {
        StringBuilder msg = new StringBuilder();

        boolean validaProximaCarta = validarCartaJogada(cartaJogador.getCarta());
        if (validaProximaCarta) {
            aceitarCartaDoJogador(cartaJogador);
            boolean houveVencedor = this.houveVencedor(cartaJogador.getJogador());
            if(houveVencedor){
                msg.append(String.format("%s venceu a ronda %d do jogo.%n%n", cartaJogador.getJogador().getPessoa().getNome(), this.mesa.getRondas()));
            }
            if (cartaJogador.getCarta().getValor().equals(7)) {
                this.mesa.proximoJogador(1);

            }else
            if (cartaJogador.getCarta().getDesignacao().equals("J")) {
                this.mesa.proximoJogador(-2);
            }else
                this.mesa.proximoJogador(0);

            msg.append(String.format("%s jogou %s, agora é a vez do Jogador é %s.", cartaJogador.getJogador().getPessoa().getNome(), cartaJogador.getCarta().getDetalhe(), this.mesa.getJogador().getPessoa().getNome()));

        }
        return msg.toString();
    }

    private boolean validarCartaJogada(Carta carta) throws ProximaCartaDeveSerNumeroDois, ProximaCartaInvalida {
        LinkedList<Carta> cartas = new LinkedList<>(this.mesa.getCartasDisponiveis());
        boolean remove = cartas.remove(carta);
        boolean proximaCarta = validaProximaCarta(carta);
        if (proximaCarta) {
            return true;
        } else {
            throw new ProximaCartaInvalida("A sua próxima carta deve ser do mesmo naipe, ou com a mesma designacao, caso não tenha peça uma carta");
        }
    }

    public void pedirCarta(int numeroDeCartas, Jogador jogador) {
        jogador = getJogadorRealInstance(jogador.getPosicao()).get();
        if (this.mesa.getCartasDisponiveis().size() < numeroDeCartas) {
            List<Carta> cartaList = this.getCartaFromCartaJogador(this.mesa.getCartasJogadas());
            cartaList.addAll(this.mesa.getCartasDisponiveis());
            List<Carta> cartas = Utils.baralhar(cartaList, 10);
            cartaList.clear();
            cartaList.addAll(cartas);
            this.mesa.getCartasDisponiveis().clear();
            this.mesa.getCartasJogadas().clear();

            LinkedList<Carta> cartaLinkedList = new LinkedList<>(cartaList);
            for (int i = 1; i <= numeroDeCartas; i++) {
                if (!cartaLinkedList.isEmpty())
                    jogador.getCartas().add(cartaLinkedList.pop());
            }

            this.mesa.getCartasDisponiveis().addAll(cartaLinkedList);
            this.mesa.proximoJogador(0);
        } else {
            LinkedList<Carta> cartaLinkedList = new LinkedList<>(this.mesa.getCartasDisponiveis());
            for (int i = 1; i <= numeroDeCartas; i++) {
                if (!cartaLinkedList.isEmpty())
                    jogador.getCartas().add(cartaLinkedList.pop());
            }
            this.mesa.proximoJogador(0);
        }
    }

    /**
     * Está funcao serve para validar os casos"A primeira jogada de uma ronda pode ser feita com qualquer carta.
     * As jogadas subsequentes devem ser do mesmo naipe ou do mesmo número da carta que
     * estiver por em cima.
     * No caso de o jogador não ter nenhuma carta que possa ser jogada, ele pede ao croupier uma
     * carta e a sua vez passa para o jogador seguinte. "
     *
     * @param carta
     * @return
     * @throws ProximaCartaDeveSerNumeroDois
     */
    public boolean validaProximaCarta(Carta carta) throws ProximaCartaDeveSerNumeroDois {
        if (this.mesa.getCartasJogadas().isEmpty())
            return true;
        if (verificaMesmoNaipe(carta))
            return true;
        else
            return verificaMesmoNumero(carta);
    }


    private boolean verificaMesmoNaipe(Carta carta) {
        CartaJogador last = new LinkedList<>(this.mesa.getCartasJogadas()).getLast();
        return (carta.getNaipe().equals(last.getCarta().getNaipe()));
    }

    private boolean verificaMesmoNumero(Carta carta) throws ProximaCartaDeveSerNumeroDois {
        CartaJogador last = new LinkedList<>(this.mesa.getCartasJogadas()).getLast();
        this.validaCartaNumeroDois(last.getCarta(), carta);
        if (validaMudaNaipe(carta))
            return true;
        return (carta.getDesignacao().equals(last.getCarta().getDesignacao()));
    }

    /**
     * Esta validacao valida o caso: "Para todas as cartas jogadas (com excepção da carta 2), o jogador pode jogar uma carta 8 de
     * qualquer naipe e mudar o naipe do jogo para qualquer a sua escolha. "
     *
     * @param carta
     * @return
     */
    public boolean validaMudaNaipe(Carta carta) {
        return carta.getDesignacao().equals("8");
    }

    /**
     * Verifica se a carta é valida segundo a regra "Se o jogador anterior jogar a carta com o número 2, então o jogador seguinte é obrigado a
     * jogar uma carta com o número 2."
     * Retorna:
     * 1. caso a anterior não seja 2,
     * 2. caso a anterios seja 2 e a proxima seja 2,
     * 3. caso a anterior seja 2 e a proxima seja diferente de 2
     *
     * @param anteriorCarta
     * @param proximaCarta
     * @return 1. caso a anterior não seja 2;
     * 2. caso a anterios seja 2 e a proxima seja 2;
     * throws new ProximaCartaDeveSerNumeroDois();
     */
    private Integer validaCartaNumeroDois(Carta anteriorCarta, Carta proximaCarta) throws ProximaCartaDeveSerNumeroDois {
        if (!anteriorCarta.getDesignacao().equals("2")) return 1;
        if (proximaCarta.getDesignacao().equals("2")) return 2;
        int cartasDeValor2JogadasAteAgora = somarCartasDeValor2JogadasAteAgora();
        throw new ProximaCartaDeveSerNumeroDois(String.format("A próxima carta deve ser carta dois, ou pede (%d) cartas ao croupier.", cartasDeValor2JogadasAteAgora), anteriorCarta, proximaCarta, cartasDeValor2JogadasAteAgora);
    }

    /**
     * Essa função serve para o caso: "No caso de não a ter carta 2, são somados os valores das cartas de
     * valor 2 jogadas de forma sequencial até a última em causa e o jogador pede ao croupier o
     * número de cartas equivalente a soma feita e sua vez passa para o jogador seguinte. "
     *
     * @return
     */
    public int somarCartasDeValor2JogadasAteAgora() {
        int sum = this.getCartaFromCartaJogador(this.mesa.getCartasJogadas())
                .stream().filter(carta -> carta.getDesignacao().equals("2"))
                .mapToInt(Carta::getValor)
                .sum();
        return sum;

    }

    private void colocarCartaNaMesa(CartaJogador cartaJogador) {
        this.mesa.getCartasJogadas().add(cartaJogador);
        Optional<Jogador> jogadorRealInstance = this.getJogadorRealInstance(cartaJogador.getJogador().getPosicao());
        jogadorRealInstance.ifPresent(jogador -> jogador.getCartas().removeIf(carta -> carta.equals(cartaJogador.getCarta())));
    }

    private void aceitarCartaDoJogador(CartaJogador cartaJogador) {
        this.mesa.getCartasJogadasAoLongoDoJogo().add(cartaJogador);
        Jogador jogador = cartaJogador.getJogador();
        colocarCartaNaMesa(cartaJogador);
    }

    /**
     * Este codigo esta de acordo com: "O objetivo é jogar todas as cartas até ficar sem nenhuma, antes que os outros jogadores o
     * consigam. Esta situação marca o fim de uma ronda e o vencedor é quem fica sem cartas.
     * Quando isto acontece, o jogo é interrompido e imediatamente são somados os valores das
     * cartas que cada jogador que não tenha ganho tem na sua posse e esse valor do somatório é
     * guardado no número de pontos do jogador (o Jogador vencedor terá somado nessa ronda 0
     * pontos).
     * O jogador que atingir os cem pontos perde e saí do jogo. Continuando assim, os outros que
     * ainda não completaram os cem pontos.
     * O vencedor será aquele que ficar por último. "
     */
    private boolean houveVencedor(Jogador jogador){
        if(!jogador.getCartas().isEmpty())
            return false;

        this.mesa.getJogadoresVencedores().add(jogador);
        jogador.setJogando(false);
        //this.mesa.getJogadoresRestantes().removeIf(_jogador -> _jogador.getPessoa().getNome().equals(jogador.getPessoa().getNome()));
        this.calcularPontuacao();
        return true;
    }

    private void calcularPontuacao(){
        for(Jogador jogador : this.mesa.getJogadoresRestantesJogando()){
            jogador.setPontosSomados(jogador.getCartas().stream().mapToInt(Carta::getValor).sum());
        }
    }

    public Jogador adicionarNovoJogador(Jogador jogador) {
        if(this.mesa.getJogadoresRestantes().size() >= 2)return null;
        List<Jogador> jogadoresRestantes = this.mesa.getJogadoresRestantes();
        List<Integer> integerList = jogadoresRestantes.stream().map(Jogador::getPosicao).sorted(Integer::compareTo).collect(Collectors.toList());
        jogador.setPosicao(Integer.sum(integerList.size(), 1));
        this.mesa.getJogadoresRestantes().add(jogador);
        return jogador;
    }

    public void iniciarJogo() {
        this.distribuirCartas();
        Jogador jogador = this.mesa.escolherPrimeiroJogador(this.getJogadores());
        jogador.setActual(true);
    }

    /**
     * 1. Levar (remover da lista origem) 6 cartas para cada jogador;
     * 2. Adiciona essas 6 cartas na propriedade cartas de cada jogador;
     */
    private void distribuirCartas() {
        List<Jogador> jogadores = this.mesa.getJogadoresRestantesJogando();
        LinkedList<Carta> cartaLinkedList = new LinkedList<>(this.mesa.getCartasDisponiveis());
        for (Jogador jogador : jogadores) {
            for (int i = 1; i <= 6; i++) {
                if (!cartaLinkedList.isEmpty())
                    jogador.getCartas().add(cartaLinkedList.pop());
            }
        }
        this.mesa.getCartasDisponiveis().clear();
        this.mesa.getCartasDisponiveis().addAll(cartaLinkedList);
    }

    public String estadoDaMesa() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Primeiro Jogador: ").append(this.mesa.escolherPrimeiroJogador(this.mesa.getJogadoresRestantes())).append("\n");
        stringBuilder.append("Jogadores").append("\n");
        this.mesa.getJogadoresRestantes().forEach(jogador -> stringBuilder.append(jogador).append("\n"));
        stringBuilder.append("\nCartas").append("\n");
        this.mesa.getCartasDisponiveis().forEach(carta -> stringBuilder.append(carta).append("\n"));
        stringBuilder.append("\nCartas Disponiveis: ").append(this.mesa.getCartasDisponiveis().size()).append("\n");
        return stringBuilder.toString();
    }

    public List<Jogador> getJogadores() {
        return mesa.getJogadoresRestantesJogando();
    }

    public List<Carta> getCartaFromCartaJogador(List<CartaJogador> cartaJogadorList) {
        return cartaJogadorList.stream().map(CartaJogador::getCarta).collect(Collectors.toList());
    }

    public Jogador getJogadorActual(){
        return this.mesa.getJogador();
    }

    public List<Jogador> getJogadoresProntos(){
        return this.getJogadores().stream().filter(Jogador::getPronto).collect(Collectors.toList());
    }

    public boolean isJogoEmAndamento(){
        return this.mesa.isJogoEmAndamento();
    }

    public boolean podeIniciaJogo() {
        List<Jogador> jogadorNaoProntos = this.getJogadores().stream().filter(jogador -> !jogador.getPronto()).collect(Collectors.toList());
       return jogadorNaoProntos.isEmpty();
    }

    public List<Carta> getCartasDisponiveis(){
        return this.mesa.getCartasDisponiveis();
    }

    public List<CartaJogador> getCartasJogadas(){
        return this.mesa.getCartasJogadas();
    }

    public Optional<Jogador> getJogadorRealInstance(Integer posicao){
        return this.mesa.getJogadoresRestantes().stream().filter(jogador -> jogador.getPosicao().equals(posicao)).findFirst();
    }
}
