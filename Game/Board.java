package Game;

public class Board {
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
}

