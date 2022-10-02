package piece;

import utils.Coordinates;
import utils.Parser;

public class Rook extends Piece {
    private boolean isMoved;

    public Rook(boolean white) {
        super(white, "♖");
        this.isMoved = false;
    }

    public boolean isMoved() {
        return isMoved;
    }

    @Override
    public boolean canMoveTo(String move) {
        return isMovingStraight(move);
    }

    public boolean isMovingStraight(String move) {
        Coordinates[] arr = Parser.parseInput(move);
        if (arr[0].getX() == arr[1].getX()) {
            return arr[0].getX() > arr[1].getX() || arr[1].getY() > arr[0].getX();
        }
        if (arr[0].getY() == arr[1].getY()) {
            return arr[0].getX() > arr[1].getX() || arr[1].getX() > arr[0].getX();
        }
        return false;
    }
}
