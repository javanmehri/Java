package mos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by A00990307 on 01/07/2016.
 */

// updated: Aug 23

public class Spot implements java.io.Serializable {

    private int x;
    private int y;
    private Piece piece;

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


    @Override
    protected Spot clone() throws CloneNotSupportedException {
        Spot spot = new Spot(this.get_X(), this.get_Y());
        spot.occupy(this.getPiece());

        return spot;
    }


    public String toString()
    {
        int i = this.get_X();
        int j = this.get_Y();
        String str="";

        switch (j)
        {
            case 0: str = "A"; break;
            case 1: str = "B"; break;
            case 2: str = "C"; break;
            case 3: str = "D"; break;
            case 4: str = "E"; break;
            case 5: str = "F"; break;
            case 6: str = "G"; break;
            case 7: str = "H"; break;
        }
        str = str + (i+1);

        return str;
    }
}