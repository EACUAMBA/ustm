/*
 * Copyright (c) Edilson Alexandre Cuamba - 4 - 6 - 2022
 */

package com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.client;

import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.exceptions.ProximaCartaDeveSerNumeroDois;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models.Carta;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.server.data_transfer_object.EstadoMesaDTO;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.server.data_transfer_object.JogadorDTO;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.server.data_transfer_object.PessoaDTO;
import com.eacuamba.dev.utils.ObjectMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import java.util.List;

public class CroupierCliente extends JFrame {
    private static int port = 8888;
    private static InetAddress address;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private ObjectMapper objectMapper = ObjectMapperUtil.getObjectMapper();
    private JogadorDTO jogadorDTO;
    private Integer cartasAPedir = 1;
    private JTextArea mensagem;
    private JPanel mainJPanel;
    private JPanel centerMainJPanel;
    private JPanel dividedCenterJPanel;
    private JPanel leftDividedCenterJPanel;
    private JPanel rightDividedCenterJPanel;
    private JPanel jogadoresJPanel;
    private JPanel cartaDisponivelParaEuJogarJPanel;
    private JPanel cartaDisponivelParaEuJogarWraperJPanel;
    private JPanel cartasJogadasJPanel;
    private JScrollPane cartasJogadasJScrollPane;
    private JPanel cartaDisponivelJPanel;
    private JScrollPane cartaDisponivelJScrollPane;
    private JPanel cartaIndisponivelJPanel;
    private JScrollPane cartaIndisponivelJScrollPane;
    private JList<JogadorDTO> jogadorDTOJogandoJList;
    private JList<JogadorDTO> jogadorDTOVencedorJList;
    private JList<JogadorDTO> jogadorDTOPerdedorJList;
    private List<JButton> cartasDisponivelList = new ArrayList<>();
    private List<JButton> cartasJogadasList = new ArrayList<>();
    private List<JButton> cartaDisponivelParaEuJogarList = new ArrayList<>();
    private JMenu nomeJogadorJMenu;
    private JMenuItem pedirCartasJMenuItem;
    private JMenuItem cadastrarUtilizadorJMenuItem;
    private JMenuItem iniciarJogoJMenuItem;

    public static void main(String[] args) throws UnknownHostException {
        CroupierCliente croupierCliente = new CroupierCliente();
        croupierCliente.build();
    }

    public void criacaoDeInstancias() {
        //Instanciação
        //  JPANEL
        this.mainJPanel = new JPanel();
        this.jogadoresJPanel = new JPanel();
        this.cartaDisponivelParaEuJogarJPanel = new JPanel();
        this.cartaDisponivelParaEuJogarWraperJPanel = new JPanel();
        this.centerMainJPanel = new JPanel();
        this.dividedCenterJPanel = new JPanel();
        this.leftDividedCenterJPanel = new JPanel();
        this.rightDividedCenterJPanel = new JPanel();
        this.cartasJogadasJPanel = new JPanel();
        this.cartaDisponivelJPanel = new JPanel();
        this.cartaIndisponivelJPanel = new JPanel();
        //  JScrollPane
        this.cartasJogadasJScrollPane = new JScrollPane();
        this.cartaDisponivelJScrollPane = new JScrollPane();
        this.cartaIndisponivelJScrollPane = new JScrollPane();
        //  TextArea
        this.mensagem = new JTextArea();
        //  JList
        this.jogadorDTOJogandoJList = new JList<>();
        this.jogadorDTOVencedorJList = new JList<>();
        this.jogadorDTOPerdedorJList = new JList<>();

        this.nomeJogadorJMenu = new JMenu();
        this.pedirCartasJMenuItem = new JMenuItem();
        this.cadastrarUtilizadorJMenuItem = new JMenuItem();
        this.iniciarJogoJMenuItem = new JMenuItem();
    }

