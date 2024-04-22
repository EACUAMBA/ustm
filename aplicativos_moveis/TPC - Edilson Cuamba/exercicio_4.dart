import 'dart:io';

void main() {
  print("Procura o palindrono");

  String canBePalindrono = stdin.readLineSync()!;
  String mirror = "";
  for (int i = canBePalindrono.length - 1; i >= 0; i--) {
    mirror = mirror + canBePalindrono[i];
  }

  bool isPalindrono = canBePalindrono.toLowerCase() == mirror.toLowerCase();

  print("Esta palavra ${isPalindrono ? "é" : "não é"} palíndromo.");
}
