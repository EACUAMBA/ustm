package contas;

public class ContaPrazo extends Conta{

    public ContaPrazo() {
    }

    public ContaPrazo(String numero, String titular, Double saldo) {
        super(numero, titular, saldo);
    }

    @Override
    public double saldoReal(double valor) {
        return this.saldoReal = this.saldo + valor - (valor * ((double) 3 / 100));
    }

    @Override
    public void depositar(double valor) {
        this.saldo = this.saldo  + valor + (0.03 * valor);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
