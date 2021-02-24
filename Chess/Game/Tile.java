package Chess.Game;
import Chess.Pieces.Piece;

public class Tile {
    private boolean color = false;  //True = Black, False = White
    private int rank, file;         //location of tile
    private Piece piece;            //piece on tile

    public Tile(int rank, int file, Piece piece){ //constructor
        this.setRank(rank);
        this.setFile(file);
        this.setPiece(piece);
        if( (rank + file) % 2 == 0 ){
            this.setColor(true);
        }
    }

    //getters and setters
    public void setColor(boolean color){ this.color = color; }
    
    public boolean getColor(){ return this.color; }

    public void setRank(int rank){ this.rank = rank; }
    
    public int getRank(){ return this.rank; }

    public void setFile(int file){ this.file = file; }
    
    public int getFile(){ return this.file; }

    public void setPiece(Piece piece){ this.piece = piece; }
    
    public Piece getPiece(){ return this.piece; }

}