    public void buildInterface() {

        Rectangle maximumWindowBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        this.setSize(maximumWindowBounds.width, maximumWindowBounds.height);
        this.setTitle("Cliente Croupier");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        setVisible(true);

        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("Ações");
        this.cadastrarUtilizadorJMenuItem.setText("Cadastrar-me");
        iniciarJogoJMenuItem.addActionListener(new IniciarJogoEventListener());
        iniciarJogoJMenuItem.setText("Iniciar o jogo!");
        pedirCartasJMenuItem.addActionListener(new PedirCartasEventListener());
        cadastrarUtilizadorJMenuItem.addActionListener(new CadastrarEventListener());
        pedirCartasJMenuItem.setText("Pedir Cartas");
        jMenu.add(pedirCartasJMenuItem);
        jMenu.add(iniciarJogoJMenuItem);
        jMenu.add(cadastrarUtilizadorJMenuItem);
        jMenuBar.add(jMenu);
        jMenuBar.add(nomeJogadorJMenu);
        this.setJMenuBar(jMenuBar);

        mainJPanel.setLayout(new BorderLayout());
        this.add(mainJPanel, BorderLayout.CENTER);

        this.cartaDisponivelParaEuJogarWraperJPanel.setLayout(new BorderLayout());
        this.cartaDisponivelParaEuJogarWraperJPanel.add(new JLabel("Minhas Cartas"), BorderLayout.NORTH);
        cartaDisponivelParaEuJogarJPanel.setLayout(new GridLayout(0, 6, 8, 8));
        Dimension dimension = new Dimension();
        dimension.height = 256;
        cartaDisponivelParaEuJogarJPanel.setSize(dimension);
        JScrollPane jScrollPaneMinhasCartas = new JScrollPane(cartaDisponivelParaEuJogarJPanel);
        this.cartaDisponivelParaEuJogarWraperJPanel.add(jScrollPaneMinhasCartas, BorderLayout.CENTER);
        mainJPanel.add(this.cartaDisponivelParaEuJogarWraperJPanel, BorderLayout.SOUTH);

        centerMainJPanel.setLayout(new BorderLayout());
        dividedCenterJPanel.setLayout(new GridLayout(1, 2));
        centerMainJPanel.add(dividedCenterJPanel, BorderLayout.CENTER);
        mainJPanel.add(centerMainJPanel, BorderLayout.CENTER);

        this.leftDividedCenterJPanel.setLayout(new BorderLayout());
        this.leftDividedCenterJPanel.add(new Label("Cartas Que podem ser distribuidas!"), BorderLayout.NORTH);
        this.cartaDisponivelJPanel.setLayout(new GridLayout(0, 4, 8, 8));
        this.cartaDisponivelJScrollPane.setViewportView(this.cartaDisponivelJPanel);
        this.leftDividedCenterJPanel.add(this.cartaDisponivelJPanel, BorderLayout.CENTER);
        dividedCenterJPanel.add(this.leftDividedCenterJPanel);

        this.rightDividedCenterJPanel.setLayout(new BorderLayout());
        this.rightDividedCenterJPanel.add(new Label("Cartas que foram jogadas!"), BorderLayout.NORTH);
        this.cartasJogadasJPanel.setLayout(new GridLayout(0, 4, 8, 8));
        this.cartasJogadasJScrollPane.setViewportView(this.cartasJogadasJPanel);
        this.rightDividedCenterJPanel.add(this.cartasJogadasJScrollPane, BorderLayout.CENTER);
        dividedCenterJPanel.add(this.rightDividedCenterJPanel);

        this.jogadoresJPanel.setLayout(new BorderLayout());
        this.jogadoresJPanel.add(new JLabel("Jogadores"), BorderLayout.NORTH);
        this.mainJPanel.add(this.jogadoresJPanel, BorderLayout.EAST);

        this.jogadorDTOJogandoJList.setFixedCellWidth(200);
        this.jogadoresJPanel.add(jogadorDTOJogandoJList);

        mensagem.setEditable(false);
        mensagem.setRows(5);
        mensagem.setBorder(new BevelBorder(BevelBorder.LOWERED));
        mensagem.setFont(new Font("Arial", Font.BOLD, 14));
        mainJPanel.add(mensagem, BorderLayout.NORTH);

        this.revalidate();
        this.repaint();
    }

