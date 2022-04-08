package com.eacuamba.dev.iii_semestre.trabalho_individual_1.exercicio_8;

import com.eacuamba.dev.utils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class App extends JFrame {
public static void main(String[] args) throws IOException {
        App app = new App();
        app.setSize(500, 500);
        app.setLocationRelativeTo(null);
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setLayout(new BorderLayout());
        String directoryPath = JOptionPane.showInputDialog(app, "Enter the file path:");
        Path path = Paths.get(directoryPath);
        Path absolutePath = path.toAbsolutePath();
        if (Files.exists(absolutePath)) {
                String[] lines = FileUtils.readFromFileToArray(absolutePath);
                StringBuilder builder = new StringBuilder();
                Arrays.stream(lines).forEach(s -> builder.append(s + "\n"));
                JOptionPane.showMessageDialog(app, String.format("The file '%s' contains:\n\n %s", absolutePath, builder.toString()));
        } else {
            JOptionPane.showMessageDialog(app, String.format("The file or directory '%s' does not exists!", absolutePath));
        }
        System.exit(0);
    }
}
