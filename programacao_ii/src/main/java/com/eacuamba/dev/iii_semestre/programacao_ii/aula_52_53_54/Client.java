package com.eacuamba.dev.iii_semestre.programacao_ii.aula_52_53_54;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class Client {
    private static final int clientPort = 8080;
    private static  final String clientIp = "169.254.113.162";

    public static void main(String... args) throws IOException {
        sendMessage();
        receiveMessage();
        main("");
    }

    public static void sendMessage(){
        Socket socket = null;
        try {
            socket = new Socket(clientIp, clientPort);

            OutputStream socketOutputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(socketOutputStream);
            JFileChooser fileChooser = new JFileChooser();
//            fileChooser.showDialog(null, "Selecionar");
//            File selectedFile = fileChooser.getSelectedFile();
            String message = JOptionPane.showInputDialog(null, "Mensagem para " + clientIp + "\n");
            dataOutputStream.writeUTF(message);

            InputStream socketInputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(socketInputStream);
            JOptionPane.showMessageDialog(null, "Mensagem de " + clientIp + "\n" +  dataInputStream.readUTF());

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void receiveMessage() throws IOException {
        Socket s;
        ServerSocket socket;
        socket = new ServerSocket(clientPort);

        s = socket.accept();
        InputStreamReader a = new InputStreamReader(s.getInputStream());
        DataInputStream dis = new DataInputStream(s.getInputStream());
        String msg; 		msg = dis.readUTF();
        JOptionPane.showMessageDialog(null, msg);

s.close();
socket.close();

//        OutputStream sos = s.getOutputStream();
//        DataOutputStream dos = new DataOutputStream(sos);
//        dos.writeUTF(JOptionPane.showInputDialog(null, "Nome"));
//        s.close();

    }
}
