
import java.util.Scanner;

public class ChessGame {
    private Board board;
    private King blackKing;
    private King whiteKing;


    Scanner scanner = new Scanner(System.in);

    ChessGame() {
        this.board = new Board();
        this.whiteKing = new King(board, true);
        this.blackKing = new King(board, false);

        setUp();

    }

    public void setUp() {
        this.addBishop(true, 0, 2);
        this.addBishop(true, 0, 5);
        this.addKnight(true, 0, 1);
        this.addKnight(true, 0, 6);
        this.addRook(true, 0, 0);
        this.addRook(true, 0, 7);
        this.addQueen(true, 0, 4);
        this.addPawn(true, 1, 0);
        this.addPawn(true, 1, 1);
        this.addPawn(true, 1, 2);
        this.addPawn(true, 1, 3);
        this.addPawn(true, 1, 4);
        this.addPawn(true, 1, 5);
        this.addPawn(true, 1, 6);
        this.addPawn(true, 1, 7);
        this.addBishop(false, 7, 2);
        this.addBishop(false, 7, 5);
        this.addKnight(false, 7, 1);
        this.addKnight(false, 7, 6);
        this.addRook(false, 7, 0);
        this.addRook(false, 7, 7);
        this.addQueen(false, 7, 4);
        this.addPawn(false, 6, 0);
        this.addPawn(false, 6, 1);
        this.addPawn(false, 6, 2);
        this.addPawn(false, 6, 3);
        this.addPawn(false, 6, 4);
        this.addPawn(false, 6, 5);
        this.addPawn(false, 6, 6);
        this.addPawn(false, 6, 7);
        board.placePiece(whiteKing, 0, 3);
        board.placePiece(blackKing, 7, 3);
    }

    public void addPawn(boolean isWhite, int x, int y) {
        Pawn pawn = new Pawn(board, isWhite);
        board.placePiece(pawn, x, y);

    }

    public void addRook(boolean isWhite, int x, int y) {
        Rook rook = new Rook(board, isWhite);
        board.placePiece(rook, x, y);

    }

    public void addBishop(boolean isWhite, int x, int y) {
        Bishop bishop = new Bishop(board, isWhite);
        board.placePiece(bishop, x, y);
    }

    public void addQueen(boolean isWhite, int x, int y) {
        Queen queen = new Queen(board, isWhite);
        board.placePiece(queen, x, y);
    }

    public void addKnight(boolean isWhite, int x, int y) {
        Knight knight = new Knight(board, isWhite);
        board.placePiece(knight, x, y);
    }


    public void starGame() {

        moveTo("e2-e4");
        moveTo("e7-e5");



        board.printBoard();
    }

    public void moveTo(String move) {
        int x = Character.getNumericValue(move.charAt(1)) - 1;
        int y = board.map.get(move.charAt(0));
        board.getPiece(x, y).moveTo(move);

    }

}
