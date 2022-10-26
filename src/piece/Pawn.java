package piece;

import utils.Coordinates;

public class Pawn extends Piece {


    public Pawn(boolean white) {
        super(white, PieceName.PAWN,false);
    }

    @Override
    public boolean canMoveTo(Coordinates coordinatesPiece, Coordinates coordinatesNextSpot) {
        return pawnMovement(coordinatesPiece, coordinatesNextSpot);
    }

    public boolean pawnMovement(Coordinates coordinatesPiece, Coordinates coordinatesNextSpot) {
        int one_step;
        int two_step;
        if (this.isWhite()) {
            one_step = 1;
            two_step = 2;
        } else {
            one_step = -1;
            two_step = -2;
        }

        if (coordinatesNextSpot.getX() - coordinatesPiece.getX() == one_step) {
            if (coordinatesNextSpot.getY() == coordinatesPiece.getY()) {
                return true;
            } else return Math.abs(coordinatesPiece.getY() - coordinatesNextSpot.getY()) == 1;
        } else if (coordinatesNextSpot.getX() - coordinatesPiece.getX() == two_step) {
            if (!hasMoved()) {
                return coordinatesNextSpot.getY() == coordinatesPiece.getY();
            }
        }
        return false;
    }
}