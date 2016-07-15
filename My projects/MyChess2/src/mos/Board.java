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

    public static Spot[] getSpotsOnTheWay(Board board, int from_i, int from_j, int to_i, int to_j)
    {
        Spot[] spots = new Spot[6];
        //board.getPieceOnTheSpot()
        if (from_i == to_i)
        {
            int delta = Math.abs(from_j - to_j);
            for (int k=0; k < delta; k++)
            {
                int min_j = Math.min(from_j , to_j);
                int max_j = Math.max(from_j , to_j);
                spots[k] = board.getSpot(from_i, min_j+k+1);
            }
        }
        return spots;
    }

    public static boolean isAnyPieceOnTheWay(Board board, int from_i, int from_j, int to_i, int to_j)
    {

        Spot[] spots = getSpotsOnTheWay(board,from_i, from_j, to_i, to_j);
        for (int i = 0; i<7; i++)
        {
            if (spots[i].isOccupied())
                return true;
        }

        return false;
    }
}
