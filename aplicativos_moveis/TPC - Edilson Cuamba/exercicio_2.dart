import 'dart:io';

void main(List<String> args) {
  print("Soma dos digitos");

  print("Insira o numero inteiro:");
  String integerNumberAsString     = stdin.readLineSync()!;
  List<String> digitsAsArrayString = integerNumberAsString.split("");
  int digitsSumResult              = digitsAsArrayString.map((digitAsString) => int.parse(digitAsString)).reduce((value, acomulator) => value + acomulator);

  print("A soma dos digitos é ${digitsSumResult}");
}
