package com.eacuamba.dev.implementing_aspects;

import com.eacuamba.dev.implementing_aspects.services.ViewBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ImplementingAspectsApplication implements CommandLineRunner {
    @Autowired
    private ViewBalanceService viewBalanceService;

    public static void main(String[] args) {
        SpringApplication.run(ImplementingAspectsApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        showMenu();
    }

    public void showMenu() {
        StringBuilder menuStringBuilder = new StringBuilder();
        menuStringBuilder.append("Menu:").append("\n");
        menuStringBuilder.append("1. Transferir").append("\n");
        menuStringBuilder.append("2. Levantar").append("\n");
        menuStringBuilder.append("3. Ver saldo").append("\n");
        menuStringBuilder.append("0. Sair").append("\n");

        Integer selectedOption = null;

        while (selectedOption == null) {

            System.out.println(menuStringBuilder);
            selectedOption = ConsoleUtilities.getIntegerOrMinusOneFromScanner();

            switch (selectedOption) {
                case 0: {
                    break;
                }
                case 1: {

                    showMenu();
                    break;
                }
                case 2: {

                    showMenu();
                    break;
                }
                case 3: {
                    this.viewBalanceService.process();
                    showMenu();
                    break;
                }
                default: {
                    System.out.println("Invalid option");
                    showMenu();
                }
            }
        }
    }
}
