package Pieces;

import java.util.ArrayList;
import java.util.List;

import Game.Board;

public class Rook extends Piece {

    private int[] ROOK_MOVE_OFFSETS = {-8, -1, 1, 8};

    public Rook(Faction color, int coordinate) {
        super(color, PieceType.ROOK, coordinate, true);
    }

    public String toString() {
        if(this.getColor() == Faction.WHITE){
            return " " + ( (char) 9820) + " ";
        }
        else{
            return " " + ( (char) 9814) + " ";
        }
    }

    @Override
    public List<Integer> findLegalMoves(Board board) {
        List<Integer> legalMoves = new ArrayList<>();
        for (int offset : ROOK_MOVE_OFFSETS) {
            for (int i = 1; i <= 7; i++) {
                int moveCoordinate = coordinate + (offset * i);
                if(moveCoordinate < 0 || moveCoordinate > 63){ //ooBounds
                    i = 8;
                }
                else{
                    int columnOffset = Math.abs(board.getTiles()[coordinate].getColumn() - board.getTiles()[moveCoordinate].getColumn());
                    int rowOffset = Math.abs(board.getTiles()[coordinate].getRow() - board.getTiles()[moveCoordinate].getRow());
                    if(rowOffset == 0 || columnOffset == 0){
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
