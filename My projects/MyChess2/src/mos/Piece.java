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

    public static enum Type { PAWN , KING, QUEEN, KNIGHT, ROOK, BISHOP }
    public static enum Color { WHITE, BLACK}

    private Type pieceType;
    private Color color;
    private boolean available = true;
    private Spot spot = null;

    public Piece(Color color) {

        this.color = color;
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

    public Color getColor(){ return this.color; }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
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


