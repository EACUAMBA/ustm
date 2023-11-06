package contas;

import java.util.UUID;

public class GestaoDeContas_2 {
    public static void main(String[] args) {
        ContaOrdem contaOrdem = new ContaOrdem(UUID.randomUUID().toString(), "Wilson Cossa", 0.0, RegimeTitularidade.INDIVIDUAL);

        imprimirContas(contaOrdem, 1000, contaOrdem);

    }

    public static void imprimirContas(Conta conta, double valor, SaldoReal saldoReal){
        conta.depositar(valor);
        System.out.print(conta);
        System.out.printf("SaldoReal: %.2f%n", saldoReal.saldoReal(valor));
    }
}
