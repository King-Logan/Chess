package Pieces;

import Game.Board;
import Game.Tile;

public class OldKing extends Piece {
    
    public King(boolean color){
        super(color);
    }

    public boolean canMove(Board board, Tile start, Tile end) {
        return false;
    }

}
