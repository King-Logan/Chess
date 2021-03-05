package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Pieces.Bishop;
import Pieces.Empty;
import Pieces.King;
import Pieces.Knight;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;
import Pieces.Piece.Faction;

public class Board {
    Tile[] tiles;

    public Board() {
        tiles = new Tile[64];
        tiles[0] = new Tile(0, new Rook(Faction.WHITE, 0));
        tiles[7] = new Tile(7, new Rook(Faction.WHITE, 7));
        tiles[56] = new Tile(56, new Rook(Faction.BLACK, 56));
        tiles[63] = new Tile(63, new Rook(Faction.BLACK, 63));

        tiles[1] = new Tile(1, new Knight(Faction.WHITE, 1));
        tiles[6] = new Tile(6, new Knight(Faction.WHITE, 6));
        tiles[57] = new Tile(57, new Knight(Faction.BLACK, 57));
        tiles[62] = new Tile(62, new Knight(Faction.BLACK, 62));
        
        tiles[2] = new Tile(2, new Bishop(Faction.WHITE, 2));
        tiles[5] = new Tile(5, new Bishop(Faction.WHITE, 5));
        tiles[58] = new Tile(58, new Bishop(Faction.BLACK, 58));
        tiles[61] = new Tile(61, new Bishop(Faction.BLACK, 61));

        tiles[3] = new Tile(3, new Queen(Faction.WHITE, 3));
        tiles[59] = new Tile(59, new Queen(Faction.BLACK, 59));

        tiles[4] = new Tile(4, new King(Faction.WHITE, 4));
        tiles[60] = new Tile(60, new King(Faction.BLACK, 60));
        
        for (int i = 0; i < 64; i++) {
            if (tiles[i] == null){
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

    public List<Piece> getWhitePieces(){ //list of all active white pieces
        List<Piece> whitePieces = new ArrayList<>();
        for (Tile tile : this.tiles) {
            if( tile.getPiece().getColor() == Faction.WHITE){
                whitePieces.add(tile.getPiece());
            }
        }
        return whitePieces;
    }

    public List<Integer> getWhitePieceCoordinates(){ //list of coordinates of all active white pieces
        List<Integer> whitePieceCoordinates = new ArrayList<>();
        for (Piece piece : this.getWhitePieces()) {
            whitePieceCoordinates.add(piece.getCoordinate());
        }
        return whitePieceCoordinates;
    }

    public King getWhiteKing(){
        for( Piece piece : this.getWhitePieces()){
            if(piece.toString() == " " + ( (char) 9818) + " "){
                return (King) piece;
            }
        }
        return null;
    }
    
    public List<Piece> getBlackPieces(){ //list of all active black pieces
        List<Piece> blackPieces = new ArrayList<>();
        for (Tile tile : this.tiles) {
            if( tile.getPiece().getColor() == Faction.BLACK){
                blackPieces.add(tile.getPiece());
            }
        }
        return blackPieces;
    }

    public King getBlackKing(){
        for( Piece piece : this.getBlackPieces()){
            if(piece.toString() == " " + ( (char) 9812) + " "){
                return (King) piece;
            }
        }
        return null;
    }

    public List<Integer> getBlackPieceCoordinates(){ //list of coordinates of all active black pieces
        List<Integer> blackPieceCoordinates = new ArrayList<>();
        for (Piece piece : this.getBlackPieces()) {
            blackPieceCoordinates.add(piece.getCoordinate());
        }
        return blackPieceCoordinates;
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
        while(!b.getBlackKing().isCheckmate(b) && !b.getWhiteKing().isCheckmate(b)){

            while(whiteTurn){
                System.out.println(b);
                if(b.getWhiteKing().isCheck(b)){
                    System.out.println("YOU ARE IN CHECK");
                }
                System.out.println("White's Move");
                System.out.print("Type the coordinate of the piece you wish to move: ");
                int startCoord = b.stringToInt(s.next());

                while(!b.getWhitePieceCoordinates().contains(startCoord) || b.getTiles()[startCoord].getPiece().findLegalMoves(b).isEmpty()){
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
        if(b.getBlackKing().isCheckmate(b)){
            System.out.println("WHITE WINS");
        }
        else if(b.getWhiteKing().isCheckmate(b)){
            System.out.println("BLACK WINS");
        }
       
    }
}
