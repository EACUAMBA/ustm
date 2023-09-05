public class SquareAreaOutsideOfClassDefinition {
    public static void main(String[] args) {
        SquareArea.askValues();
        SquareArea.noReturnAndNoArguments();
        System.out.println("noParameterAndNoArguments: The area is " + SquareArea.result);

        SquareArea.askValues();
        SquareArea.noReturnAndWithArguments(SquareArea.width, SquareArea.height);
        System.out.println("noReturnAndWithArguments: The area is " + SquareArea.result);

        SquareArea.askValues();
        double withReturnAndWithArgumentsResult = SquareArea.withReturnAndWithArguments(SquareArea.width, SquareArea.height);
        System.out.println("noReturnAndWithArguments: The area is " + withReturnAndWithArgumentsResult);

        SquareArea.askValues();
        double withReturnAndNoArgumentsResult = SquareArea.withReturnAndNoArguments();
        System.out.println("noReturnAndWithArguments: The area is " + withReturnAndNoArgumentsResult);

        System.out.println("The end!");
    }
}
