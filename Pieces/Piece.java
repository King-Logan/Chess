package Pieces;

import java.util.Collection;

import Game.Board;

public abstract class Piece {
    final Faction color;
    final PieceType pieceType;
    int coordinate;
    boolean isFirstMove;



    public Piece(final Faction color, final PieceType pieceType, int coordinate, boolean isFirstMove){
        this.color = color;
        this.pieceType = pieceType;
        this.coordinate = coordinate;
    }

    public abstract Collection<Move> findLegalMoves(Board board);
    public abstract void makeMove(Move move);

    public enum PieceType {
        PAWN,
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        NONE
    }

    public enum Faction {
        WHITE,
        BLACK,
        NONE
    }

}
