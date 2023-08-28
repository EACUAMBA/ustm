using System;

namespace PreparacaoParaExame
{
 class Program
 {
    public static void Main(String[] args)
    {

            ContaBancaria cb = new ContaBancaria();
            Console.WriteLine(cb.ToString());

            Console.WriteLine("\nDepositar 200");
            cb.Depositar(200);
            Console.WriteLine(cb.ToString());

            Console.WriteLine("\nLevantar 200");
            cb.Levantar(200);
            Console.WriteLine(cb.ToString());

            Console.WriteLine("\nLevantar 500");
            cb.Levantar(500);
            Console.WriteLine(cb.ToString());

            Console.WriteLine("\nDepositar 300");
            cb.Depositar(300);
            Console.WriteLine(cb.ToString());
        }

 }

    class Professor
    {

    }
}