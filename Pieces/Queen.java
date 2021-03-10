package Pieces;

import java.util.ArrayList;
import java.util.List;

import Game.Board;

public class Queen extends Piece {

    private int[] QUEEN_DIAGONAL_MOVE_OFFSETS = {-9, -7, 7, 9}; // directions queen can move in
    private int[] QUEEN_AXIAL_MOVE_OFFSETS = {-8, -1, 1, 8}; //separated by direction

    public Queen(Faction color, int coordinate){
        super(color, PieceType.QUEEN, coordinate, true);
    }

    public String toString() {
        if(this.getColor() == Faction.WHITE){
            return " " + ( (char) 9813) + " ";
        }
        else{
            return " " + ( (char) 9819) + " ";
        }
    }

    @Override
    public List<Integer> findLegalMoves(Board board) {
        List<Integer> legalMoves = new ArrayList<>();
        for (int offset : QUEEN_DIAGONAL_MOVE_OFFSETS) {
            for (int i = 1; i <= 7; i++) {
                int moveCoordinate = coordinate + (offset * i); //the coordinate we're checking
                if(moveCoordinate < 0 || moveCoordinate > 63){ //ooBounds
                    i = 8;//stop looking in direction
                }
                else{
                    int columnOffset = Math.abs(board.getTiles()[coordinate].getColumn() - board.getTiles()[moveCoordinate].getColumn());
                    int rowOffset = Math.abs(board.getTiles()[coordinate].getRow() - board.getTiles()[moveCoordinate].getRow());
                    if( (columnOffset == rowOffset) ){
                        if(board.getTiles()[moveCoordinate].isOccupied()){ //if there's a piece we cannot go farther
                            if(board.getTiles()[moveCoordinate].getPiece().getColor() != this.getColor()){//take a piece
                                legalMoves.add(moveCoordinate);
                            }
                            i = 8;//stop looking in this direction
                        }
                        else{
                            legalMoves.add(moveCoordinate);
                        }                
                    }
                }
            }
        }
        for (int offset : QUEEN_AXIAL_MOVE_OFFSETS) {
            for (int i = 1; i <= 7; i++) {
                int moveCoordinate = coordinate + (offset * i); //the coordinate we're checking
                if(moveCoordinate < 0 || moveCoordinate > 63){ //ooBounds
                    i = 8;//stop looking in direction
                }
                else{
                    int columnOffset = Math.abs(board.getTiles()[coordinate].getColumn() - board.getTiles()[moveCoordinate].getColumn());
                    int rowOffset = Math.abs(board.getTiles()[coordinate].getRow() - board.getTiles()[moveCoordinate].getRow());
                    if( rowOffset == 0 || columnOffset == 0 ){
                        if(board.getTiles()[moveCoordinate].isOccupied()){ //if there's a piece we cannot go farther
                            if(board.getTiles()[moveCoordinate].getPiece().getColor() != this.getColor()){//take a piece
                                if(!legalMoves.contains(moveCoordinate)){
                                    legalMoves.add(moveCoordinate);
                                }
                                    
                            }
                            i = 8;//stop looking in this direction
                        }
                        else if(!legalMoves.contains(moveCoordinate)){
                            legalMoves.add(moveCoordinate);
                        }                
                    }
                }
            }
        }
        return legalMoves;
    }

    
}
