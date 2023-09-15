package com.eacuamba.puzzle;

import java.security.SecureRandom;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Runner {
    private final static SecureRandom secureRandom = new SecureRandom();
    private final StringBufferHelper stringBufferHelper;

    public Runner() {
        this.stringBufferHelper = new StringBufferHelper();
    }


    public StringBuffer run() {
        try {
            start();
        } catch (Exception e) {
            System.out.printf("Failed to resolve the matrix. Error:%s ", e.getMessage());
        }
        return stringBufferHelper.getStringBuffer();
    }

    public void start() {
        stringBufferHelper.addTextToBufferFormatted("Hello I'm thread %s.%n", Thread.currentThread().getName());
        stringBufferHelper.addTextToBuffer("Puzzle by Edilson Alexandre Cuamba");

        long startTime = System.currentTimeMillis();

        Puzzle puzzle = new Puzzle(3, 3, this.stringBufferHelper);

        stringBufferHelper.addTextToBuffer("Initial State:");
        puzzle.printState();
        stringBufferHelper.addTextToBuffer();

        Long iteration = 1L;
        Puzzle.Position position = new Puzzle.Position(0, 0);
        while (puzzle.isMatrixUnordered()) {

            stringBufferHelper.addTextToBufferFormatted("********************************%n");
            stringBufferHelper.addTextToBufferFormatted("Iteration %s", NumberFormat.getInstance(Locale.of("pt", "MZ")).format(iteration));
            stringBufferHelper.addTextToBuffer();

            List<Puzzle.PositionDirection> availableChanges = puzzle.getAvailableChanges(position);
            int nextInt = secureRandom.nextInt(availableChanges.size());

            Puzzle.PositionDirection positionDirection = availableChanges.get(nextInt);
            stringBufferHelper.addTextToBufferFormatted("Was chose: %s%n%n", positionDirection);

            puzzle.changePosition(position, positionDirection.target);
            position = positionDirection.target;

            stringBufferHelper.addTextToBuffer("Next state:");
            puzzle.printState();
            iteration++;

            stringBufferHelper.addTextToBufferFormatted("********************************%n%n");
        }

        long endTime = System.currentTimeMillis();

        stringBufferHelper.addTextToBufferFormatted("Took %d seconds.%n", (TimeUnit.MILLISECONDS.toSeconds(endTime - startTime)));
    }


}
