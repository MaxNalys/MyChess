public class Rook extends Piece {
    String name;

    Rook(Board board, boolean white) {
        super(board, white);
        this.name = "♖";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void moveTo(String move) {
        if (rookMovement(move)) {
            board.replacePiece(move);
        }

    }

    public boolean rookMovement(String move) {
        return isMovingStraight(move) && canMoveGenerics(move);
    }
}
