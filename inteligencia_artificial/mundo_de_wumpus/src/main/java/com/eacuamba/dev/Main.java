package com.eacuamba.dev;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Wumpus!");
        final List<WarriorSensor> knowledgeBaseList = new ArrayList<>();
        SecureRandom secureRandom = new SecureRandom();

        int lines = 4;
        int columns = 4;
        MatrixElement[][] matrix = new MatrixElement[lines][columns];

        //fill the matrix with 3 pitfall and its adjacent breeze,
        //fill the left space with one Wumpu monster with ist smells
        //fill the left space with a gold spot.
        //fill the left spaces with the warrior in any position.






    }
}