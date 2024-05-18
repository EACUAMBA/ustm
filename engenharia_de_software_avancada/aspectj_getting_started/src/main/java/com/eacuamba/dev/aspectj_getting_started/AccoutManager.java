package com.eacuamba.dev.aspectj_getting_started;


import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class AccoutManager {
    private static AccoutManager accoutManager;
    private static final List<Account> accountList = new ArrayList<Account>();

    public static AccoutManager getInstance() {
        if(isNull(accoutManager)){
            return accoutManager = new AccoutManager();
        }
        return accoutManager;
    }

    public void withdraw(){
//        System.out.println("Digite a conta de destino");
//        String accountTargetNumber = ConsoleUtilities.getStringFromScanner();
//
//        System.out.println("Digite o valor (tens x na conta):");
//        String accountTargetNumber = ConsoleUtilities.getStringFromScanner();



    }

    public void transfer(){

    }

    public void viewBalance(){

    }

}
