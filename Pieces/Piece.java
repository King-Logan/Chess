package Pieces;

import Game.Board;
import Game.Tile;

public abstract class Piece{
    private boolean color; //true = black, false = white
    private boolean status; //true = alive, false = dead

    //constructor creates alive piece of given color
    public Piece(boolean color){
        this.setColor(color);
        this.setStatus(true);
    }

    
    public boolean getStatus(){ 
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

    public abstract boolean kingSafe(Board board);
        /*  check each enemy piece,
            if they can attack king after a move - false
            else true */
}