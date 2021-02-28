package Pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Game.Board;

public class Knight extends Piece{

    private int[] KNIGHT_MOVE_OFFSETS = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(Faction color, int coordinate) { 
        super(color, PieceType.KNIGHT, coordinate, true);
    }

    public String toString() {
        return " " + this.color.name().charAt(0) + "N ";
    }

    public Collection<Move> findLegalMoves(Board board){
        List<Move> legalMoves = new ArrayList<>();
        for (int offset : KNIGHT_MOVE_OFFSETS) {
            int moveCoordinate = coordinate + offset;
            if(moveCoordinate >= 0 && moveCoordinate <= 63){ //not out of bounds
                if(board.getTiles()[moveCoordinate].getPiece() == null){
                    legalMoves.add(new Move(coordinate, moveCoordinate, board));
                }
                else if(board.getTiles()[moveCoordinate].getPiece().color != this.color){
                    legalMoves.add(new Move(coordinate, moveCoordinate, board));
                }
            }
        }
        return legalMoves;
    } 
    
    public void makeMove(Move move){
        move.makeMove();
    }
}
