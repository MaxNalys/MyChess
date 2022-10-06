
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

    private void placePiece(Piece piece, int x, int y) {
        board.getBoard()[x][y] = piece;
    }

    public void starGame() {
        moveTo("e2-e4");
        moveTo("e4-e5");
        moveTo("e5-e6");




        // TODO add cycle here

        board.printBoard();
    }

    private void moveTo(String move) {
        Coordinates[] coordinates=Parser.parseInput(move);
        if (board.getPieceFromStartPosition(move).canMoveTo(coordinates[0],coordinates[1]) && checkBasicRules(move)) {
            replacePiece(move);
        }
    }

    private void replacePiece(String move) {
        board.setPieceOnTheNextSpot(move);
        deletePieceFromPosition(move);
    }

    private void deletePieceFromPosition(String move) {
        Coordinates[] coordinates = Parser.parseInput(move);
        board.getBoard()[coordinates[0].getX()][coordinates[0].getY()] = null;
    }

    private boolean isEntranceOfBoard(String move) {
        Coordinates[] coordinates = Parser.parseInput(move);
        return coordinates[1].getX() < Board.PIECE_BOARD_SIZE && coordinates[1].getX() >= 0 && coordinates[1].getY() < Board.PIECE_BOARD_SIZE && coordinates[1].getY() >= 0;
    }

    private boolean isEmptyPosition(String move) {
        Coordinates[] coordinates = Parser.parseInput(move);
        return board.getBoard()[coordinates[1].getX()][coordinates[1].getY()] == null;
    }

    private boolean isYourColor(String move) {
        return board.getPieceFromStartPosition(move).isWhite() == board.getPieceFromNextPosition(move).isWhite();
    }

    private boolean checkBasicRules(String move) {
        if (isEntranceOfBoard(move)) {
            if (move.contains("-")) {
                return isEmptyPosition(move);
            } else if (move.contains("x")) {
                return !isYourColor(move);
            }
        }
        return false;
    }


}
