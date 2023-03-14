using System;
using System.Globalization;

namespace Aula5ClassesMetodos
{
    class LerDadosDeDuasPessoasDepoisMostraMaisVelha
    {
        static void Main(string[] args)
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

        class Pessoa
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
}