package com.eacuamba.dev;

import com.eacuamba.dev.command_line_interface.CLI;
import com.eacuamba.dev.config.ApplicationConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationConfig.setDefaults();
        CLI.loadData();
        CLI.start();
    }
}
