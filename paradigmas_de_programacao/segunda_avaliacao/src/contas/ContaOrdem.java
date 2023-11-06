package contas;

public class ContaOrdem extends Conta{
    protected RegimeTitularidade regimeTitularidade;

    public ContaOrdem() {
        super();
    }

    public ContaOrdem(String numero, String titular, Double saldo, RegimeTitularidade regimeTitularidade) {
        super(numero, titular, saldo);
        this.regimeTitularidade = regimeTitularidade;
    }

    public RegimeTitularidade getRegimeTitularidade() {
        return regimeTitularidade;
    }

    public void setRegimeTitularidade(RegimeTitularidade regimeTitularidade) {
        this.regimeTitularidade = regimeTitularidade;
    }

    @Override
    public double saldoReal(double valor) {
        return this.saldo + valor + (valor * ((double) 1 / 100));
    }

    @Override
    public void depositar(double valor) {
        this.saldo = this.saldo  + valor - (0.01 * valor);
    }

    @Override
    public String toString() {
        return super.toString() + String.format("Regime de Titularidade: %s%n", this.regimeTitularidade.name());
    }
}
