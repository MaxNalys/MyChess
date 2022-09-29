public class Pawn extends Piece {
    String name;

    Pawn(Board board, boolean white) {
        super(board, white);
        this.name = "♙";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void moveTo(String move) {
        if (pawnMovement(move)) {
            board.replacePiece(move);
        }
    }

    public boolean pawnMovement(String move) {
        int startX = Character.getNumericValue(move.charAt(1)) - 1;
        int startY = board.map.get(move.charAt(0));
        int x = Character.getNumericValue(move.charAt(4)) - 1;
        int y = board.map.get(move.charAt(3));
        int one_step;
        int two_step;
        if (isWhite()) {
            one_step = 1;
            two_step = 2;
        } else {
            one_step = -1;
            two_step = -2;
        }
        if (canMoveGenerics(move)) {
            if (x - startX == one_step) {
                if (y == startY)
                    return true;
                if (Math.abs(startY - y) == 1)
                    return true;
            } else if (!isHasMoved()) {
                if (x - startX == two_step) {
                    hasMoved = true;
                    return y == startY;
                }
            }
        }
        return false;
    }




}
