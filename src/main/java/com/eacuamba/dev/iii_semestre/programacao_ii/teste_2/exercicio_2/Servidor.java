package com.eacuamba.dev.iii_semestre.programacao_ii.teste_2.exercicio_2;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Servidor {
    private static final int serverPort = 8080;

    public static void main(String[] args) {
        try {
            byte[] messageByte = new byte[60000];
            DatagramPacket datagramPacket = new DatagramPacket(messageByte, messageByte.length);
            DatagramSocket datagramSocket = new DatagramSocket(serverPort);
            datagramSocket.receive(datagramPacket);

            String s = new String(messageByte);

            StringBuilder stringBuilder = new StringBuilder();
            for(char c : s.toCharArray()){
                if(Character.isUpperCase(c)){
                    stringBuilder.append(Character.toLowerCase(c));
                }else
                    stringBuilder.append(Character.toUpperCase(c));
            }
            datagramPacket.setData(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
            datagramSocket.send(datagramPacket);

        } catch (UnknownHostException e) {
            System.out.println("Ocorreu um erro ao criar o endereço do servidor.");
        } catch (SocketException e) {
            System.out.println("Ocorreu um erro ao criar o socket do cliente.");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao enviar o pacote.");
        }
    }
}
