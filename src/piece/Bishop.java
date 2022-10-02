package piece;

import utils.Coordinates;
import utils.Parser;

public class Bishop extends Piece {

    public Bishop(boolean white) {
        super(white, "♗");
    }

    @Override
    public boolean canMoveTo(String move) {
        return isMovingDiagonal(move);
    }

    public boolean isMovingDiagonal(String move) {
        Coordinates[] arr = Parser.parseInput(move);
        int xTotal = Math.abs(arr[1].getX() - arr[0].getX());
        int yTotal = Math.abs(arr[1].getY() - arr[0].getY());
        if (xTotal == yTotal) {
            if (arr[1].getX() < arr[0].getX() || arr[1].getX() > arr[0].getX()) {
                return true;
            } else return arr[1].getY() < arr[0].getY() || arr[1].getY() > arr[0].getY();
        }
        return false;
    }
}
