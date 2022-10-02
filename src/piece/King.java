package piece;

import utils.Coordinates;
import utils.Parser;

public class King extends Piece {
    boolean isCheckmate;
    boolean isMoved;

    public King(boolean white) {
        super(white, "♔");
        this.isCheckmate = false;
        this.isMoved = false;
    }

    public boolean isMoved() {
        return isMoved;
    }

    @Override
    public boolean canMoveTo(String move) {
        return kingMovement(move);
    }

    public boolean kingMovement(String move) {
        Coordinates[] arr = Parser.parseInput(move);
        int absoluteX = Math.abs(arr[1].getX() - arr[0].getX());
        int absoluteY = Math.abs(arr[1].getY() - arr[0].getY());
        if (absoluteX <= 1 && absoluteY <= 1) {
            return absoluteX != 0 || absoluteY != 0;
        }
        return false;
    }
}
