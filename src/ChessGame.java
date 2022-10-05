
import piece.*;
import utils.Coordinates;
import utils.Parser;

import java.util.Scanner;

public class ChessGame {
    private Board board;
    private King blackKing;
    private King whiteKing;


    Scanner scanner = new Scanner(System.in);

    ChessGame() {
        this.board = new Board();
        this.whiteKing = new King(true);
        this.blackKing = new King(false);

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

    public void addKing(boolean isWhite, int x, int y) {
        King king = new King(isWhite);
        placePiece(king, x, y);
    }

    public void addPawn(boolean isWhite, int x, int y) {
        Pawn pawn = new Pawn(isWhite);
        placePiece(pawn, x, y);
    }

    public void addRook(boolean isWhite, int x, int y) {
        Rook rook = new Rook(isWhite);
        placePiece(rook, x, y);
    }

    public void addBishop(boolean isWhite, int x, int y) {
        Bishop bishop = new Bishop(isWhite);
        placePiece(bishop, x, y);
    }

    public void addQueen(boolean isWhite, int x, int y) {
        Queen queen = new Queen(isWhite);
        placePiece(queen, x, y);
    }

    public void addKnight(boolean isWhite, int x, int y) {
        Knight knight = new Knight(isWhite);
        placePiece(knight, x, y);
    }


    public void starGame() {
        moveTo("e2-e4");
        moveTo("e7-e5");
        moveTo("e4-e5");


        // TODO add cycle here

        board.printBoard();
    }

    public void moveTo(String move) {
        Coordinates[] arr = Parser.parseInput(move);
        if (checkBasicRules(move) && board.getPiece(arr[0].getX(), arr[0].getY()).canMoveTo(move)) {
            replacePiece(move);
        }
    }


    public void placePiece(Piece piece, int x, int y) {
        board.getBoard()[x][y] = piece;
    }

    public void replacePiece(String move) {
        Coordinates[] arr = Parser.parseInput(move);
        // TODO
        // Coordinates from = arr[0]; //or home, route or some better naming
        // Coordinates to = arr[1]; // or destination

        // also you should add method to Board: smth like
        // Piece getPiece(Coordinates coordinates)
        // and remove this ugly getter board.getBoard()[arr[1].getX()][arr[1].getY()]

        board.getBoard()[arr[1].getX()][arr[1].getY()] = board.getPiece(arr[0].getX(), arr[0].getY());
        deletePiece(arr[0].getX(), arr[0].getY());
    }

    // TODO
    // since you have Coordinates class now try to use it as a parameter everywhere
    public void deletePiece(int x, int y) {
        board.getBoard()[x][y] = null;
    }

    public boolean isEntranceOfBoard(String move) {
        Coordinates[] arr = Parser.parseInput(move);
        return arr[1].getX() < Board.PIECE_BOARD_SIZE && arr[1].getX() >= 0 && arr[1].getY() < Board.PIECE_BOARD_SIZE && arr[1].getY() >= 0;
    }

    public boolean isEmptyPosition(String move) {
        Coordinates[] arr = Parser.parseInput(move);
        return board.getBoard()[arr[1].getX()][arr[1].getY()] == null;
    }

    public boolean isYourColor(String move) {
        Coordinates[] arr = Parser.parseInput(move);
        return board.getPiece(arr[1].getX(), arr[1].getY()).isWhite() == board.getPiece(arr[0].getX(), arr[0].getY()).isWhite();
    }

    public boolean checkBasicRules(String move) {
        if (isEntranceOfBoard(move)) {
            if (isEmptyPosition(move)) {
                return true;
            }
            if (!isYourColor(move)) {
                return true;
            }
        }
        return false;
    }


}
