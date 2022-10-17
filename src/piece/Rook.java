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
            return coordinatesPiece.getY() > coordinatesNextSpot.getY() || coordinatesNextSpot.getY() > coordinatesPiece.getY();
        }
        if (coordinatesPiece.getY() == coordinatesNextSpot.getY()) {
            return coordinatesPiece.getX() > coordinatesNextSpot.getX() || coordinatesNextSpot.getX() > coordinatesPiece.getX();
        }
        return false;
    }

}
