package com.eacuamba.dev.servidor_tcp.servidor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final int port = 9090;
    private static final List<Socket> socketkList = new ArrayList<>();

    public static void main(String[] args) {
        //Tentando criar um servidor
        try {
            //Criando o objecto que representa o servidor, passando a porta que o servidor vai utilizar.
            ServerSocket serverSocket = new ServerSocket(port);

            //Criando o while (enquanto) para o servidor sempre esperar por uma conexão nova.
            //Enquanto houver conexão (só há conexão quando o socket é deferente de null) execute o código a seguir.
            Socket socket;
            while ((socket = serverSocket.accept()) != null) {

                //Adicione o socket no array de sockets.
                socketkList.add(socket);

                //Informando que o estado do servidor mudou, temos uma nova conexão.
                System.out.println("Conexão estabelecida com o cliente!\nTemos " + socketkList.size() + " clientes conectados.");

                //Chamando o método que envia a mensagem broadcast.
                sendBroadcastMessage("Temos mais um cliente, total de clientes: " + socketkList.size());
            }

            //Se houver algum erro, estou á capturar e a imprimir uma mensagem no terminal ao utilizador.
        } catch (IOException e) {
            System.out.println("Incidente desconhecido no servidor.");
            e.printStackTrace();
        }
    }

    public static void sendBroadcastMessage(String message) {
        //Iterando sobre a lista/coleção de sockets (conexões com o servidor, podemos chamar de sessões abertas).
        for (Socket socket : socketkList) {
            OutputStream outputStream = null;
            BufferedWriter bufferedWriter = null;
            OutputStreamWriter outputStreamWriter = null;

            //Tentando ler o fluxo de dados (OutputStream) da conexão com o cliente.
            try {
                //Obtendo o fluxo de saída de dados.
                outputStream = socket.getOutputStream();

                //Encapsulando o fluxo de saída de dados num fluxo de saída de dados escritor.
                outputStreamWriter = new OutputStreamWriter(outputStream);

                //Encapsulando o fluxo de saída de dados escritor em um memória temporária (buffer) de escrita.
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                //Escrevendo a mensagem.
                bufferedWriter.write(message);

                //Saltando a linha
                bufferedWriter.newLine();

                //Forçando o envio de tudo que foi escrito, limpando a memória transitória (cache)!?.
                bufferedWriter.flush();

                //Se houver algum erro, estou á capturar e a imprimir uma mensagem no terminal ao utilizador.
            } catch (IOException e) {
                System.out.println("Incidente ao enviar broadcast.");
                e.printStackTrace();
            }
        }
    }
}
