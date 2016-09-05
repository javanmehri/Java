package mos;

//==============================================================================
// File         : Knight.java
//
// Current Author: Mostafa Javanmehri
//
// Contact Info: m.javanmehri@gmail.com
//
// Purpose : It contains the codes needed for Knight pieces
//
// Dependencies: Piece
//
// Modification Log :
//    --> Created Jul-01-2016
//    --> Updated Aug-21-2016
//
// =============================================================================


public class Knight extends Piece {

    public Knight(Player player)
    {
        super(player);
        this.setPieceType(Type.KNIGHT);
    }


    @Override
    public boolean isValidMove(Board chessBoard, int toX, int toY) {
        if (super.isValidMove(chessBoard, toX,toY))
        {
            //...
            int fromX = this.getSpot().get_X();
            int fromY = this.getSpot().get_Y();

            if (Math.abs(toX-fromX)==1 && Math.abs(toY-fromY)==2)
            {
                return true;
            }
            if (Math.abs(toX-fromX)==2 && Math.abs(toY-fromY)==1)
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
