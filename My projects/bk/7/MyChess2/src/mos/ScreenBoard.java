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
//    --> Updated Aug-21-2016
//
// =============================================================================

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;


public class ScreenBoard {

    private static boolean isAnyPieceSelected = false;
    private static ImageView selectedImage;


    // =========================================================================================<<<   Methods   >>>=== \\

    public static void start(ImageView[][] imageViewsBoard)
    {
            GameManager.startAGame();
            Options.playSoundEffect();
            updateTheScreen();
            //update_removedBoard();
            Options.cancel_all_highlights(imageViewsBoard);
            cancel_selection();

    }

    // ==============================================================================
    // Method : clickOnBoard(Event event)                                   Level : 1
    //
    // Purpose : It manages the effects that needed to be done when user clicks on the
    // board. It calls by MainStageController.mouseClick(Event)
    //
    // =============================================================================
    public static void clickOnBoard(Event event)
    {
        Report.open_report(1, "ScreenBoard.clickOnBoard(Event)");
        ImageView clickedTile = Utl.getImageView(event);
        // select a piece
        if (!isAnyPieceSelected && !Utl.isImageViewEmpty(clickedTile) && Utl.isActivePlayer(event))
            select(event);
        else if (isAnyPieceSelected)
        {
            // deselect the piece if clicks the second time on it. And then highlight if it is check.
            if (selectedImage == clickedTile)
                cancel_selection();
            else
                play(event);
        }
        Report.close_report(1);
    }

    // ==============================================================================
    // Method : select(Event event)                                         Level : 2
    //
    // Purpose : It selects a piece by selecting the corrsponding imageView for further
    // moves.
    //
    // =============================================================================
    private static void select(Event event)
    {
        Report.open_report(2, "ScreenBoard.select(Event)");

        selectedImage = Utl.getImageView(event);
        selectedImage.setOpacity(0.5);
        isAnyPieceSelected = true;
        Options.cancel_highlight(event);

        Game game = GameManager.getTheGame();
        Board board = game.getBoard();
        Piece selectedPiece = Utl.getPiece(board, event);

        game.makeComment(selectedPiece +" is selected.");
        Report.report(2, game.getComment());

        Report.close_report(2);
    }

    // ==============================================================================
    // Method : cancel_selection()                                          Level : 2
    //
    // Purpose : It cancels the selected imageView which belongs to the spot of the
    // selected piece.
    //
    // =============================================================================
    public static void cancel_selection() {

        Report.open_report(2, "ScreenBoard.cancel_selection()");

        if (isAnyPieceSelected) {
            selectedImage.setOpacity(1);
            isAnyPieceSelected = false;
            makeComment("");
            Report.report(2, GameManager.getTheGame().getComment());
        }

        watchForCheck();

        Report.close_report(2);
    }

    // ==============================================================================
    // Method : play(Event event)                                           Level : 2
    //
    // Purpose : It plays the selected piece and move it to the spot which the cursor
    // is on.
    //
    // =============================================================================
    private static void play(Event event)
    {
        Report.open_report(2, "ScreenBoard.move(Event)");

        //if (Utl.isValidRemove(GameManager.getTheGame(), ))
        GameManager.getTheGame().play(selectedImage, Utl.getImageView(event));
        Options.playSoundEffect();
        Options.cancel_allHighlights(event);
        updateTheScreen();

        Report.close_report(2);
    }

    // ==============================================================================
    // Method : mouseOn(Event event)                                        Level : 1
    //
    // Purpose : It manage effects that needed to occure when the mouse is on the tile
    //
    // =============================================================================
    public static void mouseOn(Event event)
    {

        if (!isAnyPieceSelected && Utl.isActivePlayer(event))
            Utl.changeCursorToHand(event);

        if (isAnyPieceSelected && !isMouseOnSelectedPiece(event))
        {
            Utl.changeCursorToHand(event);
            Options.highlightTheRoutes(event);
        }

    }


