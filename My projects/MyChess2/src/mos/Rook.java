package mos;

/**
 * Created by A00990307 on 01/07/2016.
 */
public class Rook extends Piece {

    public Rook(Color color)
    {
        super(color);
        this.setPieceType(Type.ROOK);
    }


    @Override
    public boolean isValidMove(int toX, int toY) {

        if (super.isValidMove(toX,toY))
        {
            //...
            int fromX = this.getSpot().get_X();
            int fromY = this.getSpot().get_Y();

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
