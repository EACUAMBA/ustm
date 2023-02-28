using System;

class Program{
  static void Main(string[] args){
    LerTresPalavrasSeparadasPorEspaco();
  }

  static void LerUmTextoAteQuebraDeLinha(){
    Console.WriteLine("Escreva o texto:");
    string texto = Console.ReadLine();
    Console.WriteLine($"O texto escrito foi '{texto}'");
  }

  static void LerTresPalavrasUmaCadaLinha(){
    Console.WriteLine("Escreva as palavras:");
      string texto1 = Console.ReadLine();
      string texto2 = Console.ReadLine();
      string texto3 = Console.ReadLine();
  
    Console.WriteLine($"As palavras escritas foram '{texto1}'");
    Console.WriteLine($"As palavras escritas foram '{texto2}'");
    Console.WriteLine($"As palavras escritas foram '{texto3}'");
  }

  static void LerTresPalavrasSeparadasPorEspaco(){
    Console.WriteLine("Escreva as palavras Separadas pode espaços:");

    
    string texto = Console.ReadLine();
    string[] palavras = texto.Split(" ");
    
    foreach (string palavra in palavras)
    {
    Console.WriteLine($"As palavras escritas foram '{palavra}'");
    }
  }
}