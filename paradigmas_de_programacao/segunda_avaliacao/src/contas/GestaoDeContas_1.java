package contas;

import java.util.UUID;

public class GestaoDeContas_1 {
    public static void main(String[] args) {
        ContaOrdem contaOrdem = new ContaOrdem(UUID.randomUUID().toString(), "Wilson Cossa", 0.0, RegimeTitularidade.INDIVIDUAL);
        ContaPrazo contaPrazo = new ContaPrazo(UUID.randomUUID().toString(), "Igor Tossico", 0.0);

        System.out.print(contaOrdem);
        System.out.printf("Saldo Real: %.2f%n", contaOrdem.saldoReal(0));
        System.out.print(contaPrazo);
        System.out.printf("Saldo Real: %.2f%n", contaPrazo.saldoReal(0));

        System.out.println(contaOrdem);
        System.out.println(contaPrazo);

        //a
        contaOrdem.depositar(10_000);
        contaPrazo.depositar(10_000);

        //b
        System.out.println(contaOrdem);
        System.out.println(contaPrazo);

        //c
        Conta contaContaOrdem = contaOrdem;
        Conta contaContaPrazo = contaPrazo;

        contaContaOrdem.depositar(1_000);
        contaContaPrazo.depositar(1_000);

        System.out.println(contaOrdem);
        System.out.println(contaPrazo);

        //e
        SaldoReal srOrdem = contaOrdem;
        SaldoReal srPrazo = contaPrazo;

        System.out.print(contaOrdem);
        System.out.printf("SaldoReal: %.2f%n", contaOrdem.saldoReal(1_000));
        System.out.print(contaPrazo);
        System.out.printf("SaldoReal: %.2f%n", contaPrazo.saldoReal(1_000));

    }
}
