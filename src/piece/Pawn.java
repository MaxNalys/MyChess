package piece;

import utils.Coordinates;

import java.net.PortUnreachableException;

public class Pawn extends Piece {

   public boolean isMoved;

    public Pawn(boolean white) {
        super(white, "♙");
        isMoved = false;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    @Override
    public boolean canMoveTo(Coordinates coordinatesPiece, Coordinates coordinatesNextSpot) {
        return pawnMovement(coordinatesPiece, coordinatesNextSpot);
    }


    public boolean pawnMovement(Coordinates coordinatesPiece, Coordinates coordinatesNextSpot) {
        int one_step;
        int two_step;
        if (isWhite()) {
            one_step = 1;
            two_step = 2;
        } else {
            one_step = -1;
            two_step = -2;
        }

        if (coordinatesNextSpot.getX() - coordinatesPiece.getX() == one_step) {
            setMoved(true);
            if (coordinatesNextSpot.getY() == coordinatesPiece.getY()) {
                return true;
            }
            if (Math.abs(coordinatesPiece.getY() - coordinatesNextSpot.getY()) == 1) {
                return true;
            }
        } else if (!isMoved()) {
            setMoved(true);
            if (coordinatesNextSpot.getX() - coordinatesPiece.getX() == two_step) {
                return coordinatesNextSpot.getY() == coordinatesPiece.getY();
            }
        }
        return false;
    }
}