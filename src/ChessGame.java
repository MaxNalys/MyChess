
import board.Board;
import board.PrintBoard;
import piece.*;
import utils.Coordinates;
import utils.Parser;

import java.util.LinkedList;
import java.util.Scanner;

public class ChessGame {
    private boolean playingForWhite;
    private King blackKing;
    private King whiteKing;
    private Board board;
    private LinkedList<Piece> blackPieces;
    private LinkedList<Piece> whitePieces;


    ChessGame() {
        this.board = new Board();
        blackKing = new King(false);
        whiteKing = new King(true);
        blackPieces = new LinkedList<Piece>();
        whitePieces = new LinkedList<Piece>();
        setUp();
        chooseColour();

    }

    public void chooseColour() {
        //true-white
        //false-black
        this.playingForWhite = false;
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
        placePiece(blackKing, 7, 3);
        placePiece(whiteKing, 0, 3);
    }

    private void pieceColorHelper(Piece piece, boolean isWhite) {
        if (isWhite) {
            whitePieces.add(piece);
        } else {
            blackPieces.add(piece);
        }
    }


    private void addPawn(boolean isWhite, int x, int y) {
        Pawn pawn = new Pawn(isWhite);
        placePiece(pawn, x, y);
        pieceColorHelper(pawn, isWhite);
    }


    private void addRook(boolean isWhite, int x, int y) {
        Rook rook = new Rook(isWhite);
        placePiece(rook, x, y);
        pieceColorHelper(rook, isWhite);
    }

    private void addBishop(boolean isWhite, int x, int y) {
        Bishop bishop = new Bishop(isWhite);
        placePiece(bishop, x, y);
        pieceColorHelper(bishop, isWhite);
    }

    private void addQueen(boolean isWhite, int x, int y) {
        Queen queen = new Queen(isWhite);
        placePiece(queen, x, y);
        pieceColorHelper(queen, isWhite);
    }

    private void addKnight(boolean isWhite, int x, int y) {
        Knight knight = new Knight(isWhite);
        placePiece(knight, x, y);
        pieceColorHelper(knight, isWhite);
    }

    private void placePiece(Piece piece, int x, int y) {
        board.getBoard()[x][y] = piece;
    }

    public void starGame() {
        Scanner scanner = new Scanner(System.in);
        moveTo("e2-e4");
        moveTo("e7-e5");
        moveTo("d8-e7");



        PrintBoard.printBoard(board);

        // TODO add cycle here
        // Start implementing this


    }

    private void moveTo(String move) {
        Coordinates[] coordinates = Parser.parseInput(move);
        if (board.getPieceFromStartPosition(move).canMoveTo(coordinates[0], coordinates[1]) && checkBasicRules(move)) {
            if (isKingInCheck(move)) {
                System.out.println("Check");
            }
            replacePiece(move);

        }
    }

    public boolean isKingInCheck(String move) {
        Coordinates[] coordinates = Parser.parseInput(move);
        King kingInCheck;
        LinkedList<Piece> pieceLinkedList;
        if (board.getPieceFromStartPosition(move).isWhite()) {
            kingInCheck = blackKing;
            pieceLinkedList = whitePieces;
        } else {
            kingInCheck = whiteKing;
            pieceLinkedList = blackPieces;
        }

        for (Piece curPiece : pieceLinkedList) {
            if (curPiece == board.getPieceFromStartPosition(move)) {
                if (curPiece.canMoveTo(coordinates[1], board.getPieceCoordinates(kingInCheck)) && determineAnyPiecesBetweenMoves(Parser.convertCoordinatesToMove(coordinates[1], board.getPieceCoordinates(kingInCheck)))) {
                    return true;
                }
            } else if (curPiece.canMoveTo(board.getPieceCoordinates(curPiece), board.getPieceCoordinates(kingInCheck)) && determineAnyPiecesBetweenMoves(Parser.convertCoordinatesToMove(board.getPieceCoordinates(curPiece), board.getPieceCoordinates(kingInCheck)))) {
                return true;
            }
        }

        return false;

    }

    private void replacePiece(String move) {
        board.setPieceOnTheNextSpot(move);
        deletePieceFromPosition(move);
    }

    private void deletePieceFromPosition(String move) {
        Coordinates[] coordinates = Parser.parseInput(move);
        board.getBoard()[coordinates[0].getX()][coordinates[0].getY()] = null;
    }


