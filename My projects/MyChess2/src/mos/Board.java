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

    public Piece getPieceOnTheSpot(int x, int y)
    {
        return getSpot(x, y).getPiece();
    }


    public void occupySpot(Piece piece, int i, int j)

    {
        Spot spot = getSpot(i, j);
        spot.occupy(piece);
        piece.setSpot(spot);
    }

    public void removePiece(int i, int j)
    {
        getSpot(i,j).occupy(null);

    }

    public Spot[] getAllSpots()
    {
        Spot[] allSpots = new Spot[64];
        for (int i = 0; i<8; i++)
        {
            for (int j = 0; j<8; j++)
            {
                allSpots[8*i+j] = spots[i][j];
            }
        }
        return allSpots;
    }


}
