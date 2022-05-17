package com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_individual_1.exercicio_3;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App extends JFrame {
    private Object[] columns = new Object[]{"Index", "Path", "File?"};
    private List<Object[]> data = new ArrayList<>();

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
        if (Files.exists(absolutePath))
            JOptionPane.showMessageDialog(app, String.format("The file or directory '%s' exists!", absolutePath));
        else
            JOptionPane.showMessageDialog(app, String.format("The file or directory '%s' does not exists!", absolutePath));
        System.exit(0);
    }
}
