package com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_individual_1.exercicio_15;

import com.eacuamba.dev.utils.FileObjectUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            String name = JOptionPane.showInputDialog(app, "Enter student name:");
            LocalDate dateOfBirth = LocalDate.parse(JOptionPane.showInputDialog(app, "Enter student date of birth (DD-MM-YYYY):"), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String genre = JOptionPane.showInputDialog(app, "Enter student genre (M/F):");
            String code = JOptionPane.showInputDialog(app, "Enter student code/id:");
            Estudante estudante = new Estudante();
            estudante.setCodigo(code);
            estudante.setDataNascimento(dateOfBirth);
            estudante.setNome(name);
            estudante.setGenero(genre);

            Object[] objects;
            try {
                objects = (Object[]) FileObjectUtils.readObjects(absolutePath);
            } catch (Exception e) {
                objects = new Object[]{};
            }
            List<Object> objectList = new ArrayList<>(Arrays.asList(objects));
            objectList.add(estudante);

            FileObjectUtils.writeObject(absolutePath, objectList.toArray(new Object[]{}));

            JOptionPane.showMessageDialog(app, "The student data was successfully saved.");
        } else {
            JOptionPane.showMessageDialog(app, String.format("The file or directory '%s' does not exists!", absolutePath));
        }
        System.exit(0);
    }
}
