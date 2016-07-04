package mos;

/**
 * Created by A00990307 on 01/07/2016.
 */
public class King extends Piece {

    public King(Spot spot, int color)
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

            if (Math.abs(toX-fromX)==1)
            {
                return true;
            }
            if (Math.abs(toY-fromY)==1)
            {
                return true;
            }
        }
        return false;
    }
}
