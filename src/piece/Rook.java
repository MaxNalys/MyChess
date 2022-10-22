package piece;

import utils.Coordinates;

public class Rook extends Piece {

    private boolean hasMoved;

    public Rook(boolean white) {
        super(white,PieceName.ROOK);
        this.hasMoved = false;

    }
    @Override
    public boolean canMoveTo(Coordinates coordinatesPiece, Coordinates coordinatesNextSpot) {
        return isMovingStraight(coordinatesPiece, coordinatesNextSpot);
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

}
