package Pieces;

import java.util.Collection;

import Game.Board;

public class None extends Piece {

    public None(int coordinate) {
        super(Faction.NONE, PieceType.NONE, coordinate, true);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Collection<Move> findLegalMoves(Board board) {
        return null;
    }

    @Override
    public void makeMove(Move move) {
        
    }

    @Override
    public String toString() {
        return "    ";
    }
    
}
