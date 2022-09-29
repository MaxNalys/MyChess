public abstract class Piece {
    private boolean isWhite;

    protected Board board;
    protected boolean hasMoved;


    Piece(Board board, boolean isWhite) {
        this.board = board;
        this.isWhite = isWhite;
        this.hasMoved = false;

    }


    public Board getBoard() {
        return board;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }


    public abstract String getName();


    public boolean isWhite() {
        return isWhite;
    }

    public abstract void moveTo(String move);

    public boolean canMoveGenerics(String move) {
        if (board.entranceOfBoard(move)) {
            if (board.isEmptyPosition(move)) {
                return true;
            }
            return !board.isYourColor(move);
        }
        return false;
    }

    public boolean isMovingDiagonal(String move) {
        int xStart = 0;
        int yStart = 0;
        int xFinish = 1;
        int yFinish = 1;
        //e2-e4

        int currX = Character.getNumericValue(move.charAt(1)) - 1;
        int currY = board.map.get(move.charAt(0));
        int x = Character.getNumericValue(move.charAt(4)) - 1;
        int y = board.map.get(move.charAt(3));

        int xTotal = Math.abs(x - currX);
        int yTotal = Math.abs(y - currY);
        if (xTotal == yTotal) {
            if (x < currX) {
                xStart = x;
                xFinish = currX;
            } else if (x > currX) {
                xStart = currX;
                xFinish = x;
            } else {
                return false;
            }
            if (y < currY) {
                yStart = y;
                yFinish = currY;
            } else if (y > currY) {
                yStart = currY;
                yFinish = y;
            } else {
                return false;
            }
            xStart++;
            yStart++;
            for (; xStart < xFinish; xStart++, yStart++) {
                if (board.getPiece(xStart, yStart) != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isMovingStraight(String move) {
        int currX = Character.getNumericValue(move.charAt(1)) - 1;
        int currY = board.map.get(move.charAt(0));
        int x = Character.getNumericValue(move.charAt(4)) - 1;
        int y = board.map.get(move.charAt(3));
        int smallerVal;
        int largerVal;
        if (currX == x) {
            if (currY > y) {
                smallerVal = y;
                largerVal = currY;
            } else if (y > currY) {
                smallerVal = currY;
                largerVal = y;
            } else {
                return false;
            }
            smallerVal++;
            for (; smallerVal < largerVal; smallerVal++) {
                if (board.getPiece(currX, smallerVal) != null) {
                    return false;
                }
            }
            return true;
        }
        if (currY == y) {
            if (currX > x) {
                smallerVal = x;
                largerVal = currX;
            } else if (x > currX) {
                smallerVal = currX;
                largerVal = x;
            } else {
                return false;
            }
            smallerVal++;
            for (; smallerVal < largerVal; smallerVal++) {
                if (board.getPiece(smallerVal, currY) != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}

