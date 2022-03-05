package com.eacuamba.dev.personal_study.async_programming.server_mock;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class TrataRequisicao implements Runnable{
    private final Socket socket;
    public TrataRequisicao(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            System.out.println("Connectado a: " + this.socket.getRemoteSocketAddress());
            DataInputStream dataInputStream = new DataInputStream(this.socket.getInputStream());
            System.out.println(dataInputStream.readUTF());
            DataOutputStream dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
            Thread.sleep(TimeUnit.SECONDS.toMillis(5));
            dataOutputStream.writeUTF("A sua conexão terminou! Tchau!");
            this.socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
