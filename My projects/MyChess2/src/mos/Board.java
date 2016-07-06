package mos;

/**
 * Created by MOS on 2016-07-03.
 */
public class Board {

    private Spot[][] spots = new Spot[8][8];

    public Board() {

        for(int i=0; i<spots.length; i++){

            for(int j=0; j<spots.length; j++){

                this.spots[i][j] = new Spot(i, j);

            }
        }
    }

    public Spot getSpot(int x, int y) {
        return spots[x][y];
    }


    public void occupySpot(Piece piece, int i, int j)

    {
        Spot spot = getSpot(i, j);
        spot.occupy(piece);
        piece.setSpot(spot);
    }

    public Piece removePiece(int i, int j)
    {
        Spot spot = this.getSpot(i,j);
        Piece piece = spot.getPiece();
        spot.occupy(null);
        return piece;
    }

}
