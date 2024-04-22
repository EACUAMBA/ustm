import 'dart:math';

//isto não devia ser assim, acho uqe não tema performance desejada. devia exixtir uma forma melhor.
void main(List<String> args) {
  var r = new Random();
  List<int> randomIntegers = List.generate(10, (index) => r.nextInt(10));
  print(randomIntegers);

  int higher = findHigher(randomIntegers);
  randomIntegers.removeWhere((element) => element == higher);
  int secondHigher = findHigher(randomIntegers);

  print("O segundo maior valor é ${secondHigher} e o primeiro é ${higher}");
}

int findHigher(List<int> values) {
  int higher = values[0];
  for (int i = 1; i < values.length; i++) {
    int currentValue = values[i];
    if (higher < currentValue) {
      higher = currentValue;
    }
  }
  return higher;
}
