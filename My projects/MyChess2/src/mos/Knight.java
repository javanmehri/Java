package mos;

/**
 * Created by A00990307 on 01/07/2016.
 */
public class Knight extends Piece {

    @Override
    public boolean isValidMove(Spot toSpot) {
        if (super.isValidMove(toSpot))
        {
            //...
            int fromX = this.getSpot().getX();
            int fromY = this.getSpot().getY();
            int toX = toSpot.getX();
            int toY = toSpot.getY();

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
