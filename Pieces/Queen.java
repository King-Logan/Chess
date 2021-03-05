package Pieces;

import java.util.ArrayList;
import java.util.List;

import Game.Board;

public class Queen extends Piece {

    private int[] QUEEN_MOVE_OFFSETS = {-9, -8, -7, -1, 1, 7, 8, 9};

    public Queen(Faction color, int coordinate){
        super(color, PieceType.QUEEN, coordinate, true);
    }

    public String toString() {
        if(this.getColor() == Faction.WHITE){
            return " " + ( (char) 9819) + " ";
        }
        else{
            return " " + ( (char) 9813) + " ";
        }
    }

    @Override
    public List<Integer> findLegalMoves(Board board) {
                
        
        List<Integer> legalMoves = new ArrayList<>();
        for (int offset : QUEEN_MOVE_OFFSETS) {
            for (int i = 1; i <= 7; i++) {
                int moveCoordinate = coordinate + (offset * i);
                if(moveCoordinate < 0 || moveCoordinate > 63){ //ooBounds
                    i = 8;
                }
                else{
                    int columnOffset = Math.abs(board.getTiles()[coordinate].getColumn() - board.getTiles()[moveCoordinate].getColumn());
                    int rowOffset = Math.abs(board.getTiles()[coordinate].getRow() - board.getTiles()[moveCoordinate].getRow());
                    if( (rowOffset == 0 || columnOffset == 0) || (columnOffset == rowOffset) ){
                        if(board.getTiles()[moveCoordinate].isOccupied()){ //if there's a piece we cannot go farther
                            if(board.getTiles()[moveCoordinate].getPiece().getColor() != this.getColor()){//take a piece
                                legalMoves.add(moveCoordinate);
                            }
                            i = 8;
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
