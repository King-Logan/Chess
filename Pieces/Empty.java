package Pieces;

import java.util.List;

import Game.Board;


//a piece represnting an empty tile
public class Empty extends Piece {

    public Empty(int coordinate) {
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
        return "   ";
    }
    
}
