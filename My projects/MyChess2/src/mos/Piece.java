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

    public final int Black = 0;
    public final int White = 1;

    private boolean available = true;
    private int color = Black;
    private Spot spot = null;

    public void Piece(Spot spot, int color, boolean available) {
        this.spot = spot;
        if (color == this.Black | color == this.White) {
            this.color = color;
        }
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

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
        int fromX = this.getSpot().getX();
        int fromY = this.getSpot().getY();
        int toX = toSpot.getX();
        int toY = toSpot.getY();

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


