package Pieces;

import java.util.ArrayList;
import java.util.List;

import Game.Board;

public class Bishop extends Piece {
    
    private int[] BISHOP_MOVE_OFFSETS = {-9, -7, 7, 9}; //x row for all 

    public Bishop(Faction color, int coordinate) {
        super(color, PieceType.BISHOP, coordinate, true);
    }

    public String toString() {
        if(this.getColor() == Faction.WHITE){
            return " " + ( (char) 9815) + " ";
        }
        else{
            return " " + ( (char) 9821) + " ";
        }
    }
    
    public List<Integer> findLegalMoves(Board board) {
        List<Integer> legalMoves = new ArrayList<>();
        for (int offset : BISHOP_MOVE_OFFSETS) {
            for (int i = 1; i <= 7; i++) {
                int moveCoordinate = coordinate + (offset * i);
                if(moveCoordinate < 0 || moveCoordinate > 63){ //ooBounds
                    i = 8;
                }
                else{
                    int columnOffset = Math.abs(board.getTiles()[coordinate].getColumn() - board.getTiles()[moveCoordinate].getColumn());
                    int rowOffset = Math.abs(board.getTiles()[coordinate].getRow() - board.getTiles()[moveCoordinate].getRow());
                    if(columnOffset == rowOffset){
                        if(board.getTiles()[moveCoordinate].isOccupied()){ //if there's a piece we cannot go farther
                            if(board.getTiles()[moveCoordinate].getPiece().getColor() != this.getColor()){//take a piece
                                legalMoves.add(moveCoordinate);
                            }
                            i = 8;//stop going in direction
                        }
                        else{
                            legalMoves.add(moveCoordinate);
                        }                
                    }
                }
            }
        }
        return legalMoves;
    }

}
