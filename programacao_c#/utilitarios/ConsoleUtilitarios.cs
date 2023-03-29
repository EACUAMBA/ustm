namespace utilitarios;
public sealed class ConsoleUtilitarios
{
    public static T PergunteIsto<T>(string mensagem, bool obrigatorio = true)
    {
        object o = null!;
        bool invalido = true;
        do
        {
            try
            {
                Console.Write(mensagem);
                string valor = Console.ReadLine()!;

                if (typeof(T) == typeof(uint))
                {
                    o = (T)(object)uint.Parse(valor);
                }

                if (typeof(T) == typeof(int))
                {
                    o = (T)(object)int.Parse(valor);
                }

                if (typeof(T) == typeof(Int32))
                {
                    o = (T)(object)Int32.Parse(valor);
                }

                if (typeof(T) == typeof(decimal))
                {
                    o = (T)(object)decimal.Parse(valor);
                }

                if (typeof(T) == typeof(Decimal))
                {
                    o = (T)(object)Decimal.Parse(valor);
                }

                if (typeof(T) == typeof(float))
                {
                    o = (T)(object)float.Parse(valor);
                }

                if (typeof(T) == typeof(string))
                {
                    o = (T)(object)valor;
                }

                invalido = false;
            }
            catch
            {
                if (obrigatorio)
                    invalido = true;
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("O valor digitado está incorecto!");
                Console.WriteLine("Volte a inserir um valor válido!");
                Console.ResetColor();
            }
        } while (invalido);

        return (T)o;
    }

    public static void MostreAviso(string mensagem){
        Console.ForegroundColor = ConsoleColor.Yellow;
        Console.WriteLine(mensagem);
        Console.ResetColor();
    }

     public static void MostreInformacao(string mensagem){
        Console.WriteLine(mensagem);
    }
}
