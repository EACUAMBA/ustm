package contas;

public abstract class Conta implements SaldoReal {
    protected String numero;
    protected String titular;
    protected Double saldo = 0D;
    protected Double saldoReal = 0D;

    public Conta() {
    }

    public Conta(String numero, String titular, Double saldo) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Double getSaldo() {
        return saldo;
    }

    protected void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public abstract void depositar(double valor);

    public String toString() {
        return String.format("Número: %s%nTitular: %s%nSaldo: %.2f%n", numero, titular, saldo);
    }


}
