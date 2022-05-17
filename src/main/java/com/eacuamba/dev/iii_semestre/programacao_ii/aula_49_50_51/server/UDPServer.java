package com.eacuamba.dev.iii_semestre.programacao_ii.aula_49_50_51.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UDPServer {
    public static void main(String[] args) {
        int port = 8089;
        byte[] dataBuffer = new byte[1024];
        DatagramSocket datagramSocket;

        try {
            datagramSocket = new DatagramSocket(port);
            DatagramPacket datagramPacket = new DatagramPacket(dataBuffer, dataBuffer.length);

            datagramSocket.receive(datagramPacket);

            InetAddress clientAddress = datagramPacket.getAddress();
            int clientPort = datagramPacket.getPort();

            byte[] clientMessageByte = datagramPacket.getData();

            System.out.printf("Client details:\nAddress: %s\nPort: %d%n", clientAddress.getHostAddress(), clientPort);

            System.out.print(new String(clientMessageByte, StandardCharsets.UTF_8));

        } catch (IOException e) {
            System.out.println("Occoreu um erro no servidor...\n" + e.getMessage());
        }
    }
}
