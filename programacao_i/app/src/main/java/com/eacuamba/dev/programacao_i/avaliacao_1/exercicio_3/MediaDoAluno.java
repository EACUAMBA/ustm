package com.eacuamba.dev.programacao_i.avaliacao_1.exercicio_3;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class MediaDoAluno {
    private URL url = getClass().getResource("/");

    public static void main(String[] args) throws IOException, URISyntaxException {
        Scanner input = new Scanner(System.in);

        // Brincando um pouco com Streams
        URI uri = new MediaDoAluno().url.toURI();
        Path path = Paths.get(uri).resolve("files");

        if (!Files.exists(path)) {
            Files.createDirectories(path);
            path = Files.createFile(path.resolve("file.txt"));
        }
        PrintStream output_file = new PrintStream(path.toFile());

        // Para imprimir no console
        PrintStream output_console = System.out;

        output_file.println("Media do Aluno");
        output_console.println("Media do Aluno");

        output_file.println("Introduza o nome do Aluno: ");
        output_console.println("Introduza o nome do Aluno: ");
        String nome = input.nextLine();

        output_file.println("Introduza a nota da 1 avaliação: ");
        output_console.println("Introduza a nota da 1 avaliação: ");
        Double nota_1 = input.nextDouble();

        output_file.println("Introduza a nota da 2 avaliação: ");
        output_console.println("Introduza a nota da 2 avaliação: ");
        Double nota_2 = input.nextDouble();

        output_file.println("Introduza a nota da 3 avaliação: ");
        output_console.println("Introduza a nota da 3 avaliação: ");
        Double nota_3 = input.nextDouble();

        Aluno aluno = new Aluno(nome, nota_1, nota_2, nota_3);

        output_file.printf("%nO aluno " + aluno.getNome() + " teve uma media de %.2f valores.",
                aluno.getMediaAritmetica());
        output_console.printf("%nO aluno " + aluno.getNome() + " teve uma media de %.2f valores.",
                aluno.getMediaAritmetica());

        output_console.println("Fechando o ficheiro.");
        output_file.flush();
        output_file.close();
    }
}
