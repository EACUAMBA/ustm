package com.eacuamba.dev.utils;

import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;

public class FileUtils {

    public static void writeToFile(Path absolutePath, String content, boolean override) throws IOException {
        File file = absolutePath.toFile();
        FileWriter fileWriter = new FileWriter(file, !override);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        if (!override)
            bufferedWriter.newLine();
        bufferedWriter.write(content);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public static String readFromFile(Path absolutePath) throws IOException {
        FileReader fileReader = new FileReader(absolutePath.toFile());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String[] lines = bufferedReader.lines().toArray(String[]::new);
        StringBuilder builder = new StringBuilder();
        Arrays.stream(lines).forEach(builder::append);
        return builder.toString();
    }

    public static String[] readFromFileToArray(Path absolutePath) throws IOException {
        FileReader fileReader = new FileReader(absolutePath.toFile());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        return bufferedReader.lines().toArray(String[]::new);

    }
}
