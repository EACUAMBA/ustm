using System;
using System.Globalization;

namespace PreparacaoParaExame
{

    public class ContaBancaria
    {
        private Double _saldo = 0.0;
        public Double Saldo
        {
            get { return this._saldo; }
            set { this._saldo = value; }
        }

        private DateTime _dataAbertura = DateTime.Now;
        public DateTime DataAbertura
        {
            get { return this._dataAbertura; }
        }

        public ContaBancaria()
        {
            this._saldo = 0.0;
            this._dataAbertura = DateTime.Now;
        }

        public String GetDataAberturaFormatada()
        {
            return this.DataAbertura.ToString("dd/MM/yyyy");
        }

        public String GetSaldoFormatado()
        {
            CultureInfo mzCulture = new CultureInfo("pt-MZ");
            return this.Saldo.ToString("C3", mzCulture);
        }

        public void Depositar(Double valor)
        {
            this.Saldo += valor;
        }

        public void Levantar(Double valor)
        {
            if (this.Saldo >= valor)
                this.Saldo -= valor;
            else
                Console.WriteLine("Sando insuficiente para concluir a transação!");
        }

        override
        public String ToString()
        {
            return "Estado da conta\nSaldo: " + this.GetSaldoFormatado() + "\nData de Abertura: " + this.GetDataAberturaFormatada();
        }

    }
}