package Pieces;

import java.util.List;

import Game.Board;

public class Bishop extends Piece {
    
    public Bishop(Faction color, int coordinate) {
        super(color, PieceType.BISHOP, coordinate, true);
    }

    public String toString() {
        if(this.getColor() == Faction.WHITE){
            return " " + ( (char) 9821) + " ";
        }
        else{
            return " " + ( (char) 9815) + " ";
        }
    }
    
    @Override
    public List<Integer> findLegalMoves(Board board) {
        // TODO Auto-generated method stub
        return null;
    }

}
