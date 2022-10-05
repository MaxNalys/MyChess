package piece;

import utils.Coordinates;
import utils.Parser;

public class Pawn extends Piece {

    public static boolean isMoved;

    public Pawn(boolean white) {
        super(white, "♙");
        isMoved = false;
    }

    public static boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    @Override
    public boolean canMoveTo(String move) {
        return pawnMovement(move);
    }



    public boolean pawnMovement(String move) {
        Coordinates[] arr = Parser.parseInput(move);
        int one_step;
        int two_step;
        if (isWhite()) {
            one_step = 1;
            two_step = 2;
        } else {
            one_step = -1;
            two_step = -2;
        }

        if (arr[1].getX() - arr[0].getX() == one_step) {
            setMoved(true);
            if (arr[1].getY() == arr[0].getY()) {
                return true;
            }
            if (Math.abs(arr[0].getY() - arr[1].getY()) == 1) {
                return true;
            }
        } else if (!isMoved()) {
            setMoved(true);
            if (arr[1].getX() - arr[0].getX() == two_step) {
                return arr[1].getY() == arr[0].getY();
            }
        }
        return false;
    }
}