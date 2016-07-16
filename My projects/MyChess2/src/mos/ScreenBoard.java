package mos;

import javafx.event.Event;
import javafx.scene.Cursor;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.IOException;

/**
 * Created by MOS on 2016-07-12.
 */
public class ScreenBoard {

    private static boolean isAnyPieceSelected = false;
    private static ImageView selectedPiece;
    private static ImageView moveToSopt;


    // =============================================================
    //                           Methods
    // =============================================================

    public static void clickOnBoard(Event event) throws IOException {
        ImageView clickedTile = getSelectedImageView(event);

        if (!isAnyPieceSelected && !isImageViewEmpty(clickedTile) && isActivePlayer(event))
            select(event);

        else if (isAnyPieceSelected) {
            if (selectedPiece == clickedTile)
                de_select();

            else if (isImageViewEmpty(clickedTile)) {
                move(event);
                undoHighlight(event);
                undoHighlightTheWay(event);
                changeCursorToNormal(event);

            }
        }
        updateTheChessBoard();
    }


    public static void updateTheChessBoard() {
        Spot spot;
        Piece piece;
        ImageView imageViewOfSpot;
        Piece.Type type;
        Piece.Color pieceColor;
        Piece.Color backgroundColor;

        Game game = GameManager.getTheGame();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                spot = game.getBoard().getSpot(i, j);
                imageViewOfSpot = getImageViewOfSpot(spot);
                backgroundColor = getBackgroundColor(i, j);

                if (spot.isOccupied()) {
                    piece = spot.getPiece();
                    type = piece.getPieceType();
                    pieceColor = piece.getColor();

                    updateTheTile(imageViewOfSpot, type, pieceColor, backgroundColor);

                } else {
                    if (backgroundColor == Piece.Color.BLACK)
                        imageViewOfSpot.setImage(Img.Tile_Black);
                    else
                        imageViewOfSpot.setImage(Img.Tile_White);
                }

            }
        }


    }


    public static void mouseOn(Event event) {

        if (!isAnyPieceSelected && isActivePlayer(event))
        {
            changeCursorToHand(event);
        }

        if (isAnyPieceSelected && !isMouseOnSelectedPiece(event)) {
            changeCursorToHand(event);
            highlight(event);
            highlightTheWay(event);
        }
    }

    public static void mouseOut(Event event) {
        if (!isAnyPieceSelected && isActivePlayer(event))
        {
            changeCursorToNormal(event);
        }
        if (isAnyPieceSelected && !isMouseOnSelectedPiece(event)) {
            changeCursorToNormal(event);
            undoHighlight(event);
            undoHighlightTheWay(event);
        }
    }

    // =============================================================
    //                    Helper Methods
    // =============================================================


    private static void undoHighlight(ImageView imageView) {
        imageView.setEffect(null);
    }

    private static void highlight(ImageView imageView) {
        InnerShadow effect = new InnerShadow();
        effect.setColor(Color.valueOf("#ffde05a5"));
        effect.setHeight(255);
        effect.setRadius(127);
        effect.setWidth(255);
        imageView.setEffect(effect);
    }

    private static void highlightTheWay(Event event)
    {
        int to_i = getSelectedSpot(event).get_X();
        int to_j = getSelectedSpot(event).get_Y();
        int from_i =  get_XIndex(selectedPiece);
        int from_j =  get_YIndex(selectedPiece);
        Game game = GameManager.getTheGame();

        Spot[] spots = game.getSpotsOnTheWay(from_i, from_j, to_i,to_j);

        //Report.report(from_i, from_j, to_i, to_j);
        //Report.report(spots);

        if (!game.isAnyPieceOnTheWay(from_i, from_j, to_i, to_j))
        {
            Report.report(" The way is clear ");
            for (int i = 0; i<6; i++)
            {
                if (spots[i]!=null)
                    highlight(getImageViewOfSpot(spots[i]));
            }
        }
        else
            Report.report(" !!! < The way is blocked > !!!");

    }

    private static void undoHighlightTheWay(Event event)
    {
        int to_i = getSelectedSpot(event).get_X();
        int to_j = getSelectedSpot(event).get_Y();
        int from_i =  get_XIndex(selectedPiece);
        int from_j =  get_YIndex(selectedPiece);
        Game game = GameManager.getTheGame();

        Spot[] spots = game.getSpotsOnTheWay(from_i, from_j, to_i,to_j);
        for (int i = 0; i<6; i++)
            if (spots[i]!=null)
                    undoHighlight(getImageViewOfSpot(spots[i]));

    }

    private static void undoHighlight(Event event) {
        undoHighlight(getSelectedImageView(event));
    }


