package mos;

import javafx.event.Event;
import javafx.scene.image.ImageView;

import java.io.IOException;

/**
 * Created by MOS on 2016-07-12.
 */
public class ScreenBoard {

    private static boolean isAnyPieceSelected = false;
    private static ImageView selectedPiece;
    private static ImageView moveToSopt;


    // =============================================================
    //                    Helper Methods
    // =============================================================

    public static void clickOnBoard(Event event) throws IOException
    {
        ImageView clickedTile = (ImageView)event.getSource();

        if (!isAnyPieceSelected && !isImageViewEmpty(clickedTile))
            select(event);

        else if (isAnyPieceSelected)
        {
            if (selectedPiece == clickedTile)
                de_select();

            else if (isImageViewEmpty(clickedTile))
                move(event);
        }
        updateTheChessBoard();
    }


    public static void updateTheChessBoard()
    {
        Spot spot;
        Piece piece;
        ImageView imageViewOfSpot;
        Piece.Type type;
        Piece.Color pieceColor;
        Piece.Color backgroundColor;

        Game game = GameManager.getTheGame();

        for (int i = 0; i<8; i++)
        {
            for (int j = 0; j<8; j++)
            {
                spot = game.getBoard().getSpot(i,j);
                imageViewOfSpot = getImageViewOfSpot(spot);
                backgroundColor = getBackgroundColor(i,j);

                if (spot.isOccupied())
                {
                    piece = spot.getPiece();
                    type = piece.getPieceType();
                    pieceColor = piece.getColor();

                    updateTheTile(imageViewOfSpot, type, pieceColor, backgroundColor);

                }
                else
                {
                    if(backgroundColor== Piece.Color.BLACK)
                        imageViewOfSpot.setImage(Img.Tile_Black);
                    else
                        imageViewOfSpot.setImage(Img.Tile_White);
                }

            }
        }


    }

    // =============================================================
    //                    Helper Methods
    // =============================================================
    private static void select(Event event)
    {
        selectedPiece = (ImageView)event.getSource();
        selectedPiece.setOpacity(0.5);
        isAnyPieceSelected = true;
    }

    private static void de_select()
    {
        selectedPiece.setOpacity(1);
        isAnyPieceSelected = false;
    }

    private static void move(Event event) throws IOException {

            Game game = GameManager.getTheGame();

            Piece.Color color = getColorOfPiece(selectedPiece);
            Player player;
            if (color == Piece.Color.WHITE)
                player = game.getPlayer_White();
            else
                player = game.getPlayer_Black();

            if (player == GameManager.getActivePlayer())
            {
                moveToSopt = (ImageView)event.getSource();

                int from_i = get_XIndex(selectedPiece);
                int from_j = get_YIndex(selectedPiece);
                int to_i = get_XIndex(moveToSopt);
                int to_j = get_YIndex(moveToSopt);

                game.move(player, from_i, from_j, to_i, to_j);

                de_select();
                //Sounds.play(Sounds.SoundEffects.MOVE);
                GameManager.updateTheGame(game);
                GameManager.switchTheActivePlayer();
            }
    }


    private static boolean isImageViewEmpty(ImageView imageView)
    {
        if (imageView.getImage()==Img.Tile_Black || imageView.getImage()==Img.Tile_White)
            return true;
        return false;
    }

    private static Piece.Color getColorOfPiece(ImageView imageView)
    {
        ImageView[][] imageViewsBoard = MainStageController.getImageViewsBoard();

        int i = get_XIndex(imageView);
        int j = get_YIndex(imageView);
        Game game = GameManager.getTheGame();
        Piece.Color color = game.getBoard().getSpot(i,j).getPiece().getColor();

        return color;
    }

    private static int get_XIndex(ImageView imageView)
    {
        ImageView[][] imageViewsBoard = MainStageController.getImageViewsBoard();
        for (int i =0; i<8; i++)
            for (int j=0; j<8; j++)
                if (imageViewsBoard[i][j].equals(imageView))
                {
                    return i;
                }

        return -1;
    }

    private static int get_YIndex(ImageView imageView)
    {
        ImageView[][] imageViewsBoard = MainStageController.getImageViewsBoard();
        for (int i =0; i<8; i++)
            for (int j=0; j<8; j++)
                if (imageViewsBoard[i][j].equals(imageView))
                {
                    return j;
                }

        return -1;
    }

    private static Piece.Color getBackgroundColor(int i, int j)
    {
        Piece.Color backgroundColor = Piece.Color.WHITE;
        if ((i+j)%2 == 0)
            backgroundColor = Piece.Color.BLACK;
        return backgroundColor;

    }

    private static ImageView updateTheTile(ImageView imageView, Piece.Type type, Piece.Color pieceColor, Piece.Color backgroundColor)
    {
        imageView.setImage(Img.getPiece(type,pieceColor,backgroundColor));
        return imageView;
    }

    private static ImageView getImageViewOfSpot(Spot spot)
    {
        int x = spot.get_X();
        int y = spot.get_Y();
        return MainStageController.getImageViewsBoard()[x][y];
    }





}