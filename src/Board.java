
import piece.Piece;
import utils.Coordinates;
import utils.Parser;

public class Board {
    private final Piece[][] board;
    static final int PIECE_BOARD_SIZE = 8;

    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String MAGENTA = "\033[0;35m";

    Board() {
        this.board = new Piece[PIECE_BOARD_SIZE][PIECE_BOARD_SIZE];
    }

    public Piece[][] getBoard() {
        return board;
    }

    public void printBoard() {
        int count;
        for (int i = 0; i < PIECE_BOARD_SIZE; i++) {
            count = i;
            for (int j = 0; j < PIECE_BOARD_SIZE; j++) {
                if (j == 0) {
                    System.out.print(WHITE_BOLD_BRIGHT + (++count));
                }
                if (board[i][j] == null) {
                    System.out.print(MAGENTA + "\t☐");
                } else {
                    if (board[i][j].isWhite()) {
                        System.out.print("\t" + WHITE_BOLD_BRIGHT + board[i][j].getName());
                    } else {
                        System.out.print("\t" + BLACK_BOLD_BRIGHT + board[i][j].getName());
                    }
                }
            }
            System.out.println("");
        }
        System.out.print(WHITE_BOLD_BRIGHT + "    h   g   f   e   d   c   b   a");
    }

    public Piece getPieceFromStartPosition(String move) {
        Coordinates[] coordinates = Parser.parseInput(move);
        return board[coordinates[0].getX()][coordinates[0].getY()];
    }
    public Piece getPieceFromNextPosition(String move) {
        Coordinates[] coordinates = Parser.parseInput(move);
        return board[coordinates[1].getX()][coordinates[1].getY()];
    }
    public void setPieceOnTheNextSpot(String move){
        Coordinates[] coordinates = Parser.parseInput(move);
        board[coordinates[1].getX()][coordinates[1].getY()]=getPieceFromStartPosition(move);
    }

}
