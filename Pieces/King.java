package Pieces;

import Game.Board;
import Game.Tile;

public class King extends Piece {
    //TODO: add castling
    
    public King(boolean color){
        super(color);
    }
    
    
    public char getType() {
        return 'k';
    }

    public boolean canMove(Board board, Tile start, Tile end) {
        if( end.getPiece().getColor() == this.getColor() ){ //cant move to space with friendly piece
            return false;
        }
        else{
            int x = Math.abs(end.getRank() - start.getRank()); 
            int y = Math.abs(end.getFile() - start.getFile());
            
            if( x * y == 1 || x + y == 1 ){
                return kingSafe(board, end); 
            }
        }
        return false;
    }

}
