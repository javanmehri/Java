package mos;

//==============================================================================
// File         : ScreenBoard.java
//
// Current Author: Mostafa Javanmehri
//
// Contact Info: m.javanmehri@gmail.com
//
// Purpose : This file containes the methods that are needed to interact the game
// codes and the graphical scene which are used in the  MainStageController.java
//
// Dependencies: MainStage.fxml
//
// Modification Log :
//    --> Created Jul-12-2016
//    --> Updated Aug-14-2016
//
// =============================================================================

import com.sun.org.apache.regexp.internal.RE;
import javafx.event.Event;
import javafx.scene.Cursor;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import java.io.IOException;


public class ScreenBoard {

    private static boolean isAnyPieceSelected = false;
    private static boolean highlightTheRoute = false;
    private static ImageView selectedPiece;
    private static ImageView moveToSopt;


    // =====================================<<<  Linked Controller Methods   >>>===================================== \\


    // ==============================================================================
    // Method : clickOnBoard(Event event)
    //
    // Current Author: Mostafa Javanmehri
    //
    // Purpose : It manages the effects that needed to be done when user clicks on the
    // board. It calls by MainStageController.mouseClick(Event)
    //
    // Dependencies: GameManager.java
    //
    // Modification Log :
    // --> Created Jul-12-2016
    // --> Updated MMM-DD-YYYY
    //
    // =============================================================================
    public static void clickOnBoard(Event event) throws IOException, CloneNotSupportedException {

        //Report.report("\n"+"ScreenBoard.clickOnBoard(Event) :"+"\n"+"{");
        Report.open_report(1, "ScreenBoard.clickOnBoard(Event)");

        ImageView clickedTile = getImageView(event);

        // select a piece
        if (!isAnyPieceSelected && !isImageViewEmpty(clickedTile) && isActivePlayer(event))
            select(event);

        else if (isAnyPieceSelected) {

            // deselect the piece if clicks the second time on it. And then highlight if it is check.
            if (selectedPiece == clickedTile) {

                de_select();

                watchForCheck();

            }
            // do the move if a piece is already selected and the active user is clicked on an empty tile. And then
            // check if it's CHECK!

            //else if (isImageViewEmpty(clickedTile) || !isActivePlayer(event)) {

            else
            {
                //Report.report("   ScreenBoard.move(Event) :"+"\n"+"   {");
                move(event);
                //Report.report("   }");

                //Report.report("   ScreenBoard.watchForCheck() :"+"\n"+"   {");
                watchForCheck();
                //Report.report("   }");

            }

        }

        updateTheChessBoard();
        //Report.report("}"+"\n");
        Report.close_report(1);
    }


    // ==============================================================================
    // Method : updateTheChessBoard()
    //
    // Current Author: Mostafa Javanmehri
    //
    // Purpose : It checks out what image should be on each tile of the board, and calls
    // the updateTheTile() method to update tiles. It calls by MainStageController.java
    //
    // Dependencies: updateTheTile(...), GameManager.getTheGame()
    //
    // Modification Log :
    // --> Created Jul-12-2016
    // --> Updated MMM-DD-YYYY
    //
    // =============================================================================
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


    // ==============================================================================
    // Method : mouseOn(Event event)
    //
    // Current Author: Mostafa Javanmehri
    //
    // Purpose : It manage effects that needed to occure when the mouse is on the tile
    //
    // Dependencies: isAnyPieceSelected, isActivePlayer(event), isMouseOnSelectedPiece(event),
    // highlightPossibleMoves
    //
    // Modification Log :
    // --> Created Jul-12-2016
    // --> Updated MMM-DD-YYYY
    //
    // =============================================================================
    public static void mouseOn(Event event) throws CloneNotSupportedException {

        if (!isAnyPieceSelected && isActivePlayer(event))
            changeCursorToHand(event);

        if (isAnyPieceSelected && !isMouseOnSelectedPiece(event))
        {
            changeCursorToHand(event);
            if (highlightTheRoute)
                highlightTheWay(event);
        }

    }


    // ==============================================================================
    // Method : mouseOut(Event event)
    //
    // Current Author: Mostafa Javanmehri
    //
    // Purpose : It manage effects that needed to occure when the mouse exit from the tile
    //
    // Dependencies: isAnyPieceSelected, isActivePlayer(event), isMouseOnSelectedPiece(event),
    // highlightPossibleMoves
    //
    // Modification Log :
    // --> Created Jul-12-2016
    // --> Updated MMM-DD-YYYY
    //
    // =============================================================================
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


    // ===========================================<<<  Helper Methods  >>>=========================================== \\


