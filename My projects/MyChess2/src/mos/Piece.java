package mos;

//==============================================================================
// File         : Piece.java
//
// Current Author: Mostafa Javanmehri
//
// Previous Author: None
//
// Contact Info: m.javanmehri@gmail.com
//
// Purpose : This contains the general class for chess pieces.
//
// Dependencies: None
//
// Modification Log :
//    --> Created july-01-2016 (fl)
//    --> Updated MMM-DD-YYYY (fl)
//
// =============================================================================

public class Piece {

    public static final int Black = 0;
    public static final int White = 1;
    public enum Type { PAWN , KING, QUEEN, KNIGHT, ROOK, BISHOP }

    private Type pieceType;
    private boolean available = true;
    private int color = Black;
    private Spot spot = null;

    public Piece(int color) {

        if (color == this.Black | color == this.White) {
            this.color = color;
        }
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setPieceType(Type type) { this.pieceType = type; }

    public Type getPieceType() { return this.pieceType; }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getColor(){ return this.color; }

    public Spot getSpot() {
        return spot;
    }

    private void setSpot(Spot spot) {
        this.spot = spot;
    }

    public  boolean isValidMove(Spot toSpot){
        int fromX = this.getSpot().get_Y();
        int fromY = this.getSpot().get_Y();
        int toX = toSpot.get_X();
        int toY = toSpot.get_Y();

        if(toX == fromX && toY == fromY)
            return false; //cannot move nothing
        if(toX < 0 || toX > 7 || fromX < 0 || fromX > 7 || toY < 0 || toY > 7 || fromY <0 || fromY > 7)
            return false;
        return true;
    }

    private void move(Spot fromSpot, Spot toSpot)
    {
        toSpot.occupy(fromSpot.releaseSpot());
    }

}


