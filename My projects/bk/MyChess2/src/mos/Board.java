package mos;

//==============================================================================
// File         : Board.java
//
// Current Author: Mostafa Javanmehri
//
// Contact Info: m.javanmehri@gmail.com
//
// Purpose : It creates an 8x8 board of Spots, and contains some necessary methods to
// handle the board.
//
// Dependencies: Spot , Piece
//
// Modification Log :
//    --> Created Jul-03-2016
//    --> Updated Aug-21-2016
//
// =============================================================================


public class Board {

    private Spot[][] spots = new Spot[8][8];

    public enum Direction {UP, DOWN, RIGHT, LEFT}


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


    public Board clone() throws CloneNotSupportedException {
        Board newBoard = new Board();

        for(int i=0; i<spots.length; i++)
        {
            for(int j=0; j<spots.length; j++)
            {
                newBoard.spots[i][j] = this.getSpot(i,j).clone();
            }
        }

        return newBoard;
    }

    public void movePiceOneTile(Piece piece, Direction direct)
    {

        int from_i = piece.getSpot().get_X();
        int from_j = piece.getSpot().get_Y();

        switch (direct)
        {
            case RIGHT:
            {
                removePiece(from_i, from_j);
                occupySpot(piece, from_i, from_j+1);
                break;
            }
            case LEFT:
            {
                removePiece(from_i, from_j);
                occupySpot(piece, from_i, from_j-1);
                break;
            }

        }

    }



}
