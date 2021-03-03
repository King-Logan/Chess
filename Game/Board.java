package Game;

import Pieces.Knight;
import Pieces.None;
import Pieces.Piece.Faction;

public class Board {
    Tile[] tiles;

    public Board() {
        tiles = new Tile[64];
        for (int i = 0; i < 64; i++) {
            if (i == 8) {
                tiles[i] = new Tile(i, new Knight(Faction.WHITE, i));
            } else if (i == 18) {
                tiles[i] = new Tile(i, new Knight(Faction.BLACK, i));
            } else {
                tiles[i] = new Tile(i, new None(i));
            }
        }
    }


    public Tile[] getTiles() {
        return this.tiles;
    }

    public String toString() {
        String str = "---------------------------------------------------------" + '\n';
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                str += "| " + tiles[8 * i + j].getPiece() + " ";
            }
            str += "|" + '\n' + "---------------------------------------------------------" + '\n';
        }
        return str;
    }

    public static void main(String[] args) {
        Board b = new Board();
        System.out.println(b);
        b.getTiles()[8].getPiece().makeMove(b, 18); //take black knight
        System.out.println(b);
        b.getTiles()[18].getPiece().makeMove(b, 35);
        System.out.println(b);
    }
}
