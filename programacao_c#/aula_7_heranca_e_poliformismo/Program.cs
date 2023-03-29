using System;
using System.Globalization;
using utilitarios;

namespace aula_7_heranca_e_poliformismo
{
    class GestaoDeFuncionarios
    {
        static void Main(string[] args)
        {
            do
            {
                uint numeroDeFuncionarios = ConsoleUtilitarios.pergunteIsto<uint>("Quantos funcionarios vai registar?");
                Funcionario[] funcionarios = new Funcionario[numeroDeFuncionarios];

                for (int i = 0; i < numeroDeFuncionarios; i++)
                {
                    Funcionario funcionario = new Funcionario();
                    funcionario.Nome = ConsoleUtilitarios.pergunteIsto<string>("Nome:");
                    funcionario.HorasTrabalhadas = ConsoleUtilitarios.pergunteIsto<uint>("Horas Trabalhadas:");
                    funcionario.ValorPorHora = ConsoleUtilitarios.pergunteIsto<decimal>("Valor por hora:");

                    string isOutSourced = ConsoleUtilitarios.pergunteIsto<string>("Outsourced (y/n)?");
                    if (isOutSourced.Equals("y"))
                    {
                        FuncionaioTerceirizado funcionaioTerceirizado = new FuncionaioTerceirizado(funcionario);
                        funcionaioTerceirizado.DespesaAdicional = ConsoleUtilitarios.pergunteIsto<decimal>("Despesa Adicional:");
                        funcionario = funcionaioTerceirizado;
                    }

                    funcionarios[i] = funcionario;
                }


                Console.WriteLine("\n\nFuncionarios:");
                foreach (Funcionario funcionario in funcionarios)
                {
                    Console.WriteLine(funcionario);
                }


            } while (false);
        }
    }



    class Funcionario
    {
        private string _nome;
        private uint _horasTrabalhadas;
        private decimal _valorPorHora;

        public string Nome
        {
            get { return this._nome; }
            set { this._nome = value; }
        }


        public uint HorasTrabalhadas
        {
            get { return this._horasTrabalhadas; }
            set { this._horasTrabalhadas = value; }
        }

        public decimal ValorPorHora
        {
            get { return this._valorPorHora; }
            set { this._valorPorHora = value; }
        }


        public decimal CacularSalario()
        {
            return this._horasTrabalhadas * this._valorPorHora;
        }

        override
        public string ToString()
        {
            return $"{this.Nome} - {this.CacularSalario().ToString("C2", CultureInfo.InvariantCulture)}";
        }
    }

    class FuncionaioTerceirizado : Funcionario
    {
        private decimal _despesaAdicional;

        public FuncionaioTerceirizado(Funcionario funcionario)
        {
            base.HorasTrabalhadas = funcionario.HorasTrabalhadas;
            base.Nome = funcionario.Nome;
            base.ValorPorHora = funcionario.ValorPorHora;
        }

        public decimal DespesaAdicional
        {
            get { return this._despesaAdicional; }
            set { this._despesaAdicional = value; }
        }

        public decimal GetBonus()
        {
            return (this._despesaAdicional * 1.1M);
        }

        public new decimal CacularSalario()
        {
            return base.CacularSalario() + this.GetBonus();
        }

        override
        public string ToString()
        {
            return $"{this.Nome} - {this.CacularSalario().ToString("C2", new CultureInfo("pt-MZ"))}";
        }
    }
}

