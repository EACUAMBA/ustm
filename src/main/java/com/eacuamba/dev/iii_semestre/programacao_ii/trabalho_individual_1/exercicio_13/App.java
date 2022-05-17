package com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_individual_1.exercicio_13;

import com.eacuamba.dev.utils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App extends JFrame {
public static void main(String[] args) throws IOException {
        App app = new App();
        app.setSize(500, 500);
        app.setLocationRelativeTo(null);
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setLayout(new BorderLayout());
        String directoryPath = JOptionPane.showInputDialog(app, "Enter the file pathe:");
        Path path = Paths.get(directoryPath);
        Path absolutePath = path.toAbsolutePath();
        if (Files.exists(absolutePath)) {
                char _char = JOptionPane.showInputDialog(app, "Enter the character to find the number of repetitions:").charAt(0);

                String content = FileUtils.readFromFile(absolutePath);
                char[] charArray = content.toCharArray();
                int repetitions= 0;
                for(char c:charArray){
                        if(c == _char)
                                repetitions++;
                }
                JOptionPane.showMessageDialog(app, String.format("The charecter '%c' have %d repetitions.", _char, repetitions));
        } else {
            JOptionPane.showMessageDialog(app, String.format("The file or directory '%s' does not exists!", absolutePath));
        }
        System.exit(0);
    }
}
