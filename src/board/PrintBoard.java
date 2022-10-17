package board;

import piece.PieceName;

import java.util.HashMap;

public class PrintBoard {
    public static HashMap<Enum, String> pieceIdentifiers;
    private static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE
    private static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    private static final String MAGENTA = "\033[0;35m";


    static {
        pieceIdentifiers = new HashMap<>();
        pieceIdentifiers.put(PieceName.BISHOP, "♗");
        pieceIdentifiers.put(PieceName.KING, "♔");
        pieceIdentifiers.put(PieceName.PAWN, "♙");
        pieceIdentifiers.put(PieceName.KNIGHT, "♘");
        pieceIdentifiers.put(PieceName.QUEEN, "♕");
        pieceIdentifiers.put(PieceName.ROOK, "♖");
    }

    public static void printBoard(Board board) {
        int count;
        for (int i = 0; i < Board.PIECE_BOARD_SIZE; i++) {
            count = i;
            for (int j = 0; j < Board.PIECE_BOARD_SIZE; j++) {
                if (j == 0) {
                    System.out.print(WHITE_BOLD_BRIGHT + (++count));
                }
                if (board.getBoard()[i][j] == null) {
                    System.out.print(MAGENTA + "\t☐");
                } else {
                    if (board.getBoard()[i][j].isWhite()) {
                        System.out.print("\t" + WHITE_BOLD_BRIGHT + pieceIdentifiers.get(board.getBoard()[i][j].getIdentifiers()));
                    } else {
                        System.out.print("\t" + BLACK_BOLD_BRIGHT + pieceIdentifiers.get(board.getBoard()[i][j].getIdentifiers()));
                    }
                }
            }
            System.out.println("");
        }
        System.out.print(WHITE_BOLD_BRIGHT + "    h   g   f   e   d   c   b   a");
    }
}
