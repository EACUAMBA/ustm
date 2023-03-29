using utilitarios;
using ConsoleTables;

namespace LerFuncionarios
{
    class EntryPoint
    {
        private static readonly List<Funcionario> funcionarios = new List<Funcionario>();
        private static readonly string cardapio = "Cardápio\n1-Registar Funcionarios;\n2-Aumentar Salario a um funcionario;\n3-Listar";
        static void Main(string[] args)
        {
            menu();
        }

        static void menu()
        {
            ConsoleUtilitarios.MostreInformacao("Seja bem vindo");
            Console.WriteLine();
            do
            {
                ConsoleUtilitarios.MostreInformacao(cardapio);
                int numeroDoCardapioSelecionado = utilitarios.ConsoleUtilitarios.PergunteIsto<int>("");
                switch (numeroDoCardapioSelecionado)
                {
                    case (1):
                        {
                            RegistarFuncionarios();
                            break;
                        }
                    case (2):
                        {
                            AumentarSalarioAUmFuncionario();
                            break;
                        }
                        case (3):
                        {
                            ListarTodosFuncionarios();
                            break;
                        }
                }
            } while (true);
        }

        private static void ListarTodosFuncionarios()
        {
            ConsoleTable tabela = new ConsoleTable("Identificador", "Nome", "Salário");
            funcionarios.ForEach(_f => tabela.AddRow(_f.Identificador, _f.Nome, _f.Salario));
            tabela.Write();
            Console.WriteLine();
        }

        static void AumentarSalarioAUmFuncionario()
        {
            ConsoleUtilitarios.MostreInformacao("Area de aumento de salário!");

            ListarTodosFuncionarios();

            Int32 identificadorDoFuncionarioSelecionado = -1;
            Funcionario? funcionario;
            do
            {
                identificadorDoFuncionarioSelecionado = ConsoleUtilitarios.PergunteIsto<Int32>("Identificor do funcionario:");

                funcionario = funcionarios.Find(_f => identificadorDoFuncionarioSelecionado.Equals(_f.Identificador));
                if (funcionario == null)
                {
                    ConsoleUtilitarios.MostreAviso($"Funcionario com o Identificador {identificadorDoFuncionarioSelecionado} não existe!");
                    identificadorDoFuncionarioSelecionado = -1;
                }
            } while (identificadorDoFuncionarioSelecionado == -1);

            float percentagemAumentar = ConsoleUtilitarios.PergunteIsto<float>("Quantos porcentos deseja aumentar?");
            funcionario!.aumentaXPorcento(percentagemAumentar);

        }

        static void RegistarFuncionarios()
        {
            ConsoleUtilitarios.MostreInformacao("Area de registo de funcionarios!");
            int numeroDeFuncinarios = utilitarios.ConsoleUtilitarios.PergunteIsto<int>("Número de funcionarios?:");

            for (int i = 0; i < numeroDeFuncinarios; i++)
            {
                Funcionario f = new Funcionario();

                bool IdentificadorRepetido = false;
                do
                {
                    f.Identificador = ConsoleUtilitarios.PergunteIsto<Int32>("Identificador único:");
                    IdentificadorRepetido = funcionarios.Any(_f => _f.Identificador.Equals(f.Identificador));
                    if (IdentificadorRepetido)
                    {
                        ConsoleUtilitarios.MostreAviso("Este valor já está a ser utilizado!");
                    }
                } while (IdentificadorRepetido);

                f.Nome = ConsoleUtilitarios.PergunteIsto<String>("Nome:");
                f.Salario = ConsoleUtilitarios.PergunteIsto<Decimal>("Salário:");

                funcionarios.Add(f);
                Console.WriteLine();
            }
        }

    }

    public class Funcionario
    {
        public Int32 Identificador { get; set; }
        public String? Nome { get; set; }
        public Decimal? Salario { get; set; }

        public void aumentaXPorcento(float percentagemAumentar)
        {
            if (percentagemAumentar < -1 || percentagemAumentar > 1)
            {
                ConsoleUtilitarios.MostreAviso("Coloque valores no intervalo de -1.0 ate +1.0.");
                return;
            }
            this.Salario += this.Salario * (decimal) percentagemAumentar;
        }
    }
}