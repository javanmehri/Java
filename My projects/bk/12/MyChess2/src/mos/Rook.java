package mos;

//==============================================================================
// File         : Rook.java
//
// Current Author: Mostafa Javanmehri
//
// Contact Info: m.javanmehri@gmail.com
//
// Purpose : It contains the codes needed for Rook pieces
//
// Dependencies: Piece
//
// Modification Log :
//    --> Created Jul-01-2016
//    --> Updated Aug-21-2016
//
// =============================================================================

public class Rook extends Piece implements java.io.Serializable {

    public Rook(Player player)
    {
        super(player);
        this.setPieceType(Type.ROOK);
    }


    @Override
    public boolean isValidMove(Board chessBoard, int toX, int toY) {

        if (super.isValidMove(chessBoard, toX,toY))
        {
            //...
            int fromX = this.getSpot().get_X();
            int fromY = this.getSpot().get_Y();

            if (toX == fromX && toY != fromY)
            {
                return true;
            }
            if (toX != fromX && toY == fromY)
            {
                return true;
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
                if (isValidMove(chessBoard,toX,toY))
                    return true;
            }
        }
        return false;
    }


}