// =================================================================================================================

    private static void move(Event event) throws IOException {

        Game game = GameManager.getTheGame();

        Piece.Color color = getColorOfPiece(selectedPiece);
        Player player;
        if (color == Piece.Color.WHITE)
            player = game.getPlayer_White();
        else
            player = game.getPlayer_Black();

        game.setSelectedPlayer(player);

        moveToSopt = getSelectedImageView(event);

        int from_i = get_XIndex(selectedPiece);
        int from_j = get_YIndex(selectedPiece);
        //Spot fromSpot = game.getBoard().getSpot(from_i, from_j);
        int to_i = get_XIndex(moveToSopt);
        int to_j = get_YIndex(moveToSopt);
        //Spot toSpot = game.getBoard().getSpot(to_i,to_j);

        if (game.move(player, from_i, from_j, to_i, to_j)) {
            de_select();
            //Sounds.play(Sounds.SoundEffects.MOVE);
            GameManager.updateTheGame(game);
            game.switchTheActivePlayer();

        }

    }


    private static ImageView updateTheTile(ImageView imageView, Piece.Type type, Piece.Color pieceColor, Piece.Color backgroundColor) {
        imageView.setImage(Img.getPiece(type, pieceColor, backgroundColor));
        return imageView;
    }


    private static void select(Event event) {
        selectedPiece = getSelectedImageView(event);
        selectedPiece.setOpacity(0.5);
        isAnyPieceSelected = true;
    }


    private static void de_select() {
        selectedPiece.setOpacity(1);
        isAnyPieceSelected = false;
    }


    private static boolean isImageViewEmpty(ImageView imageView) {
        if (imageView.getImage() == Img.Tile_Black || imageView.getImage() == Img.Tile_White)
            return true;
        return false;
    }


    private static void changeCursorToHand(Event event) {
        getSelectedImageView(event).setCursor(Cursor.HAND);
    }


    private static void changeCursorToNormal(Event event) {
        getSelectedImageView(event).setCursor(Cursor.DEFAULT);
    }


    private static boolean isMouseOnSelectedPiece(Event event) {
        if (isAnyPieceSelected)
            return getSelectedImageView(event) == selectedPiece;
        return false;
    }


    private static void highlight(Event event) {
        highlight(getSelectedImageView(event));
    }



    private static ImageView getSelectedImageView(Event event) {
        return (ImageView) event.getSource();
    }

    private static Spot getSelectedSpot(Event event)
    {
        ImageView imageView = getSelectedImageView(event);
        int i = get_XIndex(imageView);
        int j = get_YIndex(imageView);
        return GameManager.getTheGame().getBoard().getSpot(i,j);
    }

    private static boolean isTileEmpty(Event event)
    {
        ImageView imageView = getSelectedImageView(event);
        return isImageViewEmpty(imageView);
    }

    private static boolean isActivePlayer(Event event)
    {
        if (!isTileEmpty(event))
        {
            Piece.Color color = getColorOfPiece(getSelectedImageView(event));
            Player player;
            if (color== Piece.Color.WHITE)
                player = GameManager.getTheGame().getPlayer_White();
            else
                player = GameManager.getTheGame().getPlayer_Black();

            return GameManager.getTheGame().isActivePlayer(player);
        }
        return false;
    }

    private static ImageView getImageViewOfSpot(Spot spot) {
        int x = spot.get_X();
        int y = spot.get_Y();
        return MainStageController.getImageViewsBoard()[x][y];
    }


    private static Piece.Color getColorOfPiece(ImageView imageView) {
        ImageView[][] imageViewsBoard = MainStageController.getImageViewsBoard();

        int i = get_XIndex(imageView);
        int j = get_YIndex(imageView);
        Game game = GameManager.getTheGame();
        Piece.Color color = game.getBoard().getSpot(i, j).getPiece().getColor();

        return color;
    }


    private static int get_XIndex(ImageView imageView) {
        ImageView[][] imageViewsBoard = MainStageController.getImageViewsBoard();
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (imageViewsBoard[i][j].equals(imageView)) {
                    return i;
                }

        return -1;
    }


    private static int get_YIndex(ImageView imageView) {
        ImageView[][] imageViewsBoard = MainStageController.getImageViewsBoard();
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (imageViewsBoard[i][j].equals(imageView)) {
                    return j;
                }

        return -1;
    }


    private static Piece.Color getBackgroundColor(int i, int j) {

        Piece.Color backgroundColor = Piece.Color.WHITE;
        if ((i + j) % 2 == 0)
            backgroundColor = Piece.Color.BLACK;
        return backgroundColor;

    }

}