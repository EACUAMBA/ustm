package puzzle;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    Puzzle.Position getPosition(Puzzle.Position position) {
        switch (this) {
            case UP -> {
                return new Puzzle.Position(position.x - 1, position.y);
            }
            case DOWN -> {
                return new Puzzle.Position(position.x + 1, position.y);
            }
            case LEFT -> {
                return new Puzzle.Position(position.x, position.y - 1);
            }
            case RIGHT -> {
                return new Puzzle.Position(position.x, position.y + 1);
            }
            default -> {
                return position;
            }
        }
    }
}
