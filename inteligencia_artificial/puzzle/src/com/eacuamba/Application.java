package com.eacuamba;

import com.eacuamba.puzzle.Runner;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.concurrent.*;

public class Application {


    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Puzzle by Edilson Alexandre Cuamba");
        int workers = 10;

        ExecutorCompletionService<StringBuffer> executorCompletionService = new ExecutorCompletionService<>(executorService);

        for (int i = 0; i < workers; i++) {
            Runner runner = new Runner();
           executorCompletionService.submit(runner::run);
        }

        System.out.println("Working, waiting for the first completed task only!");
        Future<StringBuffer> stringBufferFuture = executorCompletionService.take();

        System.out.println("There is a completed task!");
        pushToFile(stringBufferFuture.get().toString(), LocalDateTime.now());

        executorService.shutdown();

        System.out.println("The end!");
        System.exit(0);
    }

    public static void pushToFile(String text, LocalDateTime localDateTime) {
        Path filePath = Paths.get(String.format("D:/puzzle_resolution_%s.txt", DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH.mm.ss").format(localDateTime))).normalize();

        System.out.println("Printing to file " + filePath.toAbsolutePath());

        try {
            Files.createFile(filePath);
        } catch (Exception e) {
            System.out.printf("Failed to create file %s", e.getMessage());
        }

        String fileName = filePath.toString();
        StringBuilder textInFile = new StringBuilder();

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {

            Iterator<String> iterator = bufferedReader.lines().iterator();

            while (iterator.hasNext()) {
                textInFile.append(iterator.next());

                if (iterator.hasNext()) {
                    textInFile.append("\n");
                }
            }

        } catch (Exception e) {
            System.out.println("Failed to read file! Error: " + e.getMessage());
        }

        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter pw = new PrintWriter(bufferedWriter, true);
        ) {
            textInFile.append(text);

            pw.print(textInFile);
        } catch (Exception e) {
            System.out.println("Failed to save file! Error: " + e.getMessage());
        }

        System.out.println("Printed!");
    }
}
