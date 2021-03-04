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
    public void makeMove(Board board, int moveCoordinate){
        if(findLegalMoves(board).contains(moveCoordinate)){
            boolean capture = board.getTiles()[moveCoordinate].isOccupied();
            String captureType = board.getTiles()[moveCoordinate].getPiece().toString();
            int startCoordinate = this.coordinate;
            board.getTiles()[moveCoordinate].getPiece().setCoordinate(-1);
            board.getTiles()[startCoordinate].setPiece(new Empty(this.coordinate));
            board.getTiles()[moveCoordinate].setPiece(this);        
            System.out.println("Successful move - " + this.pieceType + " to " + board.intToString(moveCoordinate));
            if(capture){ 
                System.out.println("Enemy Piece Captured: " + captureType);
            }
            
        }else{
            System.out.println("Failed to move.");
        }
    }
    
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
