package com.eacuamba.puzzle;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Boolean.FALSE;
import static java.util.Objects.nonNull;

public class Puzzle {
    private final static SecureRandom secureRandom = new SecureRandom();
    private final Integer[][] matrix;
    private final int size;
    private final StringBufferHelper stringBufferHelper;

    public Puzzle(int lines, int columns, StringBufferHelper stringBufferHelper) {
        this.matrix = new Integer[lines][columns];
        this.size = (lines * columns);
        this.stringBufferHelper = stringBufferHelper;
        this.fillMatrix();
    }

    private void fillMatrix() {
        for (int line = 0; line < matrix.length; line++) {
            for (int column = 0; column < matrix[line].length; column++) {
                if (column == 0 && line == 0) {
                    continue;
                }
                int randomNumber;
                do {
                    randomNumber = this.getRandomNumber();
                } while (this.alreadyExistsInMatrix(randomNumber));
                matrix[line][column] = randomNumber;
            }
        }
    }

    private int getRandomNumber() {
        int i = secureRandom.nextInt(this.size);
        return i + 1;
    }

    private boolean alreadyExistsInMatrix(Integer value) {
        boolean exists = false;
        for (Integer[] integers : matrix) {
            for (Integer integer : integers) {
                if (Objects.equals(integer, value)) {
                    exists = true;
                    break;
                }
            }
        }
        return exists;
    }

    public void printState() {
        for (Integer[] integers : matrix) {
            for (Integer integer : integers) {
                stringBufferHelper.addTextToBufferFormatted(" %s ", Objects.toString(integer, " "));
            }
            stringBufferHelper.addTextToBuffer();
        }
    }

    public void changePosition(Position origin, Position target) {
        stringBufferHelper.addTextToBufferFormatted("Change position from %s to %s.%n", origin, target);
        stringBufferHelper.addTextToBuffer("Current state:");
        this.printState();
        Integer originValue = this.matrix[origin.x][origin.y];
        Integer targetValue = this.matrix[target.x][target.y];

        this.matrix[origin.x][origin.y] = targetValue;
        this.matrix[target.x][target.y] = originValue;
    }

    private PositionDirection checkDirection(Position position, Direction direction) {
        try {
            PositionDirection positionDirection = new PositionDirection();
            positionDirection.origin = position;
            positionDirection.direction = direction;

            positionDirection.target = direction.turnPosition(position);
            positionDirection.target.canAccess(this.matrix);

            return positionDirection;
        } catch (Exception e) {
            stringBufferHelper.addTextToBufferFormatted("Can't go %s!%n", direction.name());
            return null;
        }
    }

    public List<PositionDirection> getAvailableChanges(Position position) {
        stringBufferHelper.addTextToBuffer("\nChecking available directions");
        final List<PositionDirection> positionDirectionList = new ArrayList<>();
        //UP
        positionDirectionList.add(this.checkDirection(position, Direction.UP));

        //DOWN
        positionDirectionList.add(this.checkDirection(position, Direction.DOWN));

        //LEFT
        positionDirectionList.add(this.checkDirection(position, Direction.LEFT));

        //RIGHT
        positionDirectionList.add(this.checkDirection(position, Direction.RIGHT));

        //Sanitize
        positionDirectionList.removeIf(Objects::isNull);

        stringBufferHelper.addTextToBufferFormatted("Possible directions:%n");
        positionDirectionList.forEach(positionDirection -> stringBufferHelper.addTextToBuffer(positionDirection.toString()));
        stringBufferHelper.addTextToBuffer();
        return positionDirectionList;
    }

    public boolean isMatrixUnordered() {
        if (nonNull(this.matrix[0][0])) {
            return true;
        }

        int lineLength = matrix.length;
        for (int line = 0; line < lineLength; line++) {

            int columnLength = matrix[line].length;
            for (int column = 0; column < columnLength; column++) {
                if (line == 0 && column == 0) {
                    continue;
                }


                if (column < (columnLength - 1) && matrix[line][column] > matrix[line][column + 1]) {
                    return true;
                }

                if (column == (columnLength - 1) && (line + 1) < lineLength && matrix[line][column] > matrix[line + 1][0]) {
                    return true;
                }
            }
        }

        return false;
    }

    public static class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void canAccess(Integer[][] matrix) {
            boolean canAccess = true;
            if (this.x >= matrix.length || this.x < 0) {
                canAccess = false;
            } else {
                if (this.y >= matrix[this.x].length || this.y < 0) {
                    canAccess = false;
                }
            }

            if (FALSE.equals(canAccess)) {
                throw new RuntimeException("You can't access this place " + this.toString() + "!");
            }
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    public static class PositionDirection {
        public Position target;
        public Position origin;
        Direction direction;

        @Override
        public String toString() {
            return String.format("from %s to %s, direction: %s", origin, target, direction.name());
        }
    }
}
