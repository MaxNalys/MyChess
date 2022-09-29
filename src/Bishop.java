public class Bishop extends Piece {
    String name;

    Bishop(Board board, boolean white) {
        super(board, white);
        this.name = "♗";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void moveTo(String move) {
        if (bishopMovement(move)) {
            board.replacePiece(move);
        }

    }

    public boolean bishopMovement(String move) {
        return isMovingDiagonal(move) && canMoveGenerics(move);
    }
}
