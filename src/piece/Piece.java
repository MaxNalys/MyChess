package piece;

import utils.Coordinates;

public abstract class Piece {

    private final boolean isWhite;
    private PieceName identifiers;
    private boolean hasMoved;

    Piece(boolean isWhite, PieceName identifiers,boolean hasMoved) {
        this.isWhite = isWhite;
        this.identifiers = identifiers;
        this.hasMoved = hasMoved;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public abstract boolean canMoveTo(Coordinates coordinatesPiece, Coordinates coordinatesNextSpot);

    public PieceName getIdentifiers() {
        return identifiers;
    }

}

