package Game;

import Pieces.Piece;
import Pieces.Piece.PieceType;

public class Tile {
    private int coordinate; //numerical value of tile
    private Piece piece;

    public Tile(int coordinate, Piece piece){
        this.coordinate = coordinate;
        this.piece = piece;
    }

    public boolean isOccupied(){
        if(!(this.getPiece().getType() == PieceType.NONE)){
            return true;
        }
        return false;
    }

    public int getCoordinate(){ //returns numerical value of tile (0-63)
        return this.coordinate;
    }

    public int getRow(){ //returns row of board
        return coordinate / 8; 
    }

    public int getColumn(){ //returns column of board
        return coordinate % 8;
    }

    public void setPiece(Piece piece){ //sets piece on tile
        this.piece = piece;
        piece.setCoordinate(this.coordinate); 
    }
    
    public Piece getPiece(){ //returns piece on tile
        return this.piece; 
    }
    
}
