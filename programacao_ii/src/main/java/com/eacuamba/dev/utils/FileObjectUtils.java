package com.eacuamba.dev.utils;

import java.io.*;
import java.nio.file.Path;

public class FileObjectUtils {
    public static void writeObject(Path absolutePath, Object object) throws IOException {
        File file = absolutePath.toFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(object);
    }

    public static Object readObjects(Path absolutePath) throws IOException, ClassNotFoundException {
        File file = absolutePath.toFile();
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream= new ObjectInputStream(fileInputStream);
        Object o = objectInputStream.readObject();
        return o;
    }
}
