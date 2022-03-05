package com.eacuamba.dev.personal_study.async_programming.server_mock;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Servidor implements Runnable{
    private final ServerSocket serverSocket;
    private final ExecutorService executorService;
    protected List<Future> requisicoes = new ArrayList<Future>();

    public static  void main (String[] args) throws IOException {
        //Cria uma thread nova executando o servidor
        System.out.println("Servidor no ar.");
        new Thread(new Servidor(8000, 3)).run();
    }
    public Servidor(int port, int poolSize) throws IOException {
        serverSocket = new ServerSocket(port);
        executorService = Executors.newFixedThreadPool(poolSize);
    }

    @Override
    public void run() {
        try{
            //Cria uma nova tarefa para a verificação das requisições
            this.executorService.execute(new VerificaRequisicao(this.requisicoes));
            while (true){
                System.out.println("Nova requisição!");
                Future requisicao = executorService.submit(new TrataRequisicao(serverSocket.accept()));
                //Armazena todas as requisições.
                this.requisicoes.add(requisicao);
            }
        } catch (IOException e) {
            e.printStackTrace();
            executorService.shutdown();
        }
    }
}
