import javax.swing.*;

public class SquareArea {
    static double width = 0;
    static double height = 0;
    static double result = 0;

    public static void main(String[] args) {
        askValues();
        noReturnAndNoArguments();
        printResult("noParameterAndNoArguments", result);

        askValues();
        noReturnAndWithArguments(width, height);
        printResult("noReturnAndWithArguments", result);

        askValues();
        double withReturnAndWithArgumentsResult = withReturnAndWithArguments(width, height);
        printResult("noReturnAndWithArguments", withReturnAndWithArgumentsResult);

        askValues();
        double withReturnAndNoArgumentsResult = withReturnAndNoArguments();
        printResult("withReturnAndNoArguments", withReturnAndNoArgumentsResult);

        System.out.println("The end!");
    }

    static double withReturnAndNoArguments() {
        return width * height;
    }

    static double withReturnAndWithArguments(double width, double height) {
        return width * height;
    }

    static void noReturnAndWithArguments(double width, double height) {
        result = width * height;
    }

    static void noReturnAndNoArguments() {
        result = width * height;
    }

    static void askValues() {
        String heightAsString = JOptionPane.showInputDialog(null, "Insira a altura: ");
        height = Double.parseDouble(heightAsString);

        String widthAsString = JOptionPane.showInputDialog(null, "Insira o comprimento: ");
        width = Double.parseDouble(widthAsString);
    }

    static void printResult(String methodCaller, double result) {
        JOptionPane.showMessageDialog(null, String.format("%s: A area do rectangle (comprimento: %.2f, altura: %.2f) é %.2f%n", methodCaller, width, height, result));
    }
}