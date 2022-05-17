package com.eacuamba.dev.ii_semestre.programacao_i.plataforma_de_gestao_de_propriedades;
import com.eacuamba.dev.ii_semestre.programacao_i.plataforma_de_gestao_de_propriedades.command_line_interface.CLI;
import com.eacuamba.dev.ii_semestre.programacao_i.plataforma_de_gestao_de_propriedades.config.ApplicationConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationConfig.setDefaults();
       ApplicationConfig.loadData(); // Pre carrega alguns dados para questão de testes.
        CLI.start();
    }
}
