
import java.util.HashMap;

public class Board {
    private final Piece[][] board;
    public HashMap<Character, Integer> map = new HashMap<>();
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String MAGENTA = "\033[0;35m";

    Board() {
        this.board = new Piece[8][8];
        setHashMap();
    }


    public Piece[][] getBoard() {
        return board;
    }


    public void setHashMap() {
        map.put('a', 7);
        map.put('b', 6);
        map.put('c', 5);
        map.put('d', 4);
        map.put('e', 3);
        map.put('f', 2);
        map.put('g', 1);
        map.put('h', 0);
    }

    public HashMap<Character, Integer> getMap() {
        return map;
    }

    public void printBoard() {
        int count;
        for (int i = 0; i < 8; i++) {
            count = i;
            for (int j = 0; j < 8; j++) {
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

    public Piece getPiece(int x, int y) {
        return board[x][y];
    }


    public void placePiece(Piece piece, int x, int y) {
        board[x][y] = piece;
    }

    public void replacePiece(String move) {
        int startX = Character.getNumericValue(move.charAt(1)) - 1;
        int startY = map.get(move.charAt(0));
        int x = Character.getNumericValue(move.charAt(4)) - 1;
        int y = map.get(move.charAt(3));
        board[x][y] = getPiece(startX, startY);
        deletePiece(startX, startY);
    }

    public void deletePiece(int x, int y) {
        board[x][y] = null;
    }


    public boolean entranceOfBoard(String move) {
        int x = Character.getNumericValue(move.charAt(4)) - 1;
        int y = map.get(move.charAt(3));
        return x < 8 && x >= 0 && y < 8 && y >= 0;
    }

    public boolean isEmptyPosition(String move) {
        int x = Character.getNumericValue(move.charAt(4)) - 1;
        int y = map.get(move.charAt(3));
        return board[x][y] == null;
    }

    public boolean isYourColor(String move) {
        int startX = Character.getNumericValue(move.charAt(1)) - 1;
        int startY = map.get(move.charAt(0));
        int nextX = Character.getNumericValue(move.charAt(4)) - 1;
        int nextY = map.get(move.charAt(3));
        return getPiece(startX, startY).isWhite() == getPiece(nextX, nextY).isWhite();
    }
}
