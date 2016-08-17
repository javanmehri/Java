package mos;

/**
 * Created by A00990307 on 01/07/2016.
 */
public class Queen extends Piece {

    public Queen(Player player)
    {
        super(player);
        this.setPieceType(Type.QUEEN);
    }


    @Override
    public boolean isValidMove(Board chessBoard, int toX, int toY) {
        if (super.isValidMove(chessBoard, toX,toY))
        {
            //...
            int fromX = this.getSpot().get_X();
            int fromY = this.getSpot().get_Y();

            if (Math.abs(toX-fromX)== Math.abs(toY-fromY))
            {
                //System.out.println("1");
                return true;
            }
            if (toX == fromX && toY != fromY)
            {
                //System.out.println("2");
                return true;
            }
            if (toX != fromX && toY == fromY)
            {
                //System.out.println("3");
                return true;
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
                if (isValidMove(chessBoard,toX,toY))
                    return true;
            }
        }
        return false;
    }

}
