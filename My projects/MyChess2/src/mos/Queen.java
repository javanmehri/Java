package mos;

/**
 * Created by A00990307 on 01/07/2016.
 */
public class Queen extends Piece {

    public Queen(Color color)
    {
        super(color);
        this.setPieceType(Type.QUEEN);
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
            if (toX == fromX && toY != fromY)
            {
                return true;
            }
            if (toX != fromX && toY == fromY)
            {
                return true;
            }

        }
        return false;
    }
}
