package Game;

public class OldBoard {
    Tile[][] tiles;

    public Board(){
        this.resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
				tiles[i][j] = new Tile(i,j,null);
            }
        }
    }

    public Tile getTile(int rank, int file){
        return tiles[rank][file];
    }

    public Tile getWhiteKing(){
        for (Tile[] x : tiles) {
            for (Tile y : x) {
                if(y.getPiece().getColor() == false && y.getPiece().getType() == 'k'){
                    return y;
                }                
            }
        }
        return null; //SHOULD NEVER HAPPEN
    }
    public Tile getBlackKing(){
        for (Tile[] x : tiles) {
            for (Tile y : x) {
                if(y.getPiece().getColor() && y.getPiece().getType() == 'k'){
                    return y;
                }                
            }
        }
        return null; //SHOULD NEVER HAPPEN
    }
}



