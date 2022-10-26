package piece;

import utils.Coordinates;

public class Queen extends Piece {
    public Queen(boolean white) {
        super(white, PieceName.QUEEN,false);
    }

    @Override
    public boolean canMoveTo(Coordinates coordinatesPiece, Coordinates coordinatesNextSpot) {
        return isMovingDiagonal(coordinatesPiece, coordinatesNextSpot) || isMovingStraight(coordinatesPiece, coordinatesNextSpot);
    }

    public boolean isMovingStraight(Coordinates coordinatesPiece, Coordinates coordinatesNextSpot) {
        if (coordinatesPiece.getX() == coordinatesNextSpot.getX()) {
            if (coordinatesPiece.getY() > coordinatesNextSpot.getY()) {
                return true;
            } else if (coordinatesNextSpot.getY() > coordinatesPiece.getY()) {
                return true;
            }
        }

        if (coordinatesPiece.getY() == coordinatesNextSpot.getY()) {
            if (coordinatesPiece.getX() > coordinatesNextSpot.getX()) {
                return true;
            } else return coordinatesNextSpot.getX() > coordinatesPiece.getX();
        }
        return false;
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
            } else if( coordinatesNextSpot.getY() > coordinatesPiece.getY()){
                return true;
            }
        }
        return false;
    }
}

