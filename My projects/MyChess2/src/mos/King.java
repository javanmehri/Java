package mos;

/**
 * Created by A00990307 on 01/07/2016.
 */
public class King extends Piece {

    public King(Color color)
    {
        super(color);
        this.setPieceType(Type.KING);
    }

    @Override
    public boolean isValidMove(Board chessBoard, int toX, int toY) {

        if (super.isValidMove(chessBoard, toX,toY))
        {
            //...
            int fromX = this.getSpot().get_X();
            int fromY = this.getSpot().get_Y();

            if (Math.abs(toX-fromX)==1 && Math.abs(toY-fromY)<=1)
            {
                return true;
            }
            if (Math.abs(toY-fromY)==1 && Math.abs(toX-fromX)<=1)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isValidRemove(Board chessBoard, int toX, int toY)
    {
        return false;
    }

}
