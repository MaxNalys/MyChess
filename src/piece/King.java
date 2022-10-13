package piece;

import utils.Coordinates;

public class King extends Piece {
    private int x;
    private int y;
    private boolean isMoved;

    public King(boolean white, int x, int y) {
        super(white, "♔");
        this.x = x;
        this.y = y;
        this.isMoved = false;
    }

    // TODO dont add methods you don't use
    public boolean isMoved() {
        return isMoved;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    @Override
    public boolean canMoveTo(Coordinates coordinatesPiece, Coordinates coordinatesNextSpot) {
        return kingMovement(coordinatesPiece, coordinatesNextSpot);
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
