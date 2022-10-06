package piece;

import utils.Coordinates;
import utils.Parser;

public class King extends Piece {
    boolean isCheckmate;
    boolean isMoved;

    public King(boolean white) {
        super(white, "♔");
        this.isCheckmate = false;
        this.isMoved = false;
    }

    public boolean isMoved() {
        return isMoved;
    }

    @Override
    public boolean canMoveTo(Coordinates coordinatesPiece, Coordinates coordinatesNextSpot) {
        return kingMovement(coordinatesPiece,coordinatesNextSpot);
    }

    public boolean kingMovement(Coordinates coordinatesPiece, Coordinates coordinatesNextSpot) {
        int absoluteX = Math.abs(coordinatesNextSpot.getX() - coordinatesPiece.getX());
        int absoluteY = Math.abs(coordinatesNextSpot.getY() - coordinatesPiece.getY());
        if (absoluteX <= 1 && absoluteY <= 1) {
            return absoluteX != 0 || absoluteY != 0;
        }
        return false;
    }
}
