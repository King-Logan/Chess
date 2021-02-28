package Game;

import Pieces.Knight;
import Pieces.Move;
import Pieces.None;
import Pieces.Piece.Faction;

public class Board {
    Tile[] tiles;
    
    public Board(){
        this.resetBoard();
    }

    private void resetBoard(){
        tiles = new Tile[64];
        for (int i = 0; i < 64; i++) {
            if( i % 3 == 0){
                tiles[i] = new Tile(i, new Knight(Faction.WHITE, i));
            }
            else{ 
                tiles[i] = new Tile(i, new None(i));
            }
        }
    }

    public Tile[] getTiles(){
        return this.tiles;
    }

    public String toString() {
        String str = "---------------------------------------------------------" + '\n';
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                str += "| " + tiles[8*i+j].getPiece() + " ";
            }
            str += "|" + '\n' + "---------------------------------------------------------" +'\n';
        }
        return str;
    }

    public static void main(String[] args) {
        Board b = new Board();
        System.out.println(b);
        System.out.println(b.getTiles()[3].getPiece().findLegalMoves(b));
        b.getTiles()[3].getPiece().makeMove(new Move(3, 13, b));
        b.getTiles()[3].getPiece().makeMove(new Move(13, 4, b));
        b.getTiles()[3].getPiece().makeMove(new Move(3, 21, b));
        System.out.println(b);
    }
}
