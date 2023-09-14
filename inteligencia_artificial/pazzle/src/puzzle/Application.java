package puzzle;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Application {
    private final static SecureRandom secureRandom = new SecureRandom();

    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private static StringBuffer STRING_BUFFER_ALL_TIME = new StringBuffer();
    private static StringBuffer STRING_BUFFER = new StringBuffer();

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        try {
            start();
        } catch (Exception e) {
            System.out.printf("Failed to resolve the matrix. Error:%s ", e.getMessage());
        } finally {
            Application.pushToFile(STRING_BUFFER_ALL_TIME.toString(), localDateTime);
        }

    }

    public static void start() {

        addTextToBufferFormatted("Hello I'm thread %s", Thread.currentThread().getName());
        addTextToBuffer("Puzzle by Edilson Alexandre Cuamba");
        long start = System.currentTimeMillis();
        Puzzle puzzle = new Puzzle();

        addTextToBuffer("Initial State:");
        puzzle.printState();
        addTextToBuffer();

        Long iteration = 1L;
        Puzzle.Position position = new Puzzle.Position(0, 0);
        while (puzzle.isMatrixUnordered()) {

            addTextToBufferFormatted("********************************%n");
            addTextToBufferFormatted("Iteration %s", NumberFormat.getInstance(Locale.of("pt", "MZ")).format(iteration));
            addTextToBuffer();

            List<Puzzle.PositionDirection> availableChanges = puzzle.getAvailableChanges(position);
            int nextInt = secureRandom.nextInt(availableChanges.size());

            Puzzle.PositionDirection positionDirection = availableChanges.get(nextInt);
            addTextToBufferFormatted("Was chose: %s%n%n", positionDirection);

            puzzle.changePosition(position, positionDirection.target);
            position = positionDirection.target;

            addTextToBuffer("Next state:");
            puzzle.printState();
            iteration++;

            addTextToBufferFormatted("********************************%n%n");

            Application.STRING_BUFFER_ALL_TIME.append(getTextBuffered()).append("\n");
            cleanBuffer();
        }

        long end = System.currentTimeMillis();

        addTextToBufferFormatted("Took %d seconds.%n", (TimeUnit.MILLISECONDS.toSeconds(end - start)));
        Application.STRING_BUFFER_ALL_TIME.append(getTextBuffered()).append("\n");
    }


    public static synchronized void addTextToBuffer() {
        addTextToBuffer("");
    }

    public static synchronized void addTextToBuffer(String text) {
        STRING_BUFFER.append(text).append("\n");
    }

    public static synchronized void addTextToBufferFormatted(String text, Object... objects) {
        STRING_BUFFER.append(String.format(text, objects));
    }

    public static String getTextBuffered() {
        return STRING_BUFFER.toString();
    }

    public static void cleanBuffer() {
        STRING_BUFFER.delete(0, STRING_BUFFER.length() - 1);
    }

    public static void pushToFile(String text, LocalDateTime localDateTime) {
        Path filePath = Paths.get(String.format("E:/puzzle_resolution_%s.txt", DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH.mm.ss").format(localDateTime))).normalize();
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

    }
}