    // ==============================================================================
    // Method : mouseOut(Event event)                                       Level : 1
    //
    // Purpose : It manage effects that needed to occure when the mouse exit from the
    // tile
    //
    // =============================================================================
    public static void mouseOut(Event event) {
        if (!isAnyPieceSelected && Utl.isActivePlayer(event))
        {
            Utl.changeCursorToNormal(event);
        }
        if (isAnyPieceSelected && !isMouseOnSelectedPiece(event)) {
            Utl.changeCursorToNormal(event);
            Options.cancel_highlight(event);
            Options.cancel_highlightTheWay(event);
        }
    }


    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  Helper Methods  >>>>>>>>


    // ==============================================================================
    // Method : updateTheScreen()                                           Level : 3
    //
    // Purpose : It cancels any selections and then checks out what image should be on
    // each tile of the board, and calls the updateTheTile() method to update tiles.
    // It calls by MainStageController.java
    //
    // =============================================================================
    public static void updateTheScreen()
    {
        cancel_selection();

        // -------------------------------------------------------------------------
        // The following block updates the chessboard on the Screen.
        // -------------------------------------------------------------------------
        Spot spot;
        Piece piece;
        ImageView imageViewOfSpot;
        Piece.Type type;
        Piece.Color pieceColor;
        Piece.Color backgroundColor;
        Game game = GameManager.getTheGame();

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {

                spot = game.getBoard().getSpot(i, j);
                imageViewOfSpot = Utl.getImageViewOfSpot(spot);
                backgroundColor = Utl.getBackgroundColor(i, j);

                if (spot.isOccupied())
                {
                    piece = spot.getPiece();
                    updateTheTile(imageViewOfSpot, piece, backgroundColor);
                }
                else
                {
                    if (backgroundColor == Piece.Color.BLACK)
                        imageViewOfSpot.setImage(Img.Tile_Black);
                    else
                        imageViewOfSpot.setImage(Img.Tile_White);
                }
            }
        }
        update_removedBoard(game.getPlayer_White());
        update_removedBoard(game.getPlayer_Black());
    }



    private static void update_removedBoard(Player player)
    {

        if (player.getTotalMoves()==0)
            return;


        ImageView[] removedBoard;

        if (player.getColor()== Piece.Color.WHITE)
            removedBoard = MainStageController.get_removedBoard_white();
        else
            removedBoard = MainStageController.get_removedBoard_black();

        Piece[] removedPieces = player.get_RemovedPieces();
        Piece piece;

        for (int i=0 ; i<15; i++)
        {
            if (removedPieces[i]!=null)
            {
                piece = removedPieces[i];
                updateTheTile(removedBoard[i], piece, Piece.Color.WHITE);
            }
        }
    }

    // ==============================================================================
    // Method : watchForCheck()                                             Level : 3
    //
    // Purpose : It sees if it's check and does whatever needs to do in case.
    //
    // =============================================================================
    public static void watchForCheck()
    {
        Report.open_report(3, "ScreenBoard.watchForCheck()");

        if (Utl.isCheck(GameManager.getTheGame()))
        {
            Options.highlightCheck();
            Sounds.setSound(Sounds.SoundEffects.CHECK);
            Options.playSoundEffect();
        }
        Report.close_report(3);
    }

    // ==============================================================================
    // Method : updateTheTile()                                             Level : 3
    //
    // Purpose : It put the proper image on a tile of the chessboard.
    //
    // =============================================================================
    private static void updateTheTile(ImageView imageView, Piece piece, Piece.Color backgroundColor)
    {
        imageView.setImage(Img.getPiece(piece.getPieceType(), piece.getColor(), backgroundColor));
    }

    // ==============================================================================
    // Method : makeComment(String)                                         Level : 3
    //
    // Purpose : It will set a string comment on Game.comment.
    //
    // =============================================================================
    private static void makeComment(String str)
    {
        GameManager.getTheGame().makeComment(str);
    }

    // ==============================================================================
    // Method : get_selectedImageView()                                     Level : 3
    //
    // Purpose : It returns the selected ImageView.
    //
    // =============================================================================
    public static ImageView get_selectedImageView()
    {
        return selectedImage;
    }

    // ==============================================================================
    // Method : isMouseOnSelectedPiece(Event)                               Level : 3
    //
    // Purpose : It shows whether the mouse is on the selected tile.
    //
    // =============================================================================
    private static boolean isMouseOnSelectedPiece(Event event) {
        if (isAnyPieceSelected)
            return Utl.getImageView(event) == selectedImage;
        return false;
    }



}

