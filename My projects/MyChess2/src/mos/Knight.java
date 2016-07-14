package mos;

/**
 * Created by A00990307 on 01/07/2016.
 */
public class Knight extends Piece {

    public Knight(Color color)
    {
        super(color);
        this.setPieceType(Type.KNIGHT);
    }


    @Override
    public boolean isValidMove(int toX, int toY) {
        if (super.isValidMove(toX,toY))
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

}
