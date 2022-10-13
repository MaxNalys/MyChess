package piece;

import utils.Coordinates;

public class Rook extends Piece {
    // TODO i would name it hasMoved, it makes more sense
    private boolean isMoved;

    public Rook(boolean white) {
        super(white, "♖");
        this.isMoved = false;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    public boolean isMoved() {
        return isMoved;
    }

    @Override
    public boolean canMoveTo(Coordinates coordinatesPiece, Coordinates coordinatesNextSpot) {
        return isMovingStraight(coordinatesPiece, coordinatesNextSpot);
    }

    // TODO why does it differ from similar method in Queen class?
    public boolean isMovingStraight(Coordinates coordinatesPiece, Coordinates coordinatesNextSpot) {
        if (coordinatesPiece.getX() == coordinatesNextSpot.getX()) {
            return coordinatesPiece.getX() > coordinatesNextSpot.getX() || coordinatesNextSpot.getY() > coordinatesPiece.getX();
        }
        if (coordinatesPiece.getY() == coordinatesNextSpot.getY()) {
            return coordinatesPiece.getX() > coordinatesNextSpot.getX() || coordinatesNextSpot.getX() > coordinatesPiece.getX();
        }
        return false;
    }
}
