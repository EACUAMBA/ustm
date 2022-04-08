package com.eacuamba.dev.iii_semestre.trabalho_individual_1.exercicio_4;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.*;
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
        String directoryPath = JOptionPane.showInputDialog(app, "Enter the directory or file path to change permissions:");
        Path path = Paths.get(directoryPath);
        Path absolutePath = path.toAbsolutePath();
        if (Files.exists(absolutePath)) {
            if (FileSystems.getDefault().supportedFileAttributeViews().contains("posix")) {
                Set<PosixFilePermission> posixFilePermissionSet = new HashSet<>(Arrays.asList(PosixFilePermission.OWNER_READ, PosixFilePermission.GROUP_READ, PosixFilePermission.OTHERS_READ));
                Files.setPosixFilePermissions(absolutePath, posixFilePermissionSet);
            } else {
                absolutePath.toFile().setReadable(true);
                absolutePath.toFile().setWritable(false);
                absolutePath.toFile().setExecutable(false);
            }
            JOptionPane.showMessageDialog(app, String.format("The file or directory '%s' has read-only permission now!", absolutePath));
        } else {
            JOptionPane.showMessageDialog(app, String.format("The file or directory '%s' does not exists!", absolutePath));
        }
        System.exit(0);
    }
}
