package mos;

import javafx.event.Event;
import javafx.scene.image.ImageView;

import java.io.IOException;

/**
 * Created by MOS on 2016-07-12.
 */
public class ScreenManager {

    //private static ImageView[][] imageViewsBoard = new ImageView[8][8];
    private static boolean isAnyPieceSelected = false;
    private static ImageView selectedPiece;
    private static ImageView moveToSopt;


    // =============================================================
    //                    Helper Methods
    // =============================================================



    public static Game clickOnBoard(Game game,ImageView[][] imageViewsBoard, Event event) throws IOException {
        ImageView clickedTile = (ImageView)event.getSource();

        if (!isAnyPieceSelected && !isImageViewEmpty(clickedTile))
            select(event);

        else if (isAnyPieceSelected)
        {
            if (selectedPiece == clickedTile)
                de_select();

            else if (isImageViewEmpty(clickedTile))
                game = move(game, imageViewsBoard, event);
        }
        updateTheChessBoard(game, imageViewsBoard);
        return game;
    }


    public static void start(Game game, ImageView[][] imageViewsBoard) throws IOException {
        Sounds.play(Sounds.SoundEffects.START);
        updateTheChessBoard(game, imageViewsBoard);
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

    private static Game move(Game game, ImageView[][] imageViewsBoard, Event event) throws IOException {

            moveToSopt = (ImageView)event.getSource();

            //System.out.println(selectedPiece);
            Piece.Color color = getColorOfPiece(game, imageViewsBoard, selectedPiece);
            Player player;
            if (color == Piece.Color.WHITE)
                player = game.getPlayer_White();
            else
                player = game.getPlayer_Black();

            int from_i = get_XIndex(imageViewsBoard, selectedPiece);
            int from_j = get_YIndex(imageViewsBoard, selectedPiece);
            int to_i = get_XIndex(imageViewsBoard, moveToSopt);
            int to_j = get_YIndex(imageViewsBoard, moveToSopt);

            game.move(player, from_i, from_j, to_i, to_j);

            de_select();

            //Sounds.play(Sounds.SoundEffects.MOVE);

            return game;
    }


    private static boolean isImageViewEmpty(ImageView imageView)
    {
        if (imageView.getImage()==Img.Tile_Black || imageView.getImage()==Img.Tile_White)
            return true;
        return false;
    }

    private static Piece.Color getColorOfPiece(Game game, ImageView[][] imageViewsBoard, ImageView imageView)
    {
        int i = get_XIndex(imageViewsBoard, imageView);
        int j = get_YIndex(imageViewsBoard, imageView);
        Piece.Color color = game.getBoard().getSpot(i,j).getPiece().getColor();

        return color;
    }

    private static int get_XIndex(ImageView[][] imageViewsBoard, ImageView imageView)
    {
        for (int i =0; i<8; i++)
            for (int j=0; j<8; j++)
                if (imageViewsBoard[i][j].equals(imageView))
                {
                    return i;
                }

        return -1;
    }

    private static int get_YIndex(ImageView[][] imageViewsBoard, ImageView imageView)
    {
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

    private static ImageView getImageViewOfSpot(ImageView[][] imageViewsBoard, Spot spot)
    {
        int x = spot.get_X();
        int y = spot.get_Y();
        return imageViewsBoard[x][y];
    }


    private static void updateTheChessBoard(Game game, ImageView[][] imageViewsBoard)
    {
        Spot spot;
        Piece piece;
        ImageView imageViewOfSpot;
        Piece.Type type;
        Piece.Color pieceColor;
        Piece.Color backgroundColor;


        for (int i = 0; i<8; i++)
        {
            for (int j = 0; j<8; j++)
            {
                spot = game.getBoard().getSpot(i,j);
                imageViewOfSpot = getImageViewOfSpot(imageViewsBoard, spot);
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


}
