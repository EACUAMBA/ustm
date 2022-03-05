package com.eacuamba.dev.personal_study.async_programming.server_mock;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    public static void main(String[] args) {
        String serverName = "127.0.0.1";
        int port = 8000;

        try{
            System.out.println("Iniciando a conexão!");
            //faz a conexão.
            Socket socket = new Socket(serverName, port);
            System.out.println("Conectado ao: " + socket.getRemoteSocketAddress());
            OutputStream outputStream = socket.getOutputStream();

            //envia mensagem.
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF("Ola servidor! "+socket.getLocalSocketAddress());

            //recebe a resposta do servidor.
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            //imprime a resposta.
            System.out.println("Resposta: " + dataInputStream.readUTF());
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
