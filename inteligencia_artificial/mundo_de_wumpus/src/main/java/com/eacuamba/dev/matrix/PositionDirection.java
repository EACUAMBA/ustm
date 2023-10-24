package com.eacuamba.dev.matrix;

public class PositionDirection {
    public Position target;
    public Position origin;
    public Direction direction;

    @Override
    public String toString() {
        return String.format("from %s to %s, direction: %s", origin, target, direction.name());
    }
}