import 'dart:io';
import 'dart:math';

void main(List<String> args) {
  print("Introduza o valor de a:");
  double a = double.parse(stdin.readLineSync()!);

  print("Introduza o valor de b:");
  double b = double.parse(stdin.readLineSync()!);

  print("Introduza o valor de c:");
  double c = double.parse(stdin.readLineSync()!);

  double delta = sqrt(pow(b, 2) - 4 * a * c);

  double x1 = (b * (-1) + delta) / 2 * a;
  double x2 = (b * (-1) - delta) / 2 * a;

  print("A raizes são X' = ${x1} e X'' = ${x2} ");
}
