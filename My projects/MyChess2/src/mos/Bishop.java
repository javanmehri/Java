package mos;

/**
 * Created by A00990307 on 01/07/2016.
 */
public class Bishop extends Piece {

    @Override
    public boolean isValidMove(Spot toSpot) {
        if (super.isValidMove(toSpot))
        {
            //...
            int fromX = this.getSpot().getX();
            int fromY = this.getSpot().getY();
            int toX = toSpot.getX();
            int toY = toSpot.getY();

            if (Math.abs(toX-fromX)== Math.abs(toY-fromY))
            {
                return true;
            }
        }
        return false;
    }

}
