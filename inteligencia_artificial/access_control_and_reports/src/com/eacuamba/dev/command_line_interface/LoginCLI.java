package com.eacuamba.dev.command_line_interface;

import com.eacuamba.dev.command_line_interface.utils.ConsoleBase;
import com.eacuamba.dev.command_line_interface.utils.ConsoleUtils;
import com.eacuamba.dev.config.ApplicationConfig;
import com.eacuamba.dev.domain.model.User;
import com.eacuamba.dev.domain.repository.UserRepository;

import javax.swing.*;
import java.util.Arrays;
import java.util.Optional;

public class LoginCLI extends ConsoleBase {

    public static void signIn(){
        System.out.println("Bem vindo ao sistema de Gestão de Propriedades");
        System.out.println("Trabalho Prático de Programação I");
        System.out.println();
        boolean isUnauthorized = true;
        do {
            System.out.println("Autentique-se");
            String username = getTextoFromConsole("Insira o teu nome de utilizador");
            String password = getTextoFromConsole("Insira a tua senha");

            Optional<User> userOptional = UserRepository.getUserByUsername(username);
            if(userOptional.isPresent()){
                User user = userOptional.get();
                AccessService.addAccess("Tentou fazer autenticação no sistema sem sucesso!", user);
                if(user.getPassword().equals(password)){
                    isUnauthorized = false;
                    ApplicationConfig.authenticatedUser = user;
                }
            }

            if(isUnauthorized){
                System.out.println("O teu nome de utilizador ou senha estão errados!");
            }
        }while(isUnauthorized);

        AccessService.addAccess("Fez autenticação no sistema com sucesso!");
        CLI.start();
    }
}
