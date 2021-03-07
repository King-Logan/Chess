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
        this.isFirstMove = isFirstMove;
    }

  
    public int getCoordinate(){
        return this.coordinate;
    }

    public void setCoordinate(int coordinate) {
        this.coordinate = coordinate;
    }
    
    public Faction getColor(){
        return this.color;
    }
    
    public abstract List<Integer> findLegalMoves(Board board); //finds possible coordinates of moves
    
    // public boolean testSafeKingMove(Board board, int moveCoordinate){//test move on same board, and then undoes. 
    //     //will only test on otherwise legalMoves.
    //     int startCoordinate = this.coordinate;
    //     Piece originalPiece = board.getTiles()[moveCoordinate].getPiece();
    //     boolean kingSafe;

    //     board.getTiles()[startCoordinate].setPiece(new Empty(this.coordinate)); //clears start coordinate
    //     board.getTiles()[moveCoordinate].setPiece(this); //sets move piece to new coordinate  
        
    //     if(this.getColor() == Faction.WHITE){
    //         kingSafe = board.isCheck(Faction.WHITE);
    //     } else {
    //         kingSafe = board.isCheck(Faction.BLACK);
    //     }
        
    //     board.getTiles()[startCoordinate].setPiece(this); //returns piece to original location
    //     board.getTiles()[moveCoordinate].setPiece(originalPiece); //returns taken piece to original location

    //     return kingSafe;
    // }
        
    
    public void makeMove(Board board, int moveCoordinate){
        if(findLegalMoves(board).contains(moveCoordinate)){ 
            boolean capture = board.getTiles()[moveCoordinate].isOccupied();
            String captureType = board.getTiles()[moveCoordinate].getPiece().toString();
            int startCoordinate = this.coordinate;
            board.getTiles()[moveCoordinate].getPiece().setCoordinate(-1);
            board.getTiles()[startCoordinate].setPiece(new Empty(this.coordinate));
            board.getTiles()[moveCoordinate].setPiece(this);  
            this.isFirstMove = false;       
            System.out.println("Successful move - " + this.pieceType + " to " + board.intToString(moveCoordinate));
            if(capture){ 
                System.out.println("Enemy Piece Captured: " + captureType);
            }
            
        }else{
            System.out.println("Failed to move.");
        }
    }

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
