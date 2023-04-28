import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * @author Edilson Alexandre Cuamba
 * 28 de 04 de 2023 as 10:20
 */
public class MatrizDePecas {
    public static void main(String[] args) {
        String[] numeros = "65313243655652465636662646166324536256223665525256463525566534512662656666".split("");
        LinkedList<Integer> pecas = Arrays.stream(numeros).map(Integer::parseInt).collect(Collectors.toCollection(LinkedList::new));

        int altura = 20;
        int largura = 10;
        Integer[][] matriz = new Integer[altura][largura];

        for (int i = 0; i < pecas.size(); i++) {
            Integer p = pecas.get(i);
            preenchePeca(p, matriz);
            printArrayAtFile(matriz, p, i);
        }
        printConsoleArray(matriz);
    }

    public static void preenchePeca(Integer peca, Integer[][] matriz) {
        boolean preencheu = false;

        for (int i = matriz.length - 1; i >= 0; i--) {
            for (int j = 0; j < matriz[i].length; j++) {
                boolean bordaInferior = i == matriz.length - 1;
                try {
                    switch (peca) {
                        case 1: {
                            boolean temEspacoParaPeca = isNull(matriz[i][j]) && isNull(matriz[i][j + 1]) && isNull(matriz[i][j + 2]);

                            boolean embaixoTemEspacoVazio = false;
                            if (!bordaInferior) {
                                embaixoTemEspacoVazio = isNull(matriz[i + 1][j]) || isNull(matriz[i + 1][j + 1]) || isNull(matriz[i + 1][j + 2]);
                            }

                            if (temEspacoParaPeca && !embaixoTemEspacoVazio) {
                                matriz[i][j] = peca;
                                matriz[i][j + 1] = peca;
                                matriz[i][j + 2] = peca;
                                preencheu = true;
                            } else {
                                continue;
                            }
                            break;
                        }
                        case 2: {
                            boolean temEspacoParaPeca = isNull(matriz[i][j]) && isNull(matriz[i - 1][j]) && isNull(matriz[i - 2][j]);

                            boolean embaixoTemEspacoVazio = false;
                            if (!bordaInferior) {
                                embaixoTemEspacoVazio = isNull(matriz[i + 1][j]);
                            }

                            if (temEspacoParaPeca && !embaixoTemEspacoVazio) {
                                matriz[i][j] = peca;
                                matriz[i - 1][j] = peca;
                                matriz[i - 2][j] = peca;
                                preencheu = true;
                            } else {
                                continue;
                            }
                            break;
                        }
                        case 3: {
                            boolean temEspacoParaPeca = isNull(matriz[i][j]) && isNull(matriz[i][j + 1]) && isNull(matriz[i - 1][j + 1]) && isNull(matriz[i][j + 2]);

                            boolean embaixoTemEspacoVazio = false;
                            if (!bordaInferior) {
                                embaixoTemEspacoVazio = isNull(matriz[i + 1][j]) || isNull(matriz[i + 1][j + 1]) || isNull(matriz[i + 1][j + 2]);
                            }

                            if (temEspacoParaPeca && !embaixoTemEspacoVazio) {
                                matriz[i][j] = peca;
                                matriz[i][j + 1] = peca;
                                matriz[i - 1][j + 1] = peca;
                                matriz[i][j + 2] = peca;
                                preencheu = true;
                            } else {
                                continue;
                            }
                            break;
                        }
                        case 4: {
                            boolean temEspacoParaPeca = isNull(matriz[i][j]) && isNull(matriz[i - 1][j - 1]) && isNull(matriz[i - 1][j + 1]) && isNull(matriz[i - 1][j + 2]);

                            boolean embaixoTemEspacoVazio = isNull(matriz[i][j - 1]) || isNull(matriz[i][j + 1]);
                            if (!bordaInferior) {
                                embaixoTemEspacoVazio = embaixoTemEspacoVazio || isNull(matriz[i + 1][j]);
                            }


                            if (temEspacoParaPeca && !embaixoTemEspacoVazio) {
                                matriz[i][j] = peca;
                                matriz[i - 1][j - 1] = peca;
                                matriz[i - 1][j] = peca;
                                matriz[i - 1][j + 1] = peca;
                                preencheu = true;
                            } else {
                                continue;
                            }
                            break;
                        }
                        case 5: {
                            boolean temEspacoParaPeca = isNull(matriz[i][j]) && isNull(matriz[i][j + 1]) && isNull(matriz[i - 1][j]) && isNull(matriz[i - 1][j + 1]);

                            boolean embaixoTemEspacoVazio = false;
                            if (!bordaInferior) {
                                embaixoTemEspacoVazio = isNull(matriz[i + 1][j]) || isNull(matriz[i + 1][j + 1]);
                            }

                            if (temEspacoParaPeca && !embaixoTemEspacoVazio) {
                                matriz[i][j] = peca;
                                matriz[i][j + 1] = peca;
                                matriz[i - 1][j] = peca;
                                matriz[i - 1][j + 1] = peca;
                                preencheu = true;
                            } else {
                                continue;
                            }
                            break;
                        }
                        case 6: {
                            boolean temEspacoParaPeca = isNull(matriz[i][j]);

                            boolean embaixoTemEspacoVazio = false;
                            if (!bordaInferior) {
                                embaixoTemEspacoVazio = isNull(matriz[i + 1][j]);
                            }

                            if (temEspacoParaPeca && !embaixoTemEspacoVazio) {
                                matriz[i][j] = peca;
                                preencheu = true;
                            } else {
                                continue;
                            }
                            break;
                        }
                        default: {
                        }
                    }

                    if (preencheu) {
                        imprimeMatriz(peca, matriz);
                        return;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
    }

    private static void imprimeMatriz(Integer peca, Integer[][] matriz) {
        System.out.println();
        System.out.println("peça: " + peca);
        printConsoleArray(matriz);
    }

    public static void printConsoleArray(Integer[][] array) {
        System.out.println();
        for (Integer[] a : array) {
            for (Integer b : a) {
                System.out.printf("%s, ", Objects.toString(b, " "));
            }
            System.out.println();
        }

    }

    public static void printArrayAtFile(Integer[][] array, Integer pecaInserida, Integer step) {
        try {
            String fileName = "#" + step + " - matriz com nova peça " + pecaInserida + " .txt";
            Path dir = Paths.get("./steps");
            Path directory = Files.createDirectories(dir);
            Path path = Files.createFile(directory.resolve(fileName));

            PrintStream printStream = new PrintStream(Files.newOutputStream(path));

            printStream.printf("Peça inserida %d ", pecaInserida);
            printStream.println();
            for (Integer[] a : array) {
                for (Integer b : a) {
                    printStream.printf("%s, ", Objects.toString(b, " "));
                }
                printStream.println();
            }
            printStream.flush();
            printStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
