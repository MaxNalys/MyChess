package piece;

import utils.Coordinates;


public class Bishop extends Piece {

    public Bishop(boolean white) {
        super(white, "♗");
    }

    @Override
    public boolean canMoveTo(Coordinates coordinatesPiece, Coordinates coordinatesNextSpot) {
        return isMovingDiagonal(coordinatesPiece, coordinatesNextSpot);
    }

    public boolean isMovingDiagonal(Coordinates coordinatesPiece, Coordinates coordinatesNextSpot) {
        int xTotal = Math.abs(coordinatesNextSpot.getX() - coordinatesPiece.getX());
        int yTotal = Math.abs(coordinatesNextSpot.getY() - coordinatesPiece.getY());
        if (xTotal == yTotal) {
            if (coordinatesNextSpot.getX() < coordinatesPiece.getX() || coordinatesNextSpot.getX() > coordinatesPiece.getX()) {
                return true;
            } else
                return coordinatesNextSpot.getY() < coordinatesPiece.getY() || coordinatesNextSpot.getY() > coordinatesPiece.getY();
        }
        return false;
    }
}
