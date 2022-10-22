package piece;

import utils.Coordinates;


public class Bishop extends Piece {

    public Bishop(boolean white) {
        super(white,PieceName.BISHOP);
    }

    @Override
    public boolean canMoveTo(Coordinates coordinatesPiece, Coordinates coordinatesNextSpot) {
        return isMovingDiagonal(coordinatesPiece, coordinatesNextSpot);
    }

    public boolean isMovingDiagonal(Coordinates coordinatesPiece, Coordinates coordinatesNextSpot) {
        int xTotal = Math.abs(coordinatesNextSpot.getX() - coordinatesPiece.getX());
        int yTotal = Math.abs(coordinatesNextSpot.getY() - coordinatesPiece.getY());
        if (xTotal == yTotal) {
            if (coordinatesNextSpot.getX() < coordinatesPiece.getX()) {
                return true;
            } else if (coordinatesNextSpot.getX() > coordinatesPiece.getX()) {
                return true;
            }

            if (coordinatesNextSpot.getY() < coordinatesPiece.getY()) {
                return true;
            } else return coordinatesNextSpot.getY() > coordinatesPiece.getY();
        }
        return false;
    }
}
