/*
 * Copyright (c) Edilson Alexandre Cuamba - 4 - 6 - 2022
 */

package com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.server;

import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.exceptions.ProximaCartaDeveSerNumeroDois;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.exceptions.ProximaCartaInvalida;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models.Carta;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models.CartaJogador;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models.Jogador;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.service.Croupier;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.server.data_transfer_object.EstadoMesaDTO;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.server.data_transfer_object.JogadorDTO;
import com.eacuamba.dev.utils.ObjectMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * 1 - se conecta com o jogador
 * 2 - o jogador se cadastra enviando o nome
 * 3 - o servidor envia uma mensagem de cadastro
 * 4 - o jogador envia mensagem dizendo que quer jogar
 * 5 - servidor notifica todas as pessoas sobre isso
 * 6 - quem quer aceita
 * 7 - o jogo inicia.
 */
@Getter
public class CroupierServer implements Runnable {
    private static List<CroupierServer> croupierServerList = new ArrayList<>();
    private static final Croupier croupier = Croupier.getInstance();
    private static List<Thread> threads = new ArrayList<>();
    private static ExecutorService executorService;
    private static int port = 8888;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private ObjectMapper objectMapper = ObjectMapperUtil.getObjectMapper();
    private Jogador jogador;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket accept = serverSocket.accept();
                Thread thread = new Thread(new CroupierServer(accept));
                threads.add(thread);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CroupierServer(Socket socket) {
        croupierServerList.add(this);
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String line;
            while ((line = this.bufferedReader.readLine()) != null) {
                EstadoMesaDTO estadoMesaDTO = ObjectMapperUtil.getObjectMapper().readValue(line, EstadoMesaDTO.class);

                if (estadoMesaDTO.isRegistarJogador() && !croupier.isJogoEmAndamento()) {
                    cadastrarUtilizador(estadoMesaDTO);
                }
                if (estadoMesaDTO.isPedidoInicioJogo()) {
                    this.iniciarJogo(estadoMesaDTO);
                }

                if (estadoMesaDTO.isJogoGoing()) {
                    if (estadoMesaDTO.isJogarCarta()) {
                        jogarCarta(estadoMesaDTO);
                    }
                    if(estadoMesaDTO.isPedidoCarta()){
                        pedirCarta(estadoMesaDTO);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void pedirCarta(EstadoMesaDTO estadoMesaDTO) throws JsonProcessingException {
        croupier.pedirCarta(estadoMesaDTO.getCartasAPedir(), estadoMesaDTO.getJogadorDTO().getJogador());
        EstadoMesaDTO estadoMesaDTO1 = EstadoMesaDTO.builder()
                .isJogoGoing(true)
                .todasCartas(this.getTodasCartas())
                .jogadorDTO(new JogadorDTO(croupier.getJogadorRealInstance(estadoMesaDTO.getJogadorDTO().getPosicao()).get()))
                .jogadorDTOList(croupier.getJogadores().stream().map(JogadorDTO::new).collect(Collectors.toList()))
                .cartasDisponiveis(croupier.getCartasDisponiveis())
                .cartasJogadas(croupier.getCartasJogadas())
                .jogadorDTOActual(new JogadorDTO(croupier.getJogadorActual()))
                .mensagem("Pedido Aceite! Cartas Adicionadas!")
                .build();

        sendMessageBack(estadoMesaDTO1.getJSON());
        Jogador jogadorActual = croupier.getJogadorActual();
        EstadoMesaDTO.EstadoMesaDTOBuilder dtoBuilder = estadoMesaDTO.toBuilder().pediuCarta(true).quemPediuCarta(estadoMesaDTO.getJogadorDTO()).jogadorDTO(new JogadorDTO(jogadorActual)).mensagem(String.format("Agora é a vez de %s", jogadorActual.getPessoa().getNome()));
        sendBroadCasting(objectMapper.writeValueAsString(dtoBuilder));

    }

    public void iniciarJogo(EstadoMesaDTO estadoMesaDTO) throws IOException {
        this.jogador.setPronto(true);
        if (croupier.podeIniciaJogo()) {
            croupier.iniciarJogo();

            estadoMesaDTO.reset().setJogadorDTOActual(new JogadorDTO(croupier.getJogadorActual()));
            estadoMesaDTO.setTodasCartas(Arrays.stream(Carta.values()).collect(Collectors.toList()));
            estadoMesaDTO.setCartasDisponiveis(croupier.getCartasDisponiveis());
            estadoMesaDTO.setCartasJogadas(croupier.getCartasJogadas());
            estadoMesaDTO.setJogadorDTOList(croupier.getJogadores().stream().map(JogadorDTO::new).collect(Collectors.toList()));
            estadoMesaDTO.setJogoGoing(true);
            estadoMesaDTO.setMensagem(String.format("O jogo iniciou, %s é o jogador actual", croupier.getJogadorActual().getPessoa().getNome()));
            sendBroadCasting(estadoMesaDTO.getJSON());

            estadoMesaDTO.setJogadorDTO(new JogadorDTO(jogador));
            this.bufferedWriter.write(this.objectMapper.writeValueAsString(estadoMesaDTO));
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
        } else {
            estadoMesaDTO.reset();
            sendBroadCasting(estadoMesaDTO.toBuilder().mensagem(String.format("%s quer iniciar o jogo, voce também?", jogador.getPessoa().getNome())).build().getJSON());

        }
    }

    public void cadastrarUtilizador(EstadoMesaDTO estadoMesaDTO) throws IOException {
        Jogador jogador = estadoMesaDTO.getJogadorDTO().getJogador();
        jogador = croupier.adicionarNovoJogador(jogador);
        if (jogador != null) {
            this.jogador = jogador;
            JogadorDTO jogadorDTO = new JogadorDTO(jogador);
            estadoMesaDTO = EstadoMesaDTO.builder().jogadorDTO(jogadorDTO).mensagem(String.format("Jogador (%s) registado com sucesso", jogadorDTO.getPessoaDTO().getNome())).build();
            estadoMesaDTO.setRegistarJogador(true);
            estadoMesaDTO.getJogadorDTOList().addAll(croupier.getJogadores().stream().map(JogadorDTO::new).collect(Collectors.toList()));
            this.bufferedWriter.write(this.objectMapper.writeValueAsString(estadoMesaDTO));
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();

            estadoMesaDTO.setRegistarJogador(false);
            estadoMesaDTO.setMensagem(String.format("O jogador %s acaba de se cadastrar.", jogador.getPessoa().getNome()));
            sendBroadCasting(this.objectMapper.writeValueAsString(estadoMesaDTO));
        } else {
            estadoMesaDTO = EstadoMesaDTO.builder().isRegistarJogador(false).mensagem(String.format("Limite de jogadores atingido")).build();
            estadoMesaDTO.getJogadorDTOList().addAll(croupier.getJogadores().stream().map(JogadorDTO::new).collect(Collectors.toList()));
            this.bufferedWriter.write(this.objectMapper.writeValueAsString(estadoMesaDTO));
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
        }
    }

    public void jogarCarta(EstadoMesaDTO estadoMesaDTO) throws JsonProcessingException {
        Carta cartaJogada = estadoMesaDTO.getCartaJogada();
        Jogador jogador = estadoMesaDTO.getJogadorDTO().getJogador();

        CartaJogador cartaJogador = CartaJogador.builder().carta(cartaJogada).jogador(jogador).localDateTime(LocalDateTime.now()).build();
        try {
            String jogarCarta = croupier.jogarCarta(cartaJogador);
            EstadoMesaDTO build = EstadoMesaDTO.builder().isJogoGoing(true).mensagem(jogarCarta).build();
            EstadoMesaDTO estadoMesaDTO1 = sendCurrentState(build);
            sendCurrentStateToAll(estadoMesaDTO1);
        } catch (ProximaCartaDeveSerNumeroDois e) {
            EstadoMesaDTO estadoMesaDTO1 = estadoMesaDTO
                    .toBuilder()
                    .jogadorDTO(new JogadorDTO(croupier.getJogadorRealInstance(estadoMesaDTO.getJogadorDTO().getPosicao()).get()))
                    .jogadorDTOList(croupier.getJogadores().stream().map(JogadorDTO::new).collect(Collectors.toList()))
                    .cartasDisponiveis(croupier.getCartasDisponiveis())
                    .cartasJogadas(croupier.getCartasJogadas())
                    .jogadorDTOActual(new JogadorDTO(croupier.getJogadorActual()))
                    .isJogoGoing(true)
                    .proximaCartaDeveSerNumeroDois(e)
                    .mensagem(e.getMessage()).build();
            sendMessageBack(estadoMesaDTO1.getJSON());
        }catch (ProximaCartaInvalida e) {
            EstadoMesaDTO estadoMesaDTO1 = estadoMesaDTO
                    .toBuilder()
                    .jogadorDTO(new JogadorDTO(croupier.getJogadorRealInstance(estadoMesaDTO.getJogadorDTO().getPosicao()).get()))
                    .jogadorDTOList(croupier.getJogadores().stream().map(JogadorDTO::new).collect(Collectors.toList()))
                    .cartasDisponiveis(croupier.getCartasDisponiveis())
                    .cartasJogadas(croupier.getCartasJogadas())
                    .jogadorDTOActual(new JogadorDTO(croupier.getJogadorActual()))
                    .isJogoGoing(true)
                    .mensagem(e.getMessage()).build();
            sendMessageBack(estadoMesaDTO1.getJSON());
        }
    }

    public EstadoMesaDTO sendCurrentState(EstadoMesaDTO estadoMesaDTO) throws JsonProcessingException {
        estadoMesaDTO = estadoMesaDTO.toBuilder().cartasJogadas(croupier.getCartasJogadas())
                .cartasDisponiveis(croupier.getCartasDisponiveis())
                .jogadorDTOActual(new JogadorDTO(croupier.getJogadorActual()))
                .todasCartas(this.getTodasCartas())
                .jogadorDTOList(croupier.getJogadores().stream().map(JogadorDTO::new).collect(Collectors.toList()))
                .build();
        sendMessageBack(estadoMesaDTO.getJSON());
        return estadoMesaDTO;
    }

    public EstadoMesaDTO sendCurrentStateToAll(EstadoMesaDTO estadoMesaDTO) throws JsonProcessingException {
        sendBroadCasting(estadoMesaDTO.getJSON());
        return estadoMesaDTO;
    }

    public List<Carta> getTodasCartas() {
        return Arrays.stream(Carta.values()).collect(Collectors.toList());
    }

    public void sendBroadCasting(String msg) {
        for (CroupierServer croupierServer : croupierServerList) {
            if (croupierServer == this) continue;
            try {
                croupierServer.bufferedWriter.write(msg);
                croupierServer.bufferedWriter.newLine();
                croupierServer.bufferedWriter.flush();
            } catch (IOException e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void sendMessageBack(String json) {
        try {
            this.bufferedWriter.write(json);
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
