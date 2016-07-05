package mos;

/**
 * Created by A00990307 on 01/07/2016.
 */
public class Pawn extends Piece {

    public Pawn(Color color)
    {
        super(color);
        this.setPieceType(Type.PAWN);
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

            if (toX == fromX && (toY-fromY)==1)
            {
                return true;
            }
        }
        return false;
    }

}
