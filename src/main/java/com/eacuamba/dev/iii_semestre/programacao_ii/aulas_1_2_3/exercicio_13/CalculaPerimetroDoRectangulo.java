package com.eacuamba.dev.iii_semestre.programacao_ii.aulas_1_2_3.exercicio_13;

import com.eacuamba.dev.utils.ConsoleBase;

import java.math.BigDecimal;

public class CalculaPerimetroDoRectangulo extends ConsoleBase {
    public static void main(String[] args) {
        output.println("Calcula o perímetro de uma rectângulo");
        BigDecimal valorDoComprimento = getValorNumericoAsBigDecimalFromConsole("Valor do comprimento");
        BigDecimal valorDaLargura = getValorNumericoAsBigDecimalFromConsole("Valor da largura");

        BigDecimal perimetro = valorDoComprimento.multiply(BigDecimal.valueOf(2D)).add(valorDaLargura.multiply(BigDecimal.valueOf(2D)));
        output.printf("O valor do perímetro de um rectângulo com o comprimento valendo %.2f e a altura %.2f é de %.2f.", valorDoComprimento, valorDaLargura, perimetro);
    }
}
