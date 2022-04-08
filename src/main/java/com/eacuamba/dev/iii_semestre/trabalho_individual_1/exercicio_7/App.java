package com.eacuamba.dev.iii_semestre.trabalho_individual_1.exercicio_7;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.Locale;

public class App extends JFrame {
public static void main(String[] args) throws IOException {
        App app = new App();
        app.setSize(500, 500);
        app.setLocationRelativeTo(null);
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setLayout(new BorderLayout());
        String directoryPath = JOptionPane.showInputDialog(app, "Enter the directory or file path:");
        Path path = Paths.get(directoryPath);
        Path absolutePath = path.toAbsolutePath();
        if (Files.isDirectory(absolutePath)) {
            JOptionPane.showMessageDialog(app, String.format("The path '%s' points to a directory!", absolutePath));
        } else {
            JOptionPane.showMessageDialog(app, String.format("The path '%s' does not point to a directory!", absolutePath));
        }
        System.exit(0);
    }
}
