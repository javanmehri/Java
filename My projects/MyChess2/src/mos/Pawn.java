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
    public boolean isValidMove(Board chessBoard, int toX, int toY) {
        int fromX = this.getSpot().get_X();
        int fromY = this.getSpot().get_Y();

        if (super.isValidMove(chessBoard, toX,toY))
        {
            if (getColor()==Color.WHITE)
            {
                if (isPlayed()) {
                    if (toY == fromY && (toX - fromX) == 1) {
                        return true;
                    }
                } else {
                    if (toY == fromY && ((toX - fromX) == 1 || (toX - fromX) == 2)) {
                        return true;
                    }

                }
            }

            else if (getColor() == Color.BLACK)
            {
                if (isPlayed())
                {
                    if (toY == fromY && (toX-fromX)==-1 )
                    {
                        return true;
                    }
                }
                else
                {
                    if (toY == fromY && ((toX-fromX)==-1 || (toX-fromX)==-2))
                    {
                        return true;
                    }
                }


            }

        }


        return false;
    }


    @Override
    public boolean isValidRemove(Board chessBoard, int toX, int toY)
    {
        if (chessBoard.getSpot(toX,toY).isOccupied())
        {
            if (chessBoard.getPieceOnTheSpot(toX,toY).getColor()!=this.getColor())
            {
                int fromX = this.getSpot().get_X();
                int fromY = this.getSpot().get_Y();

                if (getColor()==Color.WHITE)
                {
                        if (toX==fromX+1 && ((toY==fromY+1) || (toY==fromY-1)))
                            return true;
                }
                else
                {
                        if (toX==fromX-1 && ((toY==fromY+1) || (toY==fromY-1)))
                            return true;
                }
            }
        }
        return false;
    }



}
