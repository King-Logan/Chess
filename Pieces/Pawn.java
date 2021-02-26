package Pieces;

import Game.Board;
import Game.Tile;

public class Pawn extends Piece {
    private boolean hasMoved;

    // create a pawn of color
    public Pawn(boolean color) {
        super(color);
        setHasMoved(false); 
    }

    public char getType(){
        return 'p';
    }

    public boolean getHasMoved(){ 
        return this.hasMoved; 
    } 
    
    public void setHasMoved(boolean b) { 
        this.hasMoved = b; 
    }

    public boolean canMove(Board board, Tile start, Tile end) {
        int x = end.getRank() - start.getRank(); //vector of movement in x direction
        int y = end.getFile() - start.getFile(); //vector of movement in y direction
        
        //white pawns
        if(getColor() == false){
            //2 space first move
            if( y == 2 && x == 0 && getHasMoved() == false){ 
                return kingSafe(board, board.getWhiteKing()); 
            }
            //standard move
            else if( y == 1 && x == 0 && end.getPiece() == null ){
                return kingSafe(board, board.getWhiteKing()); 
            }
            //standard attack 
            else if( y == 1 && Math.abs(x) == 1 && end.getPiece().getColor() == true){
                return kingSafe(board, board.getWhiteKing());
            }
        } 
        //black pawns
        if(getColor() == true){
            //2 space first move
            if( y == -2 && x == 0 && getHasMoved() == false){ 
                return kingSafe(board, board.getBlackKing()); 
            }
            //standard move
            else if( y == -1 && x == 0 && end.getPiece() == null ){
                return kingSafe(board, board.getBlackKing()); 
            }
            //standard attack 
            else if( y == -1 && Math.abs(x) == 1 && end.getPiece().getColor() == false){
                return kingSafe(board, board.getBlackKing()); 
            }
        }
        
        return false;    
    }
    

}