    public void build() {
        buildInterface();

        this.protocolo();
    }

    public void dizerAoCroupierParaInicarJogo() {
        try {
            EstadoMesaDTO estadoMesaDTO = EstadoMesaDTO.builder()
                    .isRegistarJogador(false)
                    .jogadorDTO(this.jogadorDTO)
                    .isPedidoInicioJogo(true)
                    .build();
            String estadoMesaDTOJSON = this.objectMapper.writeValueAsString(estadoMesaDTO);
            this.bufferedWriter.write(estadoMesaDTOJSON);
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }

    public CroupierCliente() {
        super();
        this.criacaoDeInstancias();
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), port);
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            this.mensagem.setText("Não foi possivel criar a conexão com o servidor.");
        }
    }

    public void protocolo() {
        try {
            String line;
            while ((line = this.bufferedReader.readLine()) != null) {
                EstadoMesaDTO estadoMesaDTO = this.objectMapper.readValue(line, EstadoMesaDTO.class);
                this.mensagem.setText(estadoMesaDTO.getMensagem());
                renderizarEstado(estadoMesaDTO);

            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }

    public void renderizarEstado(EstadoMesaDTO estadoMesaDTO) {
        if (estadoMesaDTO.isRegistarJogador()) {
            this.jogadorDTO = estadoMesaDTO.getJogadorDTO();
            this.nomeJogadorJMenu.setText("Tela do Jogador: " + this.jogadorDTO.toString());
        } else {
            //Actualiza dados
            if (Objects.nonNull(jogadorDTO))
                this.jogadorDTO = estadoMesaDTO.getJogadorDTOList().stream()
                        .filter(jogadorDTO1 -> jogadorDTO1.getPosicao().equals(this.jogadorDTO.getPosicao())).findFirst().orElseGet(() -> this.jogadorDTO);
        }


        this.jogadorDTOJogandoJList.setListData(estadoMesaDTO
                .getJogadorDTOList()
                .toArray(new JogadorDTO[0]));
        this.jogadorDTOJogandoJList.revalidate();
        this.jogadorDTOJogandoJList.repaint();

        //this.jogadoresJPanel.add(this.jogadoresJList, BorderLayout.CENTER);
        this.jogadoresJPanel.revalidate();
        this.jogadoresJPanel.repaint();

        this.cartasJogadasList.clear();
        if (estadoMesaDTO.isJogoGoing()) {
            estadoMesaDTO.getCartasJogadas().forEach(cartaJogador -> {
                Carta carta = cartaJogador.getCarta();
                JButton jButton = this.createCard(cartaJogador.getCarta());
                this.cartasJogadasList.add(jButton);
            });
            this.cartasJogadasJPanel.removeAll();
            this.cartasJogadasList.forEach(jButton -> {
                this.cartasJogadasJPanel.add(jButton);
            });
            this.cartasJogadasJPanel.revalidate();
            this.cartasJogadasJPanel.repaint();


            //Cartas pessoais do jogador
            Optional<JogadorDTO> optionalJogadorDTO = estadoMesaDTO.getJogadorDTOList().stream().filter(jogadorDTO1 -> jogadorDTO1.getPosicao().equals(this.jogadorDTO.getPosicao())).findFirst();
            optionalJogadorDTO.ifPresent(jogadorDTO1 -> {
                cartaDisponivelParaEuJogarList.clear();
                jogadorDTO1.getCartas().forEach(carta -> {
                    JButton jButton = this.createCard(carta);
                    this.cartaDisponivelParaEuJogarList.add(jButton);

                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JButton source = (JButton) e.getSource();
                            Optional<Carta> cartaOptional = estadoMesaDTO.getTodasCartas().stream().filter(c -> carta.name().equalsIgnoreCase(source.getName())).findFirst();
                            cartaOptional.ifPresent(carta1 -> jogarCarta(carta));
                        }
                    });
                });

            });
            this.cartaDisponivelParaEuJogarJPanel.removeAll();
            this.cartaDisponivelParaEuJogarList.forEach(jButton -> {
                this.cartaDisponivelParaEuJogarJPanel.add(jButton);
            });
            cartaDisponivelParaEuJogarJPanel.revalidate();
            cartaDisponivelParaEuJogarJPanel.repaint();

            ProximaCartaDeveSerNumeroDois proximaCartaDeveSerNumeroDois = estadoMesaDTO.getProximaCartaDeveSerNumeroDois();
            if(Objects.nonNull(proximaCartaDeveSerNumeroDois)){
                if(proximaCartaDeveSerNumeroDois.getSomatorioDasCartasDeValorDoisJogadasAteAgora() != 0){
                    this.cartasAPedir = proximaCartaDeveSerNumeroDois.getSomatorioDasCartasDeValorDoisJogadasAteAgora();
                }
            }
        }

        mainJPanel.revalidate();
        mainJPanel.repaint();
    }

    public void jogarCarta(Carta carta) {
        if (this.jogadorDTO.getActual()) {
            EstadoMesaDTO estadoMesaDTO = EstadoMesaDTO.builder().isJogoGoing(true).isJogarCarta(true).cartaJogada(carta).jogadorDTO(jogadorDTO).build();
            try {
                sendMessageBack(estadoMesaDTO.getJSON());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(CroupierCliente.this, "Não é a tua vez de jogar, para com isso!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cadastro() {
        try {
            String nome = JOptionPane.showInputDialog(this, "Nome");
            EstadoMesaDTO estadoMesaDTO = EstadoMesaDTO.builder()
                    .isRegistarJogador(true)
                    .jogadorDTO(JogadorDTO
                            .builder()
                            .pessoaDTO(PessoaDTO
                                    .builder()
                                    .nome(nome)
                                    .build())
                            .build())
                    .build();
            String estadoMesaDTOJSON = this.objectMapper.writeValueAsString(estadoMesaDTO);
            this.bufferedWriter.write(estadoMesaDTOJSON);
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }

    private JButton createCard(Carta carta) {
        JLabel cartaJLabel = new JLabel("Carta: " + carta.getDesignacao());
        JLabel naipeJLabel = new JLabel("Naipe: " + carta.getNaipe().name());
        JLabel valorJLabel = new JLabel("Valor: " + carta.getValor());
        JButton jButton = new JButton();
        jButton.setLayout(new GridLayout(3, 1));
        jButton.setName(carta.name());
        jButton.add(cartaJLabel);
        jButton.add(naipeJLabel);
        jButton.add(valorJLabel);
        return jButton;
    }

    private void pedirCartas(Integer cartas) {
        EstadoMesaDTO build = EstadoMesaDTO.builder()
                .isJogoGoing(true)
                .isPedidoCarta(true)
                .cartasAPedir(cartas)
                .jogadorDTO(this.jogadorDTO)
                .build();
        try {
            sendMessageBack(build.getJSON());
        } catch (JsonProcessingException e) {
            this.mensagem.setText(e.getOriginalMessage());
        }
    }

    public class CadastrarEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (jogadorDTO == null) {
                cadastro();
            } else
                JOptionPane.showMessageDialog(CroupierCliente.this, "Não pode se registar novamente!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public class IniciarJogoEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dizerAoCroupierParaInicarJogo();
        }

    }

    public class PedirCartasEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(jogadorDTO.getActual()){
                int i = JOptionPane.showConfirmDialog(CroupierCliente.this, "Deseja pedir " + cartasAPedir + " ao croupier?");
                if (Objects.nonNull(cartasAPedir) && i == JOptionPane.YES_OPTION) {
                    pedirCartas(cartasAPedir);
                }
            }else {
                JOptionPane.showMessageDialog(CroupierCliente.this, "Não é a tua vez de jogar, para com isso!", "Error", JOptionPane.ERROR_MESSAGE);

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
