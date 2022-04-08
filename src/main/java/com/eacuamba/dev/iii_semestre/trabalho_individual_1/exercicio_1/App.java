package com.eacuamba.dev.iii_semestre.trabalho_individual_1.exercicio_1;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class App extends JFrame{
    private Object[] columns = new Object[]{"Index", "Path", "File?"};
    private List<Object[]> data = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        App app = new App();
        app.setSize(500, 500);
        app.setLocationRelativeTo(null);
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setLayout(new BorderLayout());

        String directoryPath = JOptionPane.showInputDialog(app, "Enter the directory path:");

        Path path = Paths.get(directoryPath);
        Path absolutePath = path.toAbsolutePath();
        if(Files.isDirectory(absolutePath)){
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(absolutePath);
            Iterator<Path> iterator = directoryStream.iterator();

            int index= 1;
            while(iterator.hasNext()) {
                Path next = iterator.next();
                app.data.add(new String[]{"" + (index++), next.toAbsolutePath().toString(), "" + !Files.isDirectory(next)});
            }
            JTable jTable = new JTable(app.data.toArray(new Object[0][0]), app.columns);
            JScrollPane jScrollPane = new JScrollPane(jTable);
            app.add(jScrollPane, BorderLayout.CENTER);
            app.setVisible(true);

        }


    }
}
