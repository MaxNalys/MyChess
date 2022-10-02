package piece;

import utils.Parser;

public abstract class Piece {
    private boolean isWhite;
    String name;

    Piece(boolean isWhite, String name) {
        this.isWhite = isWhite;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public abstract boolean canMoveTo(String move);
}

