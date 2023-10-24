package com.eacuamba.dev.matrix;

import java.util.Objects;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Integer canAccess(Integer[][] matrix) {
        boolean canAccess = true;
        if (this.x >= matrix.length || this.x < 0) {
            canAccess = false;
        } else {
            if (this.y >= matrix[this.x].length || this.y < 0) {
                canAccess = false;
            }
        }

        if (Boolean.FALSE.equals(canAccess)) {
            throw new RuntimeException("You can't access this place " + this + "!");
        }else {
            return matrix[x][y];
        }
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(x, position.x) && Objects.equals(y, position.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}