package com.eacuamba.dev.iii_semestre.trabalho_individual_1.exercicio_6;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.text.NumberFormat;
import java.util.List;
import java.util.*;

public class App extends JFrame {
public static void main(String[] args) throws IOException {
        App app = new App();
        app.setSize(500, 500);
        app.setLocationRelativeTo(null);
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setLayout(new BorderLayout());
        String directoryPath = JOptionPane.showInputDialog(app, "Enter the directory or file path to check size:");
        Path path = Paths.get(directoryPath);
        Path absolutePath = path.toAbsolutePath();
        if (Files.exists(absolutePath)) {
            long size = Files.size(absolutePath);
            JOptionPane.showMessageDialog(app, String.format("The file size is: \n'%s' bytes\n'%s' kilobytes\n'%s' megabytes\n'%s' gigabytes",(size), NumberFormat.getNumberInstance(new Locale("pt", "MZ")).format((double)size/1024
            ), NumberFormat.getNumberInstance(new Locale("pt", "MZ")).format((  ((double)size/1024)/1024)), NumberFormat.getNumberInstance(new Locale("pt", "MZ")).format((((double)size/1024)/1024/1024))));
        } else {
            JOptionPane.showMessageDialog(app, String.format("The file or directory '%s' does not exists!", absolutePath));
        }
        System.exit(0);
    }
}
