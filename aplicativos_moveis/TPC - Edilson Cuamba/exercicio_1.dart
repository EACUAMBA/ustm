import 'dart:io';

void main() {
  print("Media Aritimetica");

  List<double> values = [];
  late bool addMore;

  do {
    print("Introduza o valor (ex.: 10 ou 10.5):");
    double value = double.parse(stdin.readLineSync()!);
    values.add(value);

    print("Queres adicionar mais um? (1 para sim, 2 para não)");
    addMore = stdin.readLineSync()! == "1";
  } while (addMore);

  double total = values.reduce((value, acomulator) => value + acomulator);
  double media = total / values.length;

  print("a media é ${media}");
}
