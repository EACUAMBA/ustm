package com.eacuamba.dev;

import com.eacuamba.dev.command_line_interface.CLI;
import com.eacuamba.dev.config.ApplicationConfig;

import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Args");
        Stream.of(args).forEach(System.out::println);

        ApplicationConfig.setDefaults();
        CLI.start();
    }
}
