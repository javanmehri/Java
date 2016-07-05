package mos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by A00990307 on 01/07/2016.
 */
public class Spot {

    private int x;
    private int y;
    Piece piece;

    public Spot(int x, int y) {
        this.x = x;
        this.y = y;
        this.piece = null;
    }

    public int get_X()
    {
        return this.x;
    }

    public int get_Y()
    {
        return this.y;
    }

    public Piece getPiece() { return piece;}

    public void occupy(Piece piece){
        //if piece already here, delete it, i. e. set it dead
        /*
        if(this.piece != null && this.piece.getColor()!= piece.getColor()) {
            this.piece.setAvailable(false);
            //place piece here
            this.piece = piece;
        }
        */
        this.piece = piece;
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