package piece;

import utils.Coordinates;
import utils.Parser;

public class Knight extends Piece {

    public Knight(boolean white) {
        super(white, "♘");
    }

    @Override
    public boolean canMoveTo(String move) {
        return knightMovement(move);
    }

    public boolean knightMovement(String move) {
        Coordinates[] arr = Parser.parseInput(move);
        if (Math.abs(arr[0].getX() - arr[1].getX()) == 2 && Math.abs(arr[0].getY() - arr[1].getY()) == 1)
            return true;
        return Math.abs(arr[0].getX() - arr[1].getX()) == 1 && Math.abs(arr[0].getY() - arr[1].getY()) == 2;
    }
}
