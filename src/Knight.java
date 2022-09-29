import java.util.SplittableRandom;

public class Knight extends Piece {
    String name;

    Knight(Board board, boolean white) {
        super(board, white);
        this.name = "♘";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void moveTo(String move) {
        if (knightMovement(move) && canMoveGenerics(move)) {
            board.replacePiece(move);
        }
    }

    public boolean knightMovement(String move) {
        int currX = Character.getNumericValue(move.charAt(1)) - 1;
        int currY = board.map.get(move.charAt(0));
        int x = Character.getNumericValue(move.charAt(4)) - 1;
        int y = board.map.get(move.charAt(3));
        if (Math.abs(currX - x) == 2 && Math.abs(currY - y) == 1) {
            return true;
        }
        return Math.abs(currX - x) == 1 && Math.abs(currY - y) == 2;
    }
}
