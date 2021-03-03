package Pieces;

import java.util.List;

import Game.Board;

public class None extends Piece {

    public None(int coordinate) {
        super(Faction.NONE, PieceType.NONE, coordinate, true);
    }
    public Faction getColor(){
        return Faction.NONE;
    }
    @Override
    public List<Integer> findLegalMoves(Board board) {
        return null;
    }

    @Override
    public void makeMove(Board board, int moveCoordinate) {
    }

    @Override
    public String toString() {
        return "    ";
    }
    
}
