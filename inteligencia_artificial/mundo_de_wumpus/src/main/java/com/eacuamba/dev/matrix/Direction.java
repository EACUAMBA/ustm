package com.eacuamba.dev.matrix;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    Position turnPosition(Position position) {
        switch (this) {
            case UP -> {
                return new Position(position.x - 1, position.y);
            }
            case DOWN -> {
                return new Position(position.x + 1, position.y);
            }
            case LEFT -> {
                return new Position(position.x, position.y - 1);
            }
            case RIGHT -> {
                return new Position(position.x, position.y + 1);
            }
            default -> {
                return position;
            }
        }
    }
}
