package mos;

/**
 * Created by A00990307 on 01/07/2016.
 */
public class Pawn extends Piece {


    public Pawn(Color color)
    {
        super(color);
        this.setPieceType(Type.PAWN);
        wasMoved = false;
    }


    @Override
    public boolean isValidMove(Board chessBoard, int toX, int toY) {
        int fromX = this.getSpot().get_X();
        int fromY = this.getSpot().get_Y();

        if (super.isValidMove(chessBoard, toX,toY))
        {
            //...

            if (wasMoved)
            {
                if (toY == fromY && Math.abs(toX-fromX)==1 )
                {
                    //wasMoved = true;
                    return true;
                }
            }
            else
            {
                if (toY == fromY && (Math.abs(toX-fromX)==1 || Math.abs(toX-fromX)==2))
                {
                    //wasMoved = true;
                    return true;
                }

            }

        }


        //System.out.println(" Pawn isValideMove -> false");
        //System.out.println(" toX: "+toX);
        //System.out.println(" fromX: "+fromX);
        //System.out.println(" toY: "+toY);
        //System.out.println(" fromY: "+fromY);



        return false;
    }



}
