package Pieces;

import java.util.List;

import Game.Board;

public abstract class Piece {
    Faction color;
    PieceType pieceType;
    int coordinate;
    boolean isFirstMove;



    public Piece(Faction color, PieceType pieceType, int coordinate, boolean isFirstMove){
        this.color = color;
        this.pieceType = pieceType;
        this.coordinate = coordinate;
    }
    public int getCoordinate(){
        return this.coordinate;
    }

    public void setCoordinate(int coordinate) {
        this.coordinate = coordinate;
    }

    public abstract List<Integer> findLegalMoves(Board board); //finds possible coordinates of moves
    public abstract void makeMove(Board board, int moveCoordinate); //moves piece
    public abstract Faction getColor();

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
