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


        ImageView clickedTile = getImageView(event);

        if (!isAnyPieceSelected && !isImageViewEmpty(clickedTile) && isActivePlayer(event))
            select(event);

        else if (isAnyPieceSelected) {
            if (selectedPiece == clickedTile)
                de_select();

            else if (isImageViewEmpty(clickedTile) || !isActivePlayer(event)) {

                Spot king1 = GameManager.getTheGame().getActivePlayer().getSpotOfKing();
                undoHighlight(getImageViewOfSpot(king1));

                move(event);
                undoHighlight(event);
                undoHighlightTheWay(event);
                changeCursorToNormal(event);

                Spot king2 = GameManager.getTheGame().getActivePlayer().getSpotOfKing();

                if (stillCheck())
                    highlightCheck(getImageViewOfSpot(king1));


                if (isCheck())
                    highlightCheck(getImageViewOfSpot(king2));




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
                        imageViewOfSpot.setImage(IMG.Tile_Black);
                    else
                        imageViewOfSpot.setImage(IMG.Tile_White);
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
            //highlight(event);
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



    private static boolean isCheck()
    {
        return GameManager.getTheGame().isCheck();
    }

    private static boolean stillCheck()
    {
        return GameManager.getTheGame().stillCheck();
    }

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

    private static void highlightCheck(ImageView imageView){

        InnerShadow effect = new InnerShadow();
        effect.setColor(Color.valueOf("#aa09ea94"));
        effect.setHeight(255);
        effect.setRadius(127);
        effect.setWidth(255);
        imageView.setEffect(effect);

    }

    private static void highlightTheWay(Event event)
    {
        int to_i = getSpot(event).get_X();
        int to_j = getSpot(event).get_Y();
        int from_i =  get_XIndex(selectedPiece);
        int from_j =  get_YIndex(selectedPiece);
        Game game = GameManager.getTheGame();

        Spot[] spots = game.getSpotsOnTheWay(from_i, from_j, to_i,to_j);

        //Report.report(from_i, from_j, to_i, to_j);
        //Report.report(spots);

        if (!game.isAnyPieceOnTheWay(from_i, from_j, to_i, to_j) &&
                (game.isValidMove(from_i,from_j,to_i,to_j) || game.isValidRemove(from_i,from_j,to_i,to_j)))
        {
            //Report.report(" The way is clear ");
            for (int i = 0; i<6; i++)
            {
                if (spots[i]!=null)
                    highlight(getImageViewOfSpot(spots[i]));
            }
            if (!isActivePlayer(event))
                  highlight(event);

        }
        else
        {
            Report.report(" !!! < The way is blocked > !!!");

        }

    }

    private static void undoHighlightTheWay(Event event)
    {
        int to_i = getSpot(event).get_X();
        int to_j = getSpot(event).get_Y();
        int from_i =  get_XIndex(selectedPiece);
        int from_j =  get_YIndex(selectedPiece);
        Game game = GameManager.getTheGame();

        Spot[] spots = game.getSpotsOnTheWay(from_i, from_j, to_i,to_j);
        for (int i = 0; i<6; i++)
            if (spots[i]!=null)
                    undoHighlight(getImageViewOfSpot(spots[i]));

    }

    private static void undoHighlight(Event event) {
        undoHighlight(getImageView(event));
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

        moveToSopt = getImageView(event);

        int from_i = get_XIndex(selectedPiece);
        int from_j = get_YIndex(selectedPiece);
        //Spot fromSpot = game.getBoard().getSpot(from_i, from_j);
        int to_i = get_XIndex(moveToSopt);
        int to_j = get_YIndex(moveToSopt);
        //Spot toSpot = game.getBoard().getSpot(to_i,to_j);

        if (game.move(player, from_i, from_j, to_i, to_j)) {
            //de_select();
            //Sounds.play(Sounds.SoundEffects.MOVE);
            game.switchTheActivePlayer();

        }
        de_select();
        GameManager.updateTheGame(game);


    }


    private static ImageView updateTheTile(ImageView imageView, Piece.Type type, Piece.Color pieceColor, Piece.Color backgroundColor) {
        imageView.setImage(IMG.getPiece(type, pieceColor, backgroundColor));
        return imageView;
    }


    private static void select(Event event) {
        selectedPiece = getImageView(event);
        selectedPiece.setOpacity(0.5);
        isAnyPieceSelected = true;
        undoHighlight(event);
    }


    private static void de_select() {
        selectedPiece.setOpacity(1);
        isAnyPieceSelected = false;
    }


    private static boolean isImageViewEmpty(ImageView imageView) {
        if (imageView.getImage() == IMG.Tile_Black || imageView.getImage() == IMG.Tile_White)
            return true;
        return false;
    }


    private static void changeCursorToHand(Event event) {
        getImageView(event).setCursor(Cursor.HAND);
    }


    private static void changeCursorToNormal(Event event) {
        getImageView(event).setCursor(Cursor.DEFAULT);
    }


    private static boolean isMouseOnSelectedPiece(Event event) {
        if (isAnyPieceSelected)
            return getImageView(event) == selectedPiece;
        return false;
    }


    private static void highlight(Event event) {
        highlight(getImageView(event));
    }



    private static ImageView getImageView(Event event) {
        return (ImageView) event.getSource();
    }

    private static Spot getSpot(Event event)
    {
        ImageView imageView = getImageView(event);
        int i = get_XIndex(imageView);
        int j = get_YIndex(imageView);
        return GameManager.getTheGame().getBoard().getSpot(i,j);
    }

    private static boolean isTileEmpty(Event event)
    {
        ImageView imageView = getImageView(event);
        return isImageViewEmpty(imageView);
    }

    private static boolean isActivePlayer(Event event)
    {
        if (!isTileEmpty(event))
        {
            Piece.Color color = getColorOfPiece(getImageView(event));
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


    public static void undoAllHighlights(ImageView[][] imageViews)
    {
        for (int i=0; i<8; i++)
            for (int j=0; j<8; j++)
                imageViews[i][j].setEffect(null);
    }



}