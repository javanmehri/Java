package mos;

/**
 * Created by A00990307 on 01/07/2016.
 */
public class Spot {
    private int x;
    private int y;
    Piece piece;

    public void Spot(Piece piece, int x, int y) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public void occupy(Piece piece){
        //if piece already here, delete it, i. e. set it dead
        if(this.piece != null && this.piece.getColor()!= piece.getColor()) {
            this.piece.setAvailable(false);
            //place piece here
            this.piece = piece;
        }
    }

    public boolean isOccupied() {
        if(piece != null)
            return true;
        return false;
    }

    public Piece releaseSpot() {
        Piece releasedPiece = this.piece;
        this.piece = null;
        return releasedPiece;
    }

}