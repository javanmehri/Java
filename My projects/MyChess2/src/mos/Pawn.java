package mos;

//==============================================================================
// File         : Pawn.java
//
// Current Author: Mostafa Javanmehri
//
// Contact Info: m.javanmehri@gmail.com
//
// Purpose : It contains the codes needed for Pawn pieces
//
// Dependencies: Piece
//
// Modification Log :
//    --> Created Jul-01-2016
//    --> Updated Aug-21-2016
//
// =============================================================================

public class Pawn extends Piece implements java.io.Serializable{


    public Pawn(Player player)
    {
        super(player);
        this.setPieceType(Type.PAWN);
    }


    @Override
    public boolean isValidMove(Board chessBoard, int toX, int toY) {
        int fromX = this.getSpot().get_X();
        int fromY = this.getSpot().get_Y();

        if (super.isValidMove(chessBoard, toX,toY))
        {
            if (getColor()==Color.WHITE)
            {
                if (isPlayed()) {
                    if (toY == fromY && (toX - fromX) == 1) {
                        return true;
                    }
                } else {
                    if (toY == fromY && ((toX - fromX) == 1 || (toX - fromX) == 2)) {
                        return true;
                    }

                }
            }

            else if (getColor() == Color.BLACK)
            {
                if (isPlayed())
                {
                    if (toY == fromY && (toX-fromX)==-1 )
                    {
                        return true;
                    }
                }
                else
                {
                    if (toY == fromY && ((toX-fromX)==-1 || (toX-fromX)==-2))
                    {
                        return true;
                    }
                }


            }

        }


        return false;
    }


    @Override
    public boolean isValidRemove(Board chessBoard, int toX, int toY)
    {
        if (chessBoard.getSpot(toX,toY).isOccupied())
        {
            if (chessBoard.getPieceOnTheSpot(toX,toY).getColor()!=this.getColor())
            {
                int fromX = this.getSpot().get_X();
                int fromY = this.getSpot().get_Y();

                if (getColor()==Color.WHITE)
                {
                        if (toX==fromX+1 && ((toY==fromY+1) || (toY==fromY-1)))
                            return true;
                }
                else
                {
                        if (toX==fromX-1 && ((toY==fromY+1) || (toY==fromY-1)))
                            return true;
                }
            }
        }
        return false;
    }



}
