public class King extends Piece {
    String name;
    boolean isCheckmate;

    King(Board board, boolean white) {
        super(board, white);
        this.name = "♔";
        this.isCheckmate = false;
    }

    public void setCheckmate(boolean checkmate) {
        isCheckmate = checkmate;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void moveTo(String move) {
        if (kingMovement(move) && canMoveGenerics(move)) {
            board.replacePiece(move);
        }
    }

    public boolean kingMovement(String move) {
        int currX = Character.getNumericValue(move.charAt(1)) - 1;
        int currY = board.map.get(move.charAt(0));
        int x = Character.getNumericValue(move.charAt(4)) - 1;
        int y = board.map.get(move.charAt(3));
        int absoluteX = Math.abs(x-currX);
        int absoluteY = Math.abs(y-currY);
        if (absoluteX <= 1 && absoluteY <= 1) {
            if (absoluteX == 0 && absoluteY == 0){
                return false;
            }
            return true;
        }
        return false;
    }
}
