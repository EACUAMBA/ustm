package com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_individual_1.exercicio_9;

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
        String directoryPath = JOptionPane.showInputDialog(app, "Enter the directory or file path to check size:");
        Path path = Paths.get(directoryPath);
        Path absolutePath = path.toAbsolutePath();
        if (Files.exists(absolutePath)) {
                String content = JOptionPane.showInputDialog(app, "Enter the content to write:");
                int answer = JOptionPane.showConfirmDialog(app, "Override the old content?", "", JOptionPane.YES_NO_OPTION);

                if(answer == JOptionPane.YES_OPTION){
                        FileUtils.writeToFile(absolutePath, content, true);
                }else{
                        FileUtils.writeToFile(absolutePath, content, false);
                }
        } else {
            JOptionPane.showMessageDialog(app, String.format("The file or directory '%s' does not exists!", absolutePath));
        }
        System.exit(0);
    }
}
