package Pieces;

import java.util.ArrayList;
import java.util.List;

import Game.Board;

public class Knight extends Piece{

    private int[] KNIGHT_MOVE_OFFSETS = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(Faction color, int coordinate) { 
        super(color, PieceType.KNIGHT, coordinate, true);
    }

    public Faction getColor(){
        return this.color;
    }

    public String toString() {
        return " " + this.color.name().charAt(0) + "N ";
    }

    public List<Integer> findLegalMoves(Board board){
        List<Integer> legalMoves = new ArrayList<>();
        for (int offset : KNIGHT_MOVE_OFFSETS) {
            int moveCoordinate = coordinate + offset;
            if(moveCoordinate >= 0 && moveCoordinate <= 63){ //not out of bounds
                int columnOffset = Math.abs(board.getTiles()[coordinate].getColumn() - board.getTiles()[moveCoordinate].getColumn());
                int rowOffset = Math.abs(board.getTiles()[coordinate].getRow() - board.getTiles()[moveCoordinate].getRow());
                if(rowOffset + columnOffset == 3){ //this prevents a straight line move
                    if(board.getTiles()[moveCoordinate].getPiece().getColor() != this.getColor()){
                        legalMoves.add(moveCoordinate);
                    }
                }
            }
        }
        System.out.println("Available Tiles: " + legalMoves);
        return legalMoves;
    } 
    
    public void makeMove(Board board, int moveCoordinate){
        if(findLegalMoves(board).contains(moveCoordinate)){
            int startCoordinate = this.coordinate;
            board.getTiles()[moveCoordinate].getPiece().setCoordinate(-1);
            board.getTiles()[startCoordinate].setPiece(new None(this.coordinate));
            board.getTiles()[moveCoordinate].setPiece(this);        
            System.out.println("Successful move.");
        }else{
            System.out.println("Failed to move.");
        }
    }

}
