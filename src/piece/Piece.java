package piece;

import utils.Coordinates;

public abstract class Piece {

    private final boolean isWhite;
    private PieceName identifiers;

    Piece(boolean isWhite, PieceName identifiers) {
        this.isWhite = isWhite;
        this.identifiers = identifiers;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public abstract boolean canMoveTo(Coordinates coordinatesPiece, Coordinates coordinatesNextSpot);

    public PieceName getIdentifiers() {
        return identifiers;
    }

}

