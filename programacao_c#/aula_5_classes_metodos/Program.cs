using System;
using System.Globalization;

namespace Aula5ClassesMetodos
{
    public class Application{
        static void Main(string[] arguments){
            LerDadosDeDoisFuncionariosDepoisMostraSalariadoMaisAlto.FuncionarioMaisBemPago();
        }
    }
    public class LerDadosDeDuasPessoasDepoisMostraMaisVelha
    {
        public static void PessoaMaisVelha(string[] args)
        {
            Pessoa pessoaMaisVelha = null;

            for (int i = 0; i < 5; i++)
            {
                Console.WriteLine($"Dados da pessoa {i + 1}");
                Pessoa pessoa = new Pessoa();

                Console.Write("Nome: ");
                pessoa.Nome = Console.ReadLine();

                Console.Write("Idade: ");
                pessoa.Idade = int.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);

                if (pessoaMaisVelha != null)
                {
                    if (pessoa.Idade > pessoaMaisVelha.Idade)
                        pessoaMaisVelha = pessoa;
                }
                else
                {
                    pessoaMaisVelha = pessoa;
                }
                Console.WriteLine();
            }

            Console.WriteLine($"Pessoa mais velha: {pessoaMaisVelha}");
        }

        public class Pessoa
        {
            public string Nome { get; set; }
            public int Idade { get; set; }

            override
            public string ToString()
            {
                return Nome;
            }
        }
    }

     public class LerDadosDeDoisFuncionariosDepoisMostraSalariadoMaisAlto
    {
        public static void FuncionarioMaisBemPago()
        {
            Funcionario funcionarioMaisBemPago = null;

            for (int i = 0; i < 2; i++)
            {
                Console.WriteLine($"Dados do funcionario {i + 1}");
                Funcionario pessoa = new Funcionario();

                Console.Write("Nome: ");
                pessoa.Nome = Console.ReadLine();

                Console.Write("Salario: ");
                pessoa.Salario = decimal.Parse(Console.ReadLine(), CultureInfo.InvariantCulture);

                if (funcionarioMaisBemPago != null)
                {
                    if (pessoa.Salario > funcionarioMaisBemPago.Salario)
                        funcionarioMaisBemPago = pessoa;
                }
                else
                {
                    funcionarioMaisBemPago = pessoa;
                }
                Console.WriteLine();
            }

            Console.WriteLine($"Funcionario mais bem pago: {funcionarioMaisBemPago}");
        }
        public class Funcionario
        {
            public string Nome { get; set; }
            private  decimal _salario;

            public decimal Salario{
                get{ return _salario; }
                set {
                    if(value< 0){
                        throw new ArgumentException("Argumento invalido!");
                    }

                    this._salario = value;
                }
            }

            override
            public string ToString()
            {
                return Nome;
            }
        }
    }
}