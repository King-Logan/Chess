package Pieces;

import Game.Board;

public class Move {
    private int startCoordinate;
    private int moveCoordinate;
    private Board board;

    public Move(int startCoordinate, int moveCoordinate, Board b){
        this.startCoordinate = startCoordinate;
        this.moveCoordinate = moveCoordinate;
        this.board = b;
    }

    void makeMove(){
        board.getTiles()[moveCoordinate].setPiece(board.getTiles()[startCoordinate].getPiece());        
        board.getTiles()[startCoordinate].setPiece(new None(startCoordinate));
    }

    public String toString() {
        return "" + moveCoordinate;    
    }

}
