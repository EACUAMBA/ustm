import 'dart:io';

void main() {
  print("Media do Estudante");

  List<double> values = [];
  late bool addMore;

  for (int i = 1; i <= 4; i++) {
    print("Introduza a nota do ${i} teste:");
    double value = double.parse(stdin.readLineSync()!);
    values.add(value);
  }
  ;

  double total = values.reduce((value, acomulator) => value + acomulator);
  double media = total / values.length;

  print("A media é ${media} ");
  String resultAsString = "Reprovou";

  if (media >= 14) {
    resultAsString = "Dispensado";
  }

  if (media < 14 && media >= 9.5) {
    resultAsString = "Aprovado";
  }

  if (media < 9.5) {
    resultAsString = "Reprovado";
  }
  print("O estudante foi ${resultAsString}");
}
