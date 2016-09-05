package mos;

//==============================================================================
// File         : Bishop.java
//
// Current Author: Mostafa Javanmehri
//
// Contact Info: m.javanmehri@gmail.com
//
// Purpose : It contains the codes needed for Bishop pieces
//
// Dependencies: Piece
//
// Modification Log :
//    --> Created Jul-01-2016
//    --> Updated Aug-21-2016
//
// =============================================================================

public class Bishop extends Piece {

    public Bishop(Player player)
    {
        super(player);
        this.setPieceType(Type.BISHOP);
    }


    @Override
    public boolean isValidMove(Board chessBoard, int toX, int toY) {
        if (super.isValidMove(chessBoard, toX, toY))
        {
            //...
            int fromX = this.getSpot().get_X();
            int fromY = this.getSpot().get_Y();

            if (Math.abs(toX-fromX)== Math.abs(toY-fromY))
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
