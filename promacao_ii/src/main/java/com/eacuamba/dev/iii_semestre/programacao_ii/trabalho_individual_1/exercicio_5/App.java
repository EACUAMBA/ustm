package com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_individual_1.exercicio_5;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class App extends JFrame {
    private static MessageDigest md5;

    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        App app = new App();
        app.setSize(500, 500);
        app.setLocationRelativeTo(null);
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setLayout(new BorderLayout());
        String firstFilePath = JOptionPane.showInputDialog(app, "Enter first file path:");
        String secondFilePath = JOptionPane.showInputDialog(app, "Enter second file path:");
        Path firstPath = Paths.get(firstFilePath).toAbsolutePath();
        Path secondPath = Paths.get(secondFilePath).toAbsolutePath();

        if (Files.exists(firstPath) && Files.exists(secondPath)) {
            byte[] allBytesFirst = Files.readAllBytes(firstPath);
            byte[] allBytesSecond = Files.readAllBytes(secondPath);

            byte[] digestFirst = md5.digest(allBytesFirst);
            byte[] digestSecond = md5.digest(allBytesSecond);

            boolean diferrent = false;
            if(digestFirst.length != digestSecond.length){
                diferrent= true;
            }else{
                for(int i = digestFirst.length-1; i >= 0; i--){
                    if(digestFirst[i] != digestSecond[i]){
                        diferrent= true;
                        break;
                    }
                }
            }
            if(diferrent){
                JOptionPane.showMessageDialog(app, String.format("The first file '%s' and the second file '%s' are not the same!", firstFilePath, secondFilePath));
            }else{
                JOptionPane.showMessageDialog(app, String.format("The first file '%s' and the second file '%s' are the same!", firstFilePath, secondFilePath));
            }


        } else {
            JOptionPane.showMessageDialog(app, "The one of the given files does not exists!");
        }
        System.exit(0);
    }
}
