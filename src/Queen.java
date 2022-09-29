public class Queen extends Piece {
    String name;

    Queen(Board board, boolean white) {
        super(board, white);
        this.name = "♕";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void moveTo(String move) {
        if (queenMovement(move) && canMoveGenerics(move)) {
            board.replacePiece(move);
        }
    }

    public boolean queenMovement(String move) {
        return isMovingDiagonal(move) || isMovingStraight(move);
    }
}
