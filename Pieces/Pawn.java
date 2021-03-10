package Pieces;

import java.util.ArrayList;
import java.util.List;

import Game.Board;

public class Pawn extends Piece {

    public Pawn(Faction color, int coordinate) {
        super(color, PieceType.PAWN, coordinate, true);
    }

    public String toString() {
        if(this.getColor() == Faction.WHITE){
            return " " + ( (char) 9817) + " ";
        }
        else{
            return " " + ( (char) 9823) + " ";
        }
    }

    public List<Integer> findLegalMoves(Board board) {
        List<Integer> legalMoves = new ArrayList<>();

        if(this.color == Faction.WHITE) { //moves positively
            if(coordinate + 8 <= 63 && !board.getTiles()[coordinate + 8].isOccupied()){//standard move
                legalMoves.add(coordinate + 8);
                if(isFirstMove){ //dont have to check + 16, will not have moved
                    if(!board.getTiles()[coordinate + 16].isOccupied()){
                        legalMoves.add(coordinate + 16); // 2 forwards move
                    }
                }
            }
            //attack moves
            if(coordinate + 7 <= 63 && coordinate % 8 != 0){//cannot make left diag attack if in left row
                if(board.getTiles()[coordinate + 7].getPiece().getColor() == Faction.BLACK){
                    legalMoves.add(coordinate + 7);
                }
            }
            if (coordinate + 9 <= 63 && coordinate % 8 != 7) {
                if(board.getTiles()[coordinate + 9].getPiece().getColor() == Faction.BLACK){
                    legalMoves.add(coordinate + 9);
                }

            }
        }
        
        if(this.color == Faction.BLACK) { //moves negatively
            if(coordinate - 8 >= 0 && !board.getTiles()[coordinate - 8].isOccupied()){//standard move
                legalMoves.add(coordinate - 8);
                if(isFirstMove){ //dont have to check - 16, will not have moved
                    if(!board.getTiles()[coordinate - 16].isOccupied()){
                        legalMoves.add(coordinate - 16); // 2 forwards move
                    }
                }
            }
            //attack moves
            if(coordinate - 7 >= 0 && coordinate % 8 != 0){//cannot make left diag attack if in left row
                if(board.getTiles()[coordinate - 7].getPiece().getColor() == Faction.WHITE){
                    legalMoves.add(coordinate - 7);
                }
            }
            if (coordinate - 9 >= 0 && coordinate % 8 != 7) {
                if(board.getTiles()[coordinate - 9].getPiece().getColor() == Faction.WHITE){
                    legalMoves.add(coordinate - 9);
                }

            }
        }


        return legalMoves;
    }
    
}