    private boolean isWithinBoundsMove(String move) {
        Coordinates[] coordinates = Parser.parseInput(move);
        return coordinates[1].getX() < Board.PIECE_BOARD_SIZE && coordinates[1].getX() >= 0 && coordinates[1].getY() < Board.PIECE_BOARD_SIZE && coordinates[1].getY() >= 0;
    }

    private boolean isEmptyPosition(String move) {
        return board.getPieceFromNextPosition(move) == null;
    }

    private boolean isYourColor(String move) {
        return board.getPieceFromStartPosition(move).isWhite() == board.getPieceFromNextPosition(move).isWhite();
    }

    private boolean checkBasicRules(String move) {
        if (isWithinBoundsMove(move) && determineAnyPiecesBetweenMoves(move)) {
            if (isEmptyPosition(move)) {
                return true;
            }
            return !isYourColor(move);
        }
        return false;
    }

    public boolean determineAnyPiecesBetweenMoves(String move) {
        Coordinates[] coordinates = Parser.parseInput(move);
        switch (board.getPieceFromStartPosition(move).getIdentifiers()) {
            case PAWN:
                int value;
                if (Math.abs(coordinates[0].getY() - coordinates[1].getY()) == 1) {
                    return true;
                }
                if (board.getPieceFromStartPosition(move).isWhite()) {
                    value = 1;
                } else {
                    value = -1;
                }
                return board.getBoard()[coordinates[0].getX() + value][coordinates[0].getY()] == null;
            case BISHOP:
                return lookForPiecesBetweenMovesForDiagonalMoves(move);
            case ROOK:
                return lookForPiecesBetweenMovesForStraightMoves(move);
            case QUEEN:
                return lookForPiecesBetweenMovesForStraightMoves(move) || lookForPiecesBetweenMovesForDiagonalMoves(move);
            case KNIGHT:
            case KING:
                return true;
            default:
                return false;
        }
    }

    private boolean lookForPiecesBetweenMovesForDiagonalMoves(String move) {
        Coordinates[] coordinates = Parser.parseInput(move);
        int xStart = 0;
        int yStart = 0;
        int xFinish = 1;

        if (coordinates[1].getX() < coordinates[0].getX()) {
            xStart = coordinates[1].getX();
            xFinish = coordinates[0].getX();
        } else if (coordinates[1].getX() > coordinates[0].getX()) {
            xStart = coordinates[1].getX();
            xFinish = coordinates[0].getX();
        }

        if (coordinates[1].getY() < coordinates[0].getY()) {
            yStart = coordinates[1].getY();
        } else if (coordinates[1].getY() > coordinates[0].getY()) {
            yStart = coordinates[0].getY();
        }
        yStart++;
        xStart++;
        for (; xStart < xFinish; xStart++, yStart++) {
            if (board.getPieceAt(xStart, yStart) != null) {
                return false;
            }
        }
        return true;
    }

    private boolean lookForPiecesBetweenMovesForStraightMoves(String move) {
        Coordinates[] coordinates = Parser.parseInput(move);
        int smallerVal;
        int largerVal;

        if (coordinates[0].getX() == coordinates[1].getX()) {
            if (coordinates[0].getY() > coordinates[1].getY()) {
                smallerVal = coordinates[1].getY();
                largerVal = coordinates[0].getY();
            } else if (coordinates[1].getY() > coordinates[0].getY()) {
                smallerVal = coordinates[0].getY();
                largerVal = coordinates[1].getY();
            } else return false;

            smallerVal++;
            for (; smallerVal < largerVal; smallerVal++) {
                if (board.getPieceAt(coordinates[0].getX(), smallerVal) != null) {
                    return false;
                }
            }
            return true;
        }

        if (coordinates[0].getY() == coordinates[1].getY()) {
            if (coordinates[0].getX() > coordinates[1].getX()) {
                smallerVal = coordinates[1].getX();
                largerVal = coordinates[0].getX();
            } else if (coordinates[1].getX() > coordinates[0].getX()) {
                smallerVal = coordinates[0].getX();
                largerVal = coordinates[1].getX();
            } else return false;


            smallerVal++;
            for (; smallerVal < largerVal; smallerVal++) {
                if (board.getPieceAt(smallerVal, coordinates[0].getY()) != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


}
