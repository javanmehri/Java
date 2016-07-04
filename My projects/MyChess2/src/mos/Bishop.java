package mos;

/**
 * Created by A00990307 on 01/07/2016.
 */
public class Bishop extends Piece {

    public Bishop(Spot spot, int color)
    {
        super(spot, color);
    }


    @Override
    public boolean isValidMove(Spot toSpot) {
        if (super.isValidMove(toSpot))
        {
            //...
            int fromX = this.getSpot().get_X();
            int fromY = this.getSpot().get_Y();
            int toX = toSpot.get_X();
            int toY = toSpot.get_Y();

            if (Math.abs(toX-fromX)== Math.abs(toY-fromY))
            {
                return true;
            }
        }
        return false;
    }

}
