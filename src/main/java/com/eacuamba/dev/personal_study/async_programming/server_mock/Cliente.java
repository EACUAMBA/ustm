package com.eacuamba.dev.personal_study.async_programming.server_mock;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    public static void main(String[] args) {

        //Especificando o IP address.
        String serverName = "127.0.0.1";

        //Especificando a porta.
        int port = 8000;

        try{
            System.out.println("Iniciando a conexão!");
            //Criando o socket de cliente para conectarmos ao servidor, argumentamos isso com o IP e a porta.
            Socket socket = new Socket(serverName, port);

            // Pegando o address do servidor.
            System.out.println("Conectado ao: " + socket.getRemoteSocketAddress());

            //Pegando a stream de dados que vai ao servidor, por isso é out sair.
            OutputStream outputStream = socket.getOutputStream();

            //Tornando ela um Data output para facilidar no tratamento
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            //Escrevendo em UTF8, logo que escrevemo a informacao vai para o servidor, não é possivel adicionar mais texo
            dataOutputStream.writeUTF("Ola servidor! "+socket.getLocalSocketAddress());

            //recebe a resposta do servidor.
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            //imprime a resposta do servidor.
            System.out.println("Resposta: " + dataInputStream.readUTF());
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
