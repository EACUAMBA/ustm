package com.eacuamba.dev.iii_semestre.trabalho_individual_1.exercicio_16;

import com.eacuamba.dev.iii_semestre.trabalho_individual_1.exercicio_15.Estudante;
import com.eacuamba.dev.utils.FileObjectUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class App extends JFrame {
public static void main(String[] args) throws IOException, ClassNotFoundException {
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
                Object[] objects = (Object[]) FileObjectUtils.readObjects(absolutePath);

                JOptionPane.showMessageDialog(app, String.format("There are %d objects student.", objects.length));
        } else {
            JOptionPane.showMessageDialog(app, String.format("The file or directory '%s' does not exists!", absolutePath));
        }
        System.exit(0);
    }
}
