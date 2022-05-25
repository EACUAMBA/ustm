package com.eacuamba.dev.iii_semestre.programacao_ii.teste_2.exercicio_2;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Cliente {
    private static final int serverPort = 8080;
    private static InetAddress serverAddress;

    public static void main(String[] args) {
        try {
            serverAddress = InetAddress.getByName("localhost");

            String message = "Universidade São Tomás de Moçambique";
            //Convertendo os caracteres em bytes usando a tabela UTF8 de caracteres, isso quer dizer que cada byte no array vai ter o digito correspondente na tabela UTF8
            byte[] messageByte = message.getBytes(StandardCharsets.UTF_8);

            DatagramPacket datagramPacket = new DatagramPacket(messageByte, messageByte.length, serverAddress, serverPort);
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.send(datagramPacket);
            datagramSocket.receive(datagramPacket);

            System.out.println(new String(datagramPacket.getData()));
        } catch (UnknownHostException e) {
            System.out.println("Ocorreu um erro ao criar o endereço do servidor.");
        } catch (SocketException e) {
            System.out.println("Ocorreu um erro ao criar o socket do cliente.");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao enviar o pacote.");
        }
    }
}
