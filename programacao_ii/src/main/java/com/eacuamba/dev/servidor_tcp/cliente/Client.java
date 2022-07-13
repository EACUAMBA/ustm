package com.eacuamba.dev.servidor_tcp.cliente;

import java.io.*;
import java.net.Socket;

public class Client {
    private static Socket socket;

    public static void main(String[] args) {
        //Tentando me conectar com o servidor
        try {

            //Estabelecendo a conexão com o servidor, passado com argumento o IP e a porta no anfitrião (host) que o servidor utiliza.
            //127.0.0.1 equivalente a 'localhost' um IP de loopback, aponta para a minha propria máquina, ou para a máquina que a aplicação está á rodar no momento de execução.
            socket = new Socket("127.0.0.1", 9090);

            //Informando ao utilizador (cliente) que a conexão foi estabelecida com sucesso.
            System.out.println("Conexão estabelecida com o servidor!");

            //Chamando a função que lé as mensagens vindas do servidor.
            readMessagesFromServer();

            //Se houver algum erro, estou á capturar e a imprimir uma mensagem no terminal ao utilizador.
        } catch (IOException e) {
            System.out.println("Incidente ao estabelecer conexão com o servidor.");
            e.printStackTrace();
        }
    }

    public static void readMessagesFromServer() {
        InputStream inputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        try {
            //Obtendo fluxo de entrada de dados.
            inputStream = socket.getInputStream();

            //Encapsulando o fluxo num fluxo de entrada de dados para leitura.
            inputStreamReader = new InputStreamReader(inputStream);

            //Encapsulando o fluxo de entrada de dados para leitura numa memória temporária (buffer) de leitura.
            bufferedReader = new BufferedReader(inputStreamReader);

            //Enquanto houver linha para ler no fluxo de entrada de dados, usando buffer, leia;
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                //Imprima isso no terminal.
                System.out.println(line);
            }

            //Se houver algum erro, estou á capturar e a imprimir uma mensagem no terminal ao utilizador.
        } catch (IOException e) {
            System.out.println("Incidente ao ler mensagens vindas do servidor.");
            e.printStackTrace();
        }
    }
}
