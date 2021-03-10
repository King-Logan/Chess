package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Pieces.Bishop;
import Pieces.Empty;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;
import Pieces.Piece.Faction;
import Pieces.Piece.PieceType;

public class Board {
    Tile[] tiles;

    public Board(boolean newBoard){
        tiles = new Tile[64];
        if( newBoard ){
            fillBoard();
        }
    }

    public void fillBoard() { //fills board with standard pieces
        
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

        for (int i = 8; i < 16; i++) {
            tiles[i] = new Tile(i, new Pawn(Faction.WHITE, i));
        }
        for (int i = 48; i < 56; i++) {
            tiles[i] = new Tile(i, new Pawn(Faction.BLACK, i));
        }
        
        for (int i = 0; i < 64; i++) {
            if (tiles[i] == null){
                tiles[i] = new Tile(i, new Empty(i));
            }
        }
    }

    public int stringToInt(String strCoord){
        if(strCoord.equals("-1") || strCoord.length() != 2){
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

    public void setTile(int index, Tile tile){
        this.tiles[index] = tile;
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
            if(piece.getType() == PieceType.KING){
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

    public List<Integer> getBlackPieceCoordinates(){ //list of coordinates of all active black pieces
        List<Integer> blackPieceCoordinates = new ArrayList<>();
        for (Piece piece : this.getBlackPieces()) {
            blackPieceCoordinates.add(piece.getCoordinate());
        }
        return blackPieceCoordinates;
    }
    
    public King getBlackKing(){
        for( Piece piece : this.getBlackPieces()){
            if(piece.getType() == PieceType.KING){
                return (King) piece;
            }
        }
        return null;
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

    public boolean isCheck(Faction color){ //determines if player is in check
        List<Piece> enemyPieces;
        King king;
        if(color == Faction.BLACK){
            enemyPieces = this.getWhitePieces();
            king = this.getBlackKing();
        }
        else{
            enemyPieces = this.getBlackPieces();
            king = this.getWhiteKing();
        }
        for (Piece piece : enemyPieces) {
            if(piece.findLegalMoves(this).contains(king.getCoordinate())){
                return true;
            }
        }
        
        return false;
    }

    
    public boolean isCheckmate(Faction color){ //check moves of allies to determine if lost
        List<Piece> allyPieces;
        if (color == Faction.BLACK){
            if(this.getBlackKing() == null){ //placeholder until prevent moving into check
                return true;
            }
            allyPieces = this.getBlackPieces();
           
        } else {
            if (this.getWhiteKing() == null){
                return true;
            }
            allyPieces = this.getWhitePieces();
        }
        for (Piece piece : allyPieces) {
            if(allyPieces.isEmpty()){
                return false;
            }
            else if(!piece.findLegalMoves(this).isEmpty()){
                return false;
            }
        }
        return true;
    }

    
    public void promotePawn(Piece pawn, String userIn){
        //user must only enter at least first character of piece
        if(userIn.toLowerCase().startsWith("k")){
            this.getTiles()[pawn.getCoordinate()].setPiece(new Knight(pawn.getColor(), pawn.getCoordinate()));
        }else if(userIn.toLowerCase().startsWith("b")){
            this.getTiles()[pawn.getCoordinate()].setPiece(new Bishop(pawn.getColor(), pawn.getCoordinate()));
        }else if(userIn.toLowerCase().startsWith("r")){
            this.getTiles()[pawn.getCoordinate()].setPiece(new Rook(pawn.getColor(), pawn.getCoordinate()));
        }else{
            this.getTiles()[pawn.getCoordinate()].setPiece(new Queen(pawn.getColor(), pawn.getCoordinate()));
        }
        pawn.setCoordinate(-1);
    }


    public static void main(String[] args) {
        boolean whiteTurn = true;
        Scanner s = new Scanner(System.in);
        Board b = new Board(true);
        while(!b.isCheckmate(Faction.BLACK) && !b.isCheckmate(Faction.WHITE)) { //while no player has lost

            while(whiteTurn){ //loop for white turn
                System.out.println(b);
                if(b.isCheck(Faction.WHITE)){
                    System.out.println("YOU ARE IN CHECK");
                }
                System.out.println("White's Move");
                System.out.print("Type the coordinate of the piece you wish to move: ");
                int startCoord = b.stringToInt(s.next()); //takes user input for piece to move

                while(!b.getWhitePieceCoordinates().contains(startCoord) || b.getTiles()[startCoord].getPiece().findLegalMoves(b).isEmpty()){//piece has to exist and be able to move from tile
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

                while(moveCoord != -1 && !possibleMovesInt.contains(moveCoord) && !movePiece.testSafeKingMove(b, moveCoord)){
                    System.out.println("That is not a valid move.");
                    System.out.print("Enter the coordinate to move to, or -1 to choose a different piece: ");
                    moveCoord = b.stringToInt(s.next());
                }

                if(possibleMovesInt.contains(moveCoord)){//legal move
                    movePiece.makeMove(b, moveCoord);
                    whiteTurn = false;
                    if(movePiece.getType() == PieceType.PAWN && moveCoord / 8 == 7){
                        System.out.println("Enter the type of piece to promote your pawn into");
                        System.out.println("Valid options are Queen, Rook, Bishop, or Knight");
                        b.promotePawn(movePiece, s.next());
                    }
                }
            }
            if(b.isCheckmate(Faction.BLACK)){
                break;
            }
            
            while(!whiteTurn){ //black turn
                System.out.println(b);
                if(b.isCheck(Faction.BLACK)){
                    System.out.println("YOU ARE IN CHECK");
                }
                System.out.println("Black's Move");
                System.out.print("Type the coordinate of the piece you wish to move: ");
                int startCoord = b.stringToInt(s.next());

                while(!b.getBlackPieceCoordinates().contains(startCoord) || b.getTiles()[startCoord].getPiece().findLegalMoves(b).isEmpty()){
                    System.out.println("Black cannot move from that coordinate");
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
                    whiteTurn = true;
                    if(movePiece.getType() == PieceType.PAWN && moveCoord / 8 == 0){
                        System.out.println("Enter the type of piece to promote your pawn into");
                        System.out.println("Valid options are Queen, Rook, Bishop, or Knight");
                        b.promotePawn(movePiece, s.next());
                    }
                }

            }
        }
        System.out.println(b);
        s.close();
        if(b.isCheckmate(Faction.BLACK)){
            System.out.println("WHITE WINS");
        }
        else if(b.isCheckmate(Faction.WHITE)){
            System.out.println("BLACK WINS");
        }
       
    }
}
