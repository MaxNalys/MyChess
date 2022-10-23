package piece;

import utils.Coordinates;

public class King extends Piece {

    private boolean hasMoved;
    private boolean isCheck;

    public King(boolean white) {
        super(white,PieceName.KING);
        this.hasMoved = false;
        this.isCheck=false;
    }

    @Override
    public boolean canMoveTo(Coordinates coordinatesPiece, Coordinates coordinatesNextSpot) {
        return kingMovement(coordinatesPiece, coordinatesNextSpot);
    }

    public boolean isCheck() {
        return isCheck;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public void setCheck(boolean check) {
        isCheck = check;
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
