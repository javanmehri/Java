package mos;

// Utility

import javafx.event.Event;
import javafx.scene.Cursor;
import javafx.scene.image.*;
import javafx.scene.image.ImageView;

/**
 * Created by MOS on 2016-08-19.
 */
public class Utl implements java.io.Serializable {


    public static boolean isCheck(Game game) {

        Player player = game.getUnactivePlayer();
        Piece[] pieces = player.getAllPieces();
        Piece[] validRemoves;

        for (int i=0; i<16; i++)
        {
            if (pieces[i].isAvailable())
            {
                validRemoves = getAllValidRemoves(game, pieces[i]);
                for (int k=0; k<validRemoves.length; k++)
                {
                    if (validRemoves[k]!=null)
                    {
                        if (validRemoves[k].getPieceType()== Piece.Type.KING)
                        {
                            game.checker = pieces[i];
                            game.makeComment(" ! CHECK by "+game.checker.getColor()+" "+game.checker.getPieceType()+" !");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    private static Piece[] getAllValidRemoves(Game game, Piece piece) {

        Board board = game.getBoard();
        Piece[] validRemoves = new Piece[32];

        Spot[] allSpots = board.getAllSpots();
        Spot toSpot;

        int from_i = piece.getSpot().get_X();
        int from_j = piece.getSpot().get_Y();

        int to_i;
        int to_j;

        int counter = 0;

        for (int i=0; i<64; i++)
        {
            toSpot = allSpots[i];
            to_i = toSpot.get_X();
            to_j = toSpot.get_Y();
            if (!isAnyPieceOnTheWay(game, from_i,from_j,to_i,to_j) && isValidRemove(game, from_i,from_j,to_i,to_j))
            {
                validRemoves[counter] = board.getSpot(to_i,to_j).getPiece();
                counter ++;
            }
        }


        return validRemoves;
    }


    private static boolean isAnyPieceOnTheWay(Game game, int from_i, int from_j, int to_i, int to_j) {

        int delta_i = Math.abs(from_i - to_i);
        int delta_j = Math.abs(from_j - to_j);
        int max_delta = Math.max(delta_i, delta_j);

        if (max_delta>1)
        {
            Spot[] spots = getSpotsOnTheWay(game, from_i, from_j, to_i, to_j);
            for (int i = 0; i<max_delta-1; i++)
            {
                if (spots[i]!=null)
                    if (spots[i].isOccupied())
                        return true;
            }
        }

        return false;
    }


    public static boolean isValidRemove(Game game, int from_i, int from_j, int to_i, int to_j) {

        Board board = game.getBoard();

        if (game.getPlayer(from_i,from_j)==game.getPlayer(to_i,to_j))
            return false;

        else if (board.getPieceOnTheSpot(from_i,from_j).isValidRemove(board,to_i,to_j)==false || isAnyPieceOnTheWay(game, from_i,from_j,to_i,to_j))
            return false;

        else
            return true;

    }


    private static Spot[] getSpotsOnTheWay(Game game, int from_i, int from_j, int to_i, int to_j) {
        Board board = game.getBoard();
        Spot[] spots = new Spot[6];
        int delta_i = Math.abs(to_i - from_i);
        int delta_j = Math.abs(to_j - from_j);
        int min_i = Math.min(from_i , to_i);
        int min_j = Math.min(from_j , to_j);

        //if (!isValidMove(from_i,from_j,to_i,to_j) || !isValidRemove(from_i,from_j,to_i,to_j) )
        //return spots;

        if (delta_j == 0)
        {
            if (delta_i > 1)
            {
                for (int k=0; k < delta_i-1; k++)
                {
                    spots[k] = board.getSpot(min_i+k+1, from_j);
                }
            }
        }

        else if (delta_i == 0)
        {
            if (delta_j > 1)
            {
                for (int k=0; k < delta_j-1; k++)
                {
                    spots[k] = board.getSpot(from_i, min_j+k+1);
                }
            }
        }

        else if (delta_i == delta_j)
        {
            // Report.report(" delta_i = delta_j ");
            if (delta_i > 1)
            {
                for (int k=0; k<delta_i-1; k++)
                {
                    if ((to_i-from_i)>0 && (to_j-from_j)>0)
                    {
                        spots[k] = board.getSpot(from_i+(k+1),from_j+(k+1));
                    }
                    else if ((to_i-from_i)>0 && (to_j-from_j)<0)
                    {
                        spots[k] = board.getSpot(from_i+(k+1),from_j-(k+1));
                        //Report.report(" i="+(from_i+(k+1))+" j="+(from_j-(k+1)));
                    }
                    else if ((to_i-from_i)<0 && (to_j-from_j)>0)
                    {
                        spots[k] = board.getSpot(from_i-(k+1),from_j+(k+1));
                    }
                    else if ((to_i-from_i)<0 && (to_j-from_j)<0)
                    {
                        spots[k] = board.getSpot(from_i-(k+1),from_j-(k+1));
                    }
                }
            }
        }

        //Report.report(spots);
        return spots;
    }




    public static Spot getSpot(Event event)
    {
        javafx.scene.image.ImageView imageView = getImageView(event);
        int i = get_XIndex(imageView);
        int j = get_YIndex(imageView);
        return GameManager.getTheGame().getBoard().getSpot(i,j);
    }

    public static int get_XIndex(javafx.scene.image.ImageView imageView) {
        javafx.scene.image.ImageView[][] imageViewsBoard = MainStageController.getImageViewsBoard();
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (imageViewsBoard[i][j].equals(imageView)) {
                    return i;
                }

        return -1;
    }


    public static int get_YIndex(javafx.scene.image.ImageView imageView) {
        ImageView[][] imageViewsBoard = MainStageController.getImageViewsBoard();
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (imageViewsBoard[i][j].equals(imageView)) {
                    return j;
                }

        return -1;
    }

    public static ImageView getImageViewOfSpot(Spot spot) {
        int x = spot.get_X();
        int y = spot.get_Y();
        return MainStageController.getImageViewsBoard()[x][y];
    }

    public static ImageView getImageView(Event event) {
        return (ImageView) event.getSource();
    }

    public static Piece getPiece(Board board, Event event)
    {
        ImageView imageView = getImageView(event);
        int i = get_XIndex(imageView);
        int j = get_YIndex(imageView);
        Spot spot = board.getSpot(i,j);
        Piece piece = null;

        if (spot.isOccupied())
            piece = spot.getPiece();

        return piece;
    }

    public static Piece getPiece(Board board, ImageView imageView)
    {
        int i = get_XIndex(imageView);
        int j = get_YIndex(imageView);
        Spot spot = board.getSpot(i,j);
        Piece piece = null;

        if (spot.isOccupied())
            piece = spot.getPiece();

        return piece;
    }

    public static Piece.Color getColorOfPiece(ImageView imageView) {
        ImageView[][] imageViewsBoard = MainStageController.getImageViewsBoard();

        int i = get_XIndex(imageView);
        int j = get_YIndex(imageView);
        Game game = GameManager.getTheGame();
        Piece.Color color = game.getBoard().getSpot(i, j).getPiece().getColor();

        return color;
    }


    public static boolean isActivePlayer(Event event)
    {
        if (!isTileEmpty(event))
        {
            Piece.Color color = getColorOfPiece(Utl.getImageView(event));
            Player player;
            if (color== Piece.Color.WHITE)
                player = GameManager.getTheGame().getPlayer_White();
            else
                player = GameManager.getTheGame().getPlayer_Black();

            return GameManager.getTheGame().isActivePlayer(player);
        }
        return false;
    }

    private static boolean isTileEmpty(Event event)
    {
        ImageView imageView = Utl.getImageView(event);
        return isImageViewEmpty(imageView);
    }

    public static boolean isImageViewEmpty(ImageView imageView) {
        if (imageView.getImage() == Img.Tile_Black || imageView.getImage() == Img.Tile_White)
            return true;
        return false;
    }


    public static Piece.Color getBackgroundColor(int i, int j) {

        Piece.Color backgroundColor = Piece.Color.WHITE;
        if ((i + j) % 2 == 0)
            backgroundColor = Piece.Color.BLACK;
        return backgroundColor;

    }

    public static void changeCursorToNormal(Event event) {
        getImageView(event).setCursor(Cursor.DEFAULT);
    }

    public static void changeCursorToHand(Event event) {
        getImageView(event).setCursor(Cursor.HAND);
    }




}
