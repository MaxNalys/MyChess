
import piece.*;
import utils.Coordinates;
import utils.Parser;

import java.util.Scanner;

public class ChessGame {
    private Board board;


    Scanner scanner = new Scanner(System.in);

    ChessGame() {
        this.board = new Board();
        setUp();

    }

    public void setUp() {
        addBishop(true, 0, 2);
        addBishop(true, 0, 5);
        addKnight(true, 0, 1);
        addKnight(true, 0, 6);
        addRook(true, 0, 0);
        addRook(true, 0, 7);
        addQueen(true, 0, 4);
        addPawn(true, 1, 0);
        addPawn(true, 1, 1);
        addPawn(true, 1, 2);
        addPawn(true, 1, 3);
        addPawn(true, 1, 4);
        addPawn(true, 1, 5);
        addPawn(true, 1, 6);
        addPawn(true, 1, 7);
        addBishop(false, 7, 2);
        addBishop(false, 7, 5);
        addKnight(false, 7, 1);
        addKnight(false, 7, 6);
        addRook(false, 7, 0);
        addRook(false, 7, 7);
        addQueen(false, 7, 4);
        addPawn(false, 6, 0);
        addPawn(false, 6, 1);
        addPawn(false, 6, 2);
        addPawn(false, 6, 3);
        addPawn(false, 6, 4);
        addPawn(false, 6, 5);
        addPawn(false, 6, 6);
        addPawn(false, 6, 7);
        addKing(true, 0, 3);
        addKing(false, 7, 3);
    }

    private void addKing(boolean isWhite, int x, int y) {
        King king = new King(isWhite);
        placePiece(king, x, y);
    }

    private void addPawn(boolean isWhite, int x, int y) {
        Pawn pawn = new Pawn(isWhite);
        placePiece(pawn, x, y);
    }

    private void addRook(boolean isWhite, int x, int y) {
        Rook rook = new Rook(isWhite);
        placePiece(rook, x, y);
    }

    private void addBishop(boolean isWhite, int x, int y) {
        Bishop bishop = new Bishop(isWhite);
        placePiece(bishop, x, y);
    }

    private void addQueen(boolean isWhite, int x, int y) {
        Queen queen = new Queen(isWhite);
        placePiece(queen, x, y);
    }

    private void addKnight(boolean isWhite, int x, int y) {
        Knight knight = new Knight(isWhite);
        placePiece(knight, x, y);
    }

    public void starGame() {
        moveTo("e2-e4");
        moveTo("e4-e5");
        moveTo("e5-e6");
        moveTo("e7-e5");
        moveTo("d2-d4");
        moveTo("d4-d5");
        moveTo("d5-d6");
        moveTo("d7-d5");


        // TODO add cycle here

        board.printBoard();
    }

    private void moveTo(String move) {
        Coordinates[] arr = Parser.parseInput(move);

        if (board.getPiece(arr[0].getX(), arr[0].getY()).canMoveTo(move) && checkBasicRules(move)) {
            replacePiece(move);
        }
    }

    private void placePiece(Piece piece, int x, int y) {
        board.getBoard()[x][y] = piece;
    }

    private void replacePiece(String move) {
        Coordinates[] arr = Parser.parseInput(move);
        board.getBoard()[arr[1].getX()][arr[1].getY()] = board.getPiece(arr[0].getX(), arr[0].getY());
        deletePiece(arr[0].getX(), arr[0].getY());
    }

    private void deletePiece(int x, int y) {
        board.getBoard()[x][y] = null;
    }

    private boolean isEntranceOfBoard(String move) {
        Coordinates[] arr = Parser.parseInput(move);
        return arr[1].getX() < Board.PIECE_BOARD_SIZE && arr[1].getX() >= 0 && arr[1].getY() < Board.PIECE_BOARD_SIZE && arr[1].getY() >= 0;
    }

    private boolean isEmptyPosition(String move) {
        Coordinates[] arr = Parser.parseInput(move);
        return board.getBoard()[arr[1].getX()][arr[1].getY()] == null;
    }

    private boolean isYourColor(String move) {
        Coordinates[] arr = Parser.parseInput(move);
        return board.getPiece(arr[1].getX(), arr[1].getY()).isWhite() == board.getPiece(arr[0].getX(), arr[0].getY()).isWhite();
    }

    private boolean checkBasicRules(String move) {
        if (isEntranceOfBoard(move)) {
            if (move.contains("-")) {
                if (isEmptyPosition(move)) {
                    return true;
                }
            } else if (move.contains("x")) {
                if (!isYourColor(move)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean determinePiecesBetweenMove(String move) {
        Coordinates[] arr = Parser.parseInput(move);
        if (Pawn.isMoved()) {
            return true;
        } else if (!Pawn.isMoved() && board.getPiece(arr[1].getX() - 1, arr[0].getY()) != null) {
            return true;
        }
        return false;
    }
}
