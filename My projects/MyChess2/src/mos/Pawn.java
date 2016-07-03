package mos;

/**
 * Created by A00990307 on 01/07/2016.
 */
public class Pawn extends Piece {

    @Override
    public boolean isValidMove(Spot toSpot) {

        if (super.isValidMove(toSpot))
        {
            //...
            int fromX = this.getSpot().getX();
            int fromY = this.getSpot().getY();
            int toX = toSpot.getX();
            int toY = toSpot.getY();

            if (toX == fromX && (toY-fromY)==1)
            {
                return true;
            }
        }
        return false;
    }

}
