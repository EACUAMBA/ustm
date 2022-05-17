package com.eacuamba.dev.iii_semestre.aula_49_50_51.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UDPClient {
    public static void main(String[] args) {
        String message = "Olá server 127.0.0.1";
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

        int port = 8089;
        DatagramSocket datagramSocket;

        try{
            InetAddress localHost = InetAddress.getLocalHost();

            DatagramPacket datagramPacket = new DatagramPacket(messageBytes, messageBytes.length, localHost, port);

            datagramSocket = new DatagramSocket();
            datagramSocket.send(datagramPacket);

            InetAddress serverAddress = datagramPacket.getAddress();
            int serverPort = datagramPacket.getPort();

            System.out.printf("Server details:\nAddress: %s\nPort: %d%n", serverAddress.getHostAddress(), serverPort);

        }catch (IOException e) {
            System.out.println("Ocoreu um erro no client...\n" + e.getMessage());
        }
    }
}
