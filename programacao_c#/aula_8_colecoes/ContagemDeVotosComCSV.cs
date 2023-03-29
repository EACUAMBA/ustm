

namespace ContagemDeVotosComCSV
{
    class EntryPoint
    {
        public static void ContarVotos()
        {
            string[] linhas = File.ReadAllLines(@"D:\projectos\ustm\programacao_c#\aula_8_colecoes\votos.csv");
            linhas
            .Skip(1)
            .GroupBy(_linha => _linha.Split(",")[0])
            .ToDictionary(_grupo => _grupo.Key, _grupo => _grupo.Select(_linha => _linha.Split(",")[1]).Sum(_voto => int.Parse(_voto)))
            .OrderBy(_dicionario => _dicionario.Value)
            .ToList()
            .ForEach(_grupo => System.Console.WriteLine($"Candidato: {_grupo.Key}, Votos: {_grupo.Value}"));
        }
    }
}