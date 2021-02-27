package Pieces;

import Game.Board;
import Game.Tile;

public abstract class OGPiece{
    private boolean color; //true = black, false = white
    private boolean status; //true = alive, false = dead

    //constructor creates alive piece of given color
    public Piece(boolean color){
        this.setColor(color);
        this.setStatus(true);
    }

    
    public boolean isAlive(){ 
        return this.status; 
    }
    
    public void setStatus(boolean status){ 
        this.status = status; 
    }
    
    public boolean getColor(){ 
        return this.color; 
    }
    
    public void setColor(boolean color){ 
        this.color = color;
    }
    
    public abstract boolean canMove(Board board, Tile start, Tile end);
    
    public abstract char getType();
    

    public boolean kingSafe(Board board, Tile king){
        if(king.getPiece().getColor()){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if( board.getTile(i, j).getPiece().getColor() != false &&                       //if opposite color
                        board.getTile(i, j).getPiece().canMove(board, board.getTile(i, j), king) && //and can attack king
                        board.getTile(i,j).getPiece().isAlive()){                                   //and is alive
                        return false;                                                               //king is not safe
                    }
                }
            }
        }else{
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if( board.getTile(i, j).getPiece().getColor() != true &&                        //if opposite color
                        board.getTile(i, j).getPiece().canMove(board, board.getTile(i, j), king) && //and can attack king
                        board.getTile(i,j).getPiece().isAlive()){                                   //and is alive
                        return false;                                                               //king is not safe
                    }
                }
            }
        }
        return true;
    }
}

