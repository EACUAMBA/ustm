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

            // Pegamos o endereço do cliente, o IP.
            System.out.println("Connectado a: " + this.socket.getRemoteSocketAddress());

            //Pegando o InputStream e encapsulando ele em um DataInputStream.
            DataInputStream dataInputStream = new DataInputStream(this.socket.getInputStream());

            //Lendo os dados usando o encoding UTF-8, quero dizer convertendo eles, os bytes em caracteres UTF-8, lembre que UTF-8 é uma tabela de caracteres e valores das suas posições, estamos a pegar esses valores em um array de bytes e mapeando para o seu corresponedente na posicao correcta.
            System.out.println(dataInputStream.readUTF());
            // Obtendo a stream, o fluxo de dados de saído, a que sera enviada ao clieente. Acho que é uma instancia que será commitada.
            DataOutputStream dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
            //Escrevendo no fluxo de saída.
            dataOutputStream.writeUTF("A sua conexão terminou! Tchau!");

            //Fazer a threas actual dormir por 5 segundos.
            Thread.sleep(TimeUnit.SECONDS.toMillis(5));

            // Fechando a requisição com o cliente e enviando a resposta.
            this.socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