    // ==============================================================================
    // Method : highlightTheWay(Event event)
    //
    // Current Author: Mostafa Javanmehri
    //
    // Purpose : It highlights the route of the move if its not blocked and if the move is
    // legal
    //
    // Dependencies: GameManager.getTheGame(), game.isAnyPieceOnTheWay, highlight(ImageView)
    //
    // Modification Log :
    // --> Created Jul-12-2016
    // --> Updated MMM-DD-YYYY
    //
    // =============================================================================
    private static void highlightTheWay(Event event) throws CloneNotSupportedException {

        //Report.report(" > ScreenBoard.highlightTheWay"+"\n"+"{");
        int from_i =  get_XIndex(selectedPiece);
        int from_j =  get_YIndex(selectedPiece);
        int to_i = getSpot(event).get_X();
        int to_j = getSpot(event).get_Y();

        Game game = GameManager.getTheGame();

        Spot[] spots = game.getSpotsOnTheWay(from_i, from_j, to_i,to_j);

        //Report.report("game.isValidMove(from_i,from_j,to_i,to_j) : " + game.isValidMove(from_i,from_j,to_i,to_j));

        if ( game.isValidMove(from_i,from_j,to_i,to_j) || game.isValidRemove(from_i,from_j,to_i,to_j) )
        {
            for (int i = 0; i<6; i++)
            {
                if (spots[i]!=null)
                    highlight(getImageViewOfSpot(spots[i]));
            }

            //if (!isActivePlayer(event))
            //Report.report("game.isValidMove(from_i,from_j,to_i,to_j) : " + game.isValidMove(from_i,from_j,to_i,to_j));
            //Report.report("game.isValidRemove(from_i,from_j,to_i,to_j) : " + game.isValidRemove(from_i,from_j,to_i,to_j));
            highlight(event);

        }
        else
        {
            //Report.report(" !!! < The way is blocked > !!!");
        }

        //Report.report("}");
    }

    // ==============================================================================
    // Method : undoHighlightTheWay(Event event)
    //
    // Current Author: Mostafa Javanmehri
    //
    // Purpose : It removes the highlight effect on the route
    //
    // Dependencies: GameManager.getTheGame(), undoHighlight(ImageView)
    //
    // Modification Log :
    // --> Created Jul-12-2016
    // --> Updated MMM-DD-YYYY
    //
    // =============================================================================
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


    private static void move(Event event) throws IOException, CloneNotSupportedException {

        //Report.report("\n"+"   ScreenBoard.move(Event)"+"\n"+"   {");
        Report.open_report(2, "ScreenBoard.move(Event)");

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

        // -----------------------------------------------------------------------------
        // The following block removes highlight on the king and CHECK comment, in case.
        // -----------------------------------------------------------------------------
        Spot kingSpot = GameManager.getTheGame().getActivePlayer().getSpotOfKing();
        undoHighlight(getImageViewOfSpot(kingSpot));
        game.comment("");

        // -----------------------------------------------------------------------------
        // The following block removes other highlights and changes the cursor to normal
        // -----------------------------------------------------------------------------
        undoHighlight(event);
        undoHighlightTheWay(event);
        changeCursorToNormal(event);

        /*
        if (game.isValidMove(from_i, from_j, to_i, to_j) || game.isValidRemove(from_i, from_j, to_i, to_j) )
        {
            game.move(player, from_i, from_j, to_i, to_j);
            game.switchTheActivePlayer();
        }
        */

        game.move(player, from_i, from_j, to_i, to_j);

        //if (game.move(player, from_i, from_j, to_i, to_j)) {
            //de_select();
            //Sounds.play(Sounds.SoundEffects.MOVE);
            //Report.report("> ScreenBoard.move: game.move -> true");
            //game.switchTheActivePlayer();

        //}

        de_select();
        GameManager.updateTheGame(game);

        //Report.report("   }"+"\n");
        Report.close_report(2);
    }


    private static void watchForCheck() throws IOException {

        //Report.report("\n"+"   ScreenBoard.watchForCheck()"+"\n"+"   {");
        Report.open_report(2, "ScreenBoard.watchForCheck()");

        Game game = GameManager.getTheGame();
        Spot kingSpot = game.getActivePlayer().getSpotOfKing();
        ImageView kingImageView = getImageViewOfSpot(kingSpot);

        if (game.isCheck())
        {
            highlightCheck(kingImageView);
            game.comment(" ! CHECK !");
            Sounds.play(Sounds.SoundEffects.CHECK);
            //System.out.println(" > Check ");
        }

        //Report.report("   }"+"\n");
        Report.close_report(2);
    }


    private static boolean stillCheck()
    {
        Game game = GameManager.getTheGame();
        Spot kingSpot = game.getActivePlayer().getSpotOfKing();
        ImageView kingImageView = getImageViewOfSpot(kingSpot);

        if (game.stillCheck())
        {
            highlightCheck(kingImageView);
        }

        return game.stillCheck();
    }


    private static void undoHighlight(ImageView imageView) {
        imageView.setEffect(null);
    }

    private static void undoHighlight(Event event) {
        undoHighlight(getImageView(event));
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








// =================================================================================================================




    private static ImageView updateTheTile(ImageView imageView, Piece.Type type, Piece.Color pieceColor, Piece.Color backgroundColor) {
        imageView.setImage(IMG.getPiece(type, pieceColor, backgroundColor));
        return imageView;
    }


    private static void select(Event event) throws CloneNotSupportedException {
        selectedPiece = getImageView(event);
        selectedPiece.setOpacity(0.5);
        isAnyPieceSelected = true;
        undoHighlight(event);

        //String text = Integer.toString(COMP.possibleMoveSize(GameManager.getTheGame(), getSpot(event).getPiece()));

        //MainStageController.textArea.setText("  !!! ");
    }


    public static void de_select() {
        if (isAnyPieceSelected) {
            selectedPiece.setOpacity(1);
            isAnyPieceSelected = false;
        }
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


    public static Spot getSpot(Event event)
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

    public static void switch_highlightTheRoute()
    {
        if (highlightTheRoute)
            highlightTheRoute = false;
        else
            highlightTheRoute = true;
    }

    public static boolean get_HighlightPossibleMoves()
    {
        return highlightTheRoute;
    }

}