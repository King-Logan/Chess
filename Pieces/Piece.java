package Pieces;

public abstract class Piece {
    protected final Faction color;
    protected final PieceType pieceType;

    public Piece(final Faction color, final PieceType pieceType){
        this.color = color;
        this.pieceType = pieceType;
    }

}
