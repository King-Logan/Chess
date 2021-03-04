package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Pieces.Empty;
import Pieces.Knight;
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
                tiles[i] = new Tile(i, new Empty(i));
            }
        }
    }

    public int stringToInt(String strCoord){
        if(strCoord.equals("-1")){
            return -1;
        }
        
        //charAt(0) is alphabet, so we need to subtract 97
        //charAt(1) is number, subtract - 49
        return  8 * ( (int) strCoord.toLowerCase().charAt(1) - 49) + 
                ((int) strCoord.toLowerCase().charAt(0) - 97);
    }

    public String intToString(int intCoord){
        
        String str = "";
        str += (char) (intCoord % 8 + 97);
        str += (char) (intCoord / 8 + 49);
        return str.toUpperCase();

    }

    public Tile[] getTiles() {
        return this.tiles;
    }

    public String toString() {
        String str = "-------------------------------------------------" + '\n';
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                str += "| " + tiles[8 * i + j].getPiece() + " ";
            }
            str += "| " + (i+1) + '\n' + "-------------------------------------------------" + '\n';
        }
        str += "|  A  |  B  |  C  |  D  |  E  |  F  |  G  |  H  |";
        return str;
    }

    public static void main(String[] args) {
        boolean whiteTurn = true;
        Scanner s = new Scanner(System.in);
        Board b = new Board();
        for (int i = 0; i < 20; i++) {
            while(whiteTurn){
                System.out.println(b);
                System.out.println("White's Move");
                System.out.print("Type the coordinate of the piece you wish to move: ");
                int startCoord = b.stringToInt(s.next());

                while(b.getTiles()[startCoord].getPiece().getColor() != Faction.WHITE || b.getTiles()[startCoord].getPiece().findLegalMoves(b).isEmpty()){
                    System.out.println("White cannot move from that coordinate");
                    System.out.print("Enter a valid coordinate:");
                    startCoord = b.stringToInt(s.next());
                }//ensures moveable piece
                

                Piece movePiece = b.getTiles()[startCoord].getPiece();
                List<Integer> possibleMovesInt = movePiece.findLegalMoves(b);
                List<String> possibleMovesString = new ArrayList<>(); 
                for (Integer move : possibleMovesInt) {
                    possibleMovesString.add(b.intToString(move));
                }    
                
                
                System.out.println("The piece you wish to move is: " + movePiece);
                System.out.println("This piece can move to the following coordinates: ");
                System.out.println(possibleMovesString);

                System.out.print("Enter the coordinate to move to, or -1 to choose a different piece: ");
                int moveCoord = b.stringToInt(s.next());

                while(moveCoord != -1 && !possibleMovesInt.contains(moveCoord)){
                    System.out.println("That is not a valid move.");
                    System.out.print("Enter the coordinate to move to, or -1 to choose a different piece: ");
                    moveCoord = b.stringToInt(s.next());
                }

                if(possibleMovesInt.contains(moveCoord)){//succesful move
                    movePiece.makeMove(b, moveCoord);
                    whiteTurn = false;
                }

            }
            
            while(!whiteTurn){
                whiteTurn = true;
            }
        }
        
        s.close();
       
    }
}
