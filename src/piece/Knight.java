package piece;

import utils.Coordinates;

public class Knight extends Piece {

    public Knight(boolean white) {
        super(white, PieceName.KNIGHT,false);
    }

    @Override
    public boolean canMoveTo(Coordinates coordinatesPiece, Coordinates coordinatesNextSpot) {
        return knightMovement(coordinatesPiece, coordinatesNextSpot);
    }

    public boolean knightMovement(Coordinates coordinatesPiece, Coordinates coordinatesNextSpot) {
        if (Math.abs(coordinatesPiece.getX() - coordinatesNextSpot.getX()) == 2 && Math.abs(coordinatesPiece.getY() - coordinatesNextSpot.getY()) == 1)
            return true;
        return Math.abs(coordinatesPiece.getX() - coordinatesNextSpot.getX()) == 1 && Math.abs(coordinatesPiece.getY() - coordinatesNextSpot.getY()) == 2;
    }
}
