package Pieces;

import java.util.ArrayList;
import java.util.List;

import Game.Board;

public class King extends Piece {
    
    private int[] KING_MOVE_OFFSETS = {-9, -8, -7, -1, 1, 7, 8, 9};

    public King(Faction color, int coordinate) {
        super(color, PieceType.KING, coordinate, true);
    }


    public String toString() {
        if(this.getColor() == Faction.WHITE){
            return " " + ( (char) 9818) + " ";
        }
        else{
            return " " + ( (char) 9812) + " ";
        }
    }

    @Override
    public List<Integer> findLegalMoves(Board board) {
        List<Integer> legalMoves = new ArrayList<>();
        for (int offset : KING_MOVE_OFFSETS) {
            int moveCoordinate = coordinate + offset;
            if( moveCoordinate >= 0 && moveCoordinate <= 63 ){
                int columnOffset = Math.abs(board.getTiles()[coordinate].getColumn() - board.getTiles()[moveCoordinate].getColumn());
                int rowOffset = Math.abs(board.getTiles()[coordinate].getRow() - board.getTiles()[moveCoordinate].getRow());
                if(rowOffset + columnOffset <= 2){
                    if(board.getTiles()[moveCoordinate].getPiece().getColor() != this.getColor()){
                        legalMoves.add(moveCoordinate);
                    }
                }
            }
        }
        return legalMoves;
    }


}
