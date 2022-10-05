package piece;

import utils.Parser;

public abstract class Piece {
    private boolean isWhite;

    // TODO should be private final, right?
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

    // TODO you should pass Coordinate object since it will be already parsed in ChessGame
    // you will avoid parsing in the pieces this way
    public abstract boolean canMoveTo(String move);

}

