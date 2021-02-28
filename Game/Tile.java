package Game;

import Pieces.Piece;

public class Tile {
    protected final int coordinate; //numerical value of tile
    private Piece piece;

    public Tile(final int coordinate, Piece piece){
        this.coordinate = coordinate;
        this.piece = piece;
    }

    public boolean isOccupied(){
        if(this.getPiece() != null){
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
    }
    
    public Piece getPiece(){ //returns piece on tile
        return this.piece; 
    }
    
    /*public static void main(String[] args) {
        Tile t = new Tile(15, null);
        System.out.println(t.getCoordinate());
        System.out.println(t.getRow());
        System.out.println(t.getColumn());
        System.out.println(t.isOccupied());
    }*/
}
