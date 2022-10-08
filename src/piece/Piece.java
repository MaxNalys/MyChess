package piece;

import utils.Coordinates;

public abstract class Piece {

    private final boolean isWhite;
    private final String name;

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

    public abstract boolean canMoveTo(Coordinates coordinatesPiece,Coordinates coordinatesNextSpot);


}

