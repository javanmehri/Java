package mos;

/**
 * Created by A00990307 on 01/07/2016.
 */
public class Bishop extends Piece {

    public Bishop(Color color)
    {
        super(color);
        this.setPieceType(Type.BISHOP);
    }


    @Override
    public boolean isValidMove(int toX, int toY) {
        if (super.isValidMove(toX, toY))
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

}
