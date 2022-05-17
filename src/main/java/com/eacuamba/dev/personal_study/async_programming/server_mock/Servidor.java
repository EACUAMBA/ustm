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

    // Usamos o executorService como um auxiliar para termos uma instancia de Future e assim fazermos o track de uma thread.
    private final ExecutorService executorService;
    protected List<Future<?>> requisicoes = new ArrayList<>();

    public static  void main (String[] args) throws IOException {
        System.out.println("Servidor no ar.");
        //Cria uma thread nova executando o servidor, dentro dessa thread temos uma classe que implementa a Interface Runnable, no seu contrutor especificamos a porta que o nosso servidor vai escutar, sendo que o IP é o IP da máquina, e o segundo argumento é o número de threads que a nossa pool vai ter.
        new Thread(
                new Servidor(8000, 3)
        ).start();
    }
    public Servidor(int port, int poolSize) throws IOException {
        // Cria um novo socket de servidor que escuta na porta que especificamos no construtor
        serverSocket = new ServerSocket(port);

        //Cria a nossa pool de connections com o tamanho que especificamos no construtor.
        executorService = Executors.newFixedThreadPool(poolSize);
    }

    @Override
    public void run() {
        try{
            // Adiciona no nosso pool de threads uma nova thread que vai estar a verificar as requisições feitas se já foram terminas, canceladas, ou em andamento. Recebe como argumento o array com as Futures.
            this.executorService.execute(new VerificaRequisicao(this.requisicoes));

            // Criamos um loop infinito para ouvir eternamente, os pedidos de requisição.
            while (true){
                System.out.println("Nova requisição!");

                // Submetemos ou adicionamos uma nova thread na nossa pool, este é um objecto que terá a responsabilidade de tratar as requisições, como parametro passamos para ele uma instancia de ServerSocket aceite, neste caso estamos a dizer qeu aceitamos o pedido de requisição  e assim vamos tratar esta requisição na nossa thread.
                Future<?> requisicao = executorService.submit(
                        //Threas que trata as requisições
                        new TrataRequisicao(
                                // Uma requisição na porta aceita.
                                serverSocket.accept()
                        )
                );
                // Adicionamos no array a requisição.
                this.requisicoes.add(requisicao);

                //OBS: note que a classe que trata a requisição é uma classe que implementa runnable, com isso podemos tratala com a especificação de Future para sabermos se as instruções lá já terminaram ou estão em andamdento.
            }
        } catch (IOException e) {
            e.printStackTrace();
            executorService.shutdown();
        }
    }
}
