import javax.swing.*;

public class SquareAreaWithMenu {
    public static void main(String[] args) {
        boolean _continue = true;

        do {
            String options = String.format(
                    "%s%n%s%n%s%n%s%n%s",
                    "1 - Calcular utilizando o metodo sem retorno e sem parameto;",
                    "2 - Calcular utilizando o metodo sem retorno e com parameto;",
                    "3 - Calcular utilizando o metodo com retorno e sem parameto;",
                    "4 - Calcular utilizando o metodo com retorno e set parameto;",
                    "5 - Sair;"
            );

            String selectedOptionAsString = JOptionPane.showInputDialog(null, options);
            if(selectedOptionAsString == null){
                selectedOptionAsString = "5";
            }

            try {
                int selectedOption = Integer.parseInt(selectedOptionAsString);

                if (selectedOption < 1 || selectedOption > 5) {
                    throw new RuntimeException("Valor invalido!");
                }

                if (selectedOption == 5) {
                    _continue = false;
                } else {
                    calc(selectedOption);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Escolha um valor válido", "Aviso", JOptionPane.WARNING_MESSAGE);
            }

        } while (_continue);
    }

    static void calc(int selectedOption) {
        SquareArea.askValues();

        switch (selectedOption) {
            case 1 -> {
                SquareArea.noReturnAndNoArguments();
                SquareArea.printResult("noReturnAndNoArguments", SquareArea.result);
            }
            case 2 -> {
                SquareArea.noReturnAndWithArguments(SquareArea.width, SquareArea.height);
                SquareArea.printResult("noReturnAndWithArguments", SquareArea.result);
            }
            case 3 -> {
                double result = SquareArea.withReturnAndNoArguments();
                SquareArea.printResult("withReturnAndNoArguments", result);
            }
            case 4 -> {
                double result = SquareArea.withReturnAndWithArguments(SquareArea.width, SquareArea.height);
                SquareArea.printResult("withReturnAndNoArguments", result);
            }
            default -> {
            }
        }
    }
}
