package Game;

import java.util.List;
import java.util.Scanner;

import Pieces.Knight;
import Pieces.None;
import Pieces.Piece;
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
        boolean whiteTurn = true;
        Scanner s = new Scanner(System.in);
        Board b = new Board();
        
        if(whiteTurn){
            System.out.println(b);
            System.out.println("White's Move");
            System.out.println("Type the coordinate of the piece you wish to move");
            int startCoord = s.nextInt();

            while(b.getTiles()[startCoord].getPiece().getColor() != Faction.WHITE || b.getTiles()[startCoord].getPiece().findLegalMoves(b).isEmpty()){
                System.out.println("White cannot move from that coordinate");
                System.out.println("Enter a valid coordinate:");
                startCoord = s.nextInt();
            }


            Piece movePiece = b.getTiles()[startCoord].getPiece();
            List<Integer> possibleMoves = movePiece.findLegalMoves(b);
            
            System.out.println("The piece you wish to move is: " + movePiece);
            System.out.println("This piece can move to the following coordinates: ");
            System.out.println(possibleMoves);

            System.out.println("Enter the coordinate to move to, or -1 to choose a different piece: ");
            int moveCoord = s.nextInt();

            while(moveCoord != -1 && !possibleMoves.contains(moveCoord)){
                System.out.println("That is not a valid move.");
                System.out.println("Enter the coordinate to move to, or -1 to choose a different piece: ");
                moveCoord = s.nextInt();
            }

            movePiece.makeMove(b, moveCoord);
            whiteTurn = false;
            System.out.println(b);
        }
        s.close();


        /*System.out.println(b);
        b.getTiles()[8].getPiece().makeMove(b, 18); //take black knight
        System.out.println(b);
        b.getTiles()[18].getPiece().makeMove(b, 35);
        System.out.println(b);*/
    }
}
