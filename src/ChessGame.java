
import board.Board;
import board.PrintBoard;
import piece.*;
import utils.ColourGameChange;
import utils.Coordinates;
import utils.Parser;

import javax.print.DocFlavor;
import java.util.LinkedList;
import java.util.Scanner;

public class ChessGame {
    private ColourGameChange colourGameChange;
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
        colourGameChange = new ColourGameChange();

    }


    public void setUp() {
        addBishop(true, "c1");
        addBishop(true, "f1");
        addKnight(true, "b1");
        addKnight(true, "g1");
        addRook(true, "a1");
        addRook(true, "h1");
        addQueen(true, "d1");
        addPawn(true, "a2");
        addPawn(true, "b2");
        addPawn(true, "c2");
        addPawn(true, "d2");
        addPawn(true, "e2");
        addPawn(true, "f2");
        addPawn(true, "g2");
        addPawn(true, "h2");
        addBishop(false, "c8");
        addBishop(false, "f8");
        addKnight(false, "b8");
        addKnight(false, "g8");
        addRook(false, "a8");
        addRook(false, "h8");
        addQueen(false, "d8");
        addPawn(false, "a7");
        addPawn(false, "b7");
        addPawn(false, "c7");
        addPawn(false, "d7");
        addPawn(false, "e7");
        addPawn(false, "f7");
        addPawn(false, "g7");
        addPawn(false, "h7");
        addKing(whiteKing, "e1");
        addKing(blackKing, "e8");
    }

    public void deletePieceFromList(Piece piece) {
        if (whitePieces.contains(piece)) {
            whitePieces.remove(piece);
        } else {
            blackPieces.remove(piece);
        }
    }

    private void pieceColorHelper(Piece piece, boolean isWhite) {
        if (isWhite) {
            whitePieces.add(piece);
        } else {
            blackPieces.add(piece);
        }
    }

    private void addKing(King king, String move) {
        Coordinates coordinates = Parser.convertFistPartOfMove(move);
        placePiece(king, coordinates.getX(), coordinates.getY());
        pieceColorHelper(king, king.isWhite());
    }

    private void addPawn(boolean isWhite, String move) {
        Coordinates coordinates = Parser.convertFistPartOfMove(move);
        Pawn pawn = new Pawn(isWhite);
        placePiece(pawn, coordinates.getX(), coordinates.getY());
        pieceColorHelper(pawn, isWhite);
    }


    private void addRook(boolean isWhite, String move) {
        Coordinates coordinates = Parser.convertFistPartOfMove(move);
        Rook rook = new Rook(isWhite);
        placePiece(rook, coordinates.getX(), coordinates.getY());
        pieceColorHelper(rook, isWhite);
    }

    private void addBishop(boolean isWhite, String move) {
        Coordinates coordinates = Parser.convertFistPartOfMove(move);
        Bishop bishop = new Bishop(isWhite);
        placePiece(bishop, coordinates.getX(), coordinates.getY());
        pieceColorHelper(bishop, isWhite);
    }

    private void addQueen(boolean isWhite, String move) {
        Coordinates coordinates = Parser.convertFistPartOfMove(move);
        Queen queen = new Queen(isWhite);
        placePiece(queen, coordinates.getX(), coordinates.getY());
        pieceColorHelper(queen, isWhite);
    }

    private void addKnight(boolean isWhite, String move) {
        Coordinates coordinates = Parser.convertFistPartOfMove(move);
        Knight knight = new Knight(isWhite);
        placePiece(knight, coordinates.getX(), coordinates.getY());
        pieceColorHelper(knight, isWhite);
    }

    private void placePiece(Piece piece, int x, int y) {
        board.getBoard()[x][y] = piece;
    }

    public void starGame() {
        Scanner scanner = new Scanner(System.in);
        boolean continueGame = true;
        King king;
        while (continueGame) {
          
            if (colourGameChange.isWhite()) {
                king = whiteKing;
            } else {
                king = blackKing;
            }
            PrintBoard.printBoard(board, colourGameChange.isWhite());
            System.out.println();
            String move = scanner.next();
            if (king.isCheck()) {
                moveTo(move);
                if (!checkMovement()) {
                    System.out.println("ERROR");
                    return;
                }
            } else {
                moveTo(move);
            }
            if (isKingInCheck()) {
                System.out.println("check");
            }
            colourGameChange.gameChangeColour();
            System.out.println();
        }
    }

    public boolean checkMovement() {
        colourGameChange.gameChangeColour();
        if (isKingInCheck()) {
            return false;
        }
        colourGameChange.gameChangeColour();
        return true;
    }

    public boolean isGameOver() {
        if (isCheckMate()) {
            System.out.println("CheckMate");
            return true;
        } else if (isStaleMate()) {
            System.out.println("StaleMate");
            return true;
        }
        return false;
    }

    public boolean isCheckMate() {
        King king;
        LinkedList<Piece> linkedList;
        if (colourGameChange.isWhite()) {
            linkedList = whitePieces;
            king = whiteKing;
        } else {
            linkedList = blackPieces;
            king = blackKing;
        }
        if (!king.isCheck()) {
            return false;
        }
        for (int i = 0; i < Board.PIECE_BOARD_SIZE; i++) {
            for (int j = 0; j < Board.PIECE_BOARD_SIZE; j++) {
                Coordinates coordinates = new Coordinates(i, j);
                for (Piece piece : linkedList) {
                    if (piece.canMoveTo(board.getPieceCoordinates(piece), coordinates) && determineAnyPiecesBetweenMoves(Parser.convertCoordinatesToMove(board.getPieceCoordinates(piece), coordinates))) {
                        replacePiece(Parser.convertCoordinatesToMove(board.getPieceCoordinates(piece), coordinates));
                        if (checkMovement()) {
                            deletePieceFromNextPosition(Parser.convertCoordinatesToMove(board.getPieceCoordinates(piece), coordinates));
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean isStaleMate() {
        LinkedList<Piece> linkedList;
        if (colourGameChange.isWhite()) {
            linkedList = whitePieces;
        } else {
            linkedList = blackPieces;
        }
        for (int i = 0; i < Board.PIECE_BOARD_SIZE; i++) {
            for (int j = 0; j < Board.PIECE_BOARD_SIZE; j++) {
                Coordinates coordinates = new Coordinates(i, j);
                for (Piece piece : linkedList) {
                    if (piece.canMoveTo(board.getPieceCoordinates(piece), coordinates) && determineAnyPiecesBetweenMoves(Parser.convertCoordinatesToMove(board.getPieceCoordinates(piece), coordinates))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void moveTo(String move) {
        Coordinates[] coordinates = Parser.parseInput(move);
        if (canCastling(move)) {
            board.getPieceFromStartPosition(move).setMoved(true);
            board.getPieceFromNextPosition(move).setMoved(true);
            castlingMove(move);
        } else if (board.getPieceFromStartPosition(move).canMoveTo(coordinates[0], coordinates[1]) && checkBasicRules(move)) {
            if (board.getPieceFromNextPosition(move) != null) {
                deletePieceFromList(board.getPieceFromNextPosition(move));
            }
            board.getPieceFromStartPosition(move).setMoved(true);
            replacePiece(move);
        }
    }


    public void castlingMove(String move) {
        Coordinates[] coordinates = Parser.parseInput(move);
        if (isShortCastling(move)) {
            placePiece(board.getPieceFromStartPosition(move), coordinates[0].getX(), 1);
            deletePieceFromStartPosition(move);
            placePiece(board.getPieceFromNextPosition(move), coordinates[0].getX(), 2);
            deletePieceFromNextPosition(move);
        } else {
            placePiece(board.getPieceFromStartPosition(move), coordinates[0].getX(), 5);
            deletePieceFromStartPosition(move);
            placePiece(board.getPieceFromNextPosition(move), coordinates[0].getX(), 4);
            deletePieceFromNextPosition(move);
        }

    }

    public boolean isCastling(String move) {
        if (board.getPieceFromNextPosition(move) != null) {
            return board.getPieceFromStartPosition(move).getIdentifiers().equals(PieceName.KING) && board.getPieceFromNextPosition(move).getIdentifiers().equals(PieceName.ROOK);
        }
        return false;
    }

    public boolean canCastling(String move) {
        Coordinates[] coordinates = Parser.parseInput(move);
        LinkedList<Piece> pieceLinkedList;

        if (!colourGameChange.isWhite()) {
            pieceLinkedList = whitePieces;
        } else {
            pieceLinkedList = blackPieces;
        }

        if (isKingInCheck()) {
            return false;
        }

        if (isCastling(move)) {
            if (board.getPieceFromStartPosition(move).hasMoved() || board.getPieceFromNextPosition(move).hasMoved()) {
                return false;
            }
            if (isShortCastling(move)) {
                for (int i = coordinates[0].getY() - 1; i > coordinates[1].getY(); i--) {
                    Coordinates newCoordinates = new Coordinates(coordinates[0].getX(), i);
                    for (Piece curPiece : pieceLinkedList) {
                        if (board.getBoard()[coordinates[0].getX()][i] != null) {
                            return false;
                        }
                        if (curPiece.canMoveTo(board.getPieceCoordinates(curPiece), newCoordinates) && determineAnyPiecesBetweenMoves(Parser.convertCoordinatesToMove(board.getPieceCoordinates(curPiece), newCoordinates))) {
                            return false;
                        }
                    }
                }
                return true;
            } else {
                for (int i = coordinates[0].getY() + 1; i < coordinates[1].getY(); i++) {
                    Coordinates newCoordinates = new Coordinates(coordinates[0].getX(), i);
                    for (Piece curPiece : pieceLinkedList) {
                        if (board.getBoard()[coordinates[0].getX()][i] != null) {
                            return false;
                        }
                        if (curPiece.canMoveTo(board.getPieceCoordinates(curPiece), newCoordinates) && determineAnyPiecesBetweenMoves(Parser.convertCoordinatesToMove(board.getPieceCoordinates(curPiece), newCoordinates))) {
                            return false;
                        }
                    }
                }
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean isShortCastling(String move) {
        Coordinates[] coordinates = Parser.parseInput(move);
        return coordinates[0].getY() - coordinates[1].getY() >= 0;
    }

    public boolean isKingInCheck() {
        LinkedList<Piece> pieceLinkedList;
        King king;
        if (colourGameChange.isWhite()) {
            pieceLinkedList = whitePieces;
            king = blackKing;
        } else {
            pieceLinkedList = blackPieces;
            king = whiteKing;
        }

        for (Piece curPiece : pieceLinkedList) {
            if (curPiece.canMoveTo(board.getPieceCoordinates(curPiece), board.getPieceCoordinates(king)) && determineAnyPiecesBetweenMoves(Parser.convertCoordinatesToMove(board.getPieceCoordinates(curPiece), board.getPieceCoordinates(king)))) {
                king.setCheck(true);
                return true;
            }
        }
        king.setCheck(false);
        return false;
    }

    private void replacePiece(String move) {
        board.setPieceOnTheNextSpot(move);
        deletePieceFromStartPosition(move);
    }

    private void deletePieceFromStartPosition(String move) {
        Coordinates[] coordinates = Parser.parseInput(move);
        board.getBoard()[coordinates[0].getX()][coordinates[0].getY()] = null;
    }

    private void deletePieceFromNextPosition(String move) {
        Coordinates[] coordinates = Parser.parseInput(move);
        board.getBoard()[coordinates[1].getX()][coordinates[1].getY()] = null;
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

        int xTotal = Math.abs(coordinates[1].getX() - coordinates[0].getX());
        int yTotal = Math.abs(coordinates[1].getY() - coordinates[0].getY());

        if (xTotal == yTotal) {
            if (coordinates[1].getX() < coordinates[0].getX()) {
                xStart = coordinates[1].getX();
                xFinish = coordinates[0].getX();
            } else if (coordinates[1].getX() > coordinates[0].getX()) {
                xStart = coordinates[0].getX();
                xFinish = coordinates[1].getX();
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
        return false;
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
