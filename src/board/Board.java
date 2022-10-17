package board;

import piece.Piece;
import utils.Coordinates;
import utils.Parser;

public class Board {

    private final Piece[][] board;
    public static final int PIECE_BOARD_SIZE = 8;

    public Board() {
        this.board = new Piece[PIECE_BOARD_SIZE][PIECE_BOARD_SIZE];
    }

    public Piece[][] getBoard() {
        return board;
    }

    public Piece getPieceAt(int x, int y) {
        return board[x][y];
    }

    public Piece getPieceFromStartPosition(String move) {
        Coordinates[] coordinates = Parser.parseInput(move);
        return board[coordinates[0].getX()][coordinates[0].getY()];
    }

    public Piece getPieceFromNextPosition(String move) {
        Coordinates[] coordinates = Parser.parseInput(move);
        return board[coordinates[1].getX()][coordinates[1].getY()];
    }

    public void setPieceOnTheNextSpot(String move) {
        Coordinates[] coordinates = Parser.parseInput(move);
        board[coordinates[1].getX()][coordinates[1].getY()] = getPieceFromStartPosition(move);
    }

    public Coordinates getPieceCoordinates(Piece piece){
        for (int i = 0; i < PIECE_BOARD_SIZE; i++) {
            for (int j = 0; j < PIECE_BOARD_SIZE; j++) {
                if (board[i][j]==piece) {
                    Coordinates pieceCoordinates = new Coordinates(i, j);
                    return pieceCoordinates;

                }
            }
        }
        return null;
    }

}
