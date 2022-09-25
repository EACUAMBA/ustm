package com.eacuamba.dev.personal_study.async_programming.server_mock;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class VerificaRequisicao implements Runnable{
    private final List<Future<?>> futureList;

    public VerificaRequisicao(List<Future<?>> futureList){
        this.futureList = futureList;
    }

    @Override
    public void run() {
        while(true){
            int somaTerminadas = 0;
            int somaCanceladas = 0;
            int somaEmExecucao = 0;
            try{
                Thread.sleep(TimeUnit.SECONDS.toMillis(10));
                //Pegando a lista de Futures e verificando o estado delas.
                for(Future<?> future : this.futureList){
                    if(future.isDone()){
                        somaTerminadas++;
                    }else if(future.isCancelled()){
                        somaCanceladas++;
                    }else if (!future.isDone()){
                        somaEmExecucao++;
                    }
                }
                System.out.println("Terminadas: " + somaTerminadas);
                System.out.println("Canceladas: " + somaCanceladas);
                System.out.println("Em execução: " + somaEmExecucao);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
