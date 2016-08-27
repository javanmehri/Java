package mos;

//==============================================================================
// File         : Options.java
//
// Current Author: Mostafa Javanmehri
//
// Contact Info: m.javanmehri@gmail.com
//
// Purpose : This file containes the methods that are needed for some option effects
// on the screen, liks highlights and comments.
//
// Dependencies: MainStage.fxml
//
// Modification Log :
//    --> Created Aug-22-2016
//    --> Updated Aug-24-2016
//
// =============================================================================

import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;


public class Options {

    private static boolean switch_highlightTheRoute = false;
    private static boolean switch_highlightChecks = false;
    private static boolean switch_soundEffects = false;
    private static boolean switch_comments = false;
    private static boolean switch_notes = false;

    // =================================================================================<<<  highlight Methods  >>>=== \\

    // ==============================================================================
    // Method : highlight(ImageView)
    //
    // Purpose : This method adds an especial highlight effect on the ImageView.
    //
    // =============================================================================
    public static void highlight(ImageView imageView) {
        InnerShadow effect = new InnerShadow();
        effect.setColor(Color.valueOf("#ffde05a5"));
        effect.setHeight(255);
        effect.setRadius(127);
        effect.setWidth(255);
        imageView.setEffect(effect);
    }

    // ==============================================================================
    // Method : highlight(Event)
    //
    // Purpose : This method adds an especial highlight effect on the ImageView.
    //
    // =============================================================================
    public static void highlight(Event event) {
        highlight(Utl.getImageView(event));
    }

    // ==============================================================================
    // Method : cancel_highlight(ImageView)
    //
    // Purpose : It removes the highlight effect on the ImageView.
    //
    // =============================================================================
    public static void cancel_highlight(ImageView imageView) {
        imageView.setEffect(null);
    }

    // ==============================================================================
    // Method : cancel_highlight(Event)
    //
    // Purpose : It removes the highlight effect on the ImageView which the cursor is
    // on.
    //
    // =============================================================================
    public static void cancel_highlight(Event event) {
        cancel_highlight(Utl.getImageView(event));
    }

    // ==============================================================================
    // Method : cancel_highlightTheWay(Event event)
    //
    // Purpose : It removes the highlight effect on the route
    //
    // =============================================================================
    public static void cancel_highlightTheWay(Event event)
    {
        ImageView selectedImageView = ScreenBoard.get_selectedImageView();
        if (selectedImageView==null)
            return;

        int to_i = Utl.getSpot(event).get_X();
        int to_j = Utl.getSpot(event).get_Y();
        int from_i =  Utl.get_XIndex(selectedImageView);
        int from_j =  Utl.get_YIndex(selectedImageView);
        Game game = GameManager.getTheGame();

        Spot[] spots = game.getSpotsOnTheWay(from_i, from_j, to_i,to_j);
        for (int i = 0; i<6; i++)
            if (spots[i]!=null)
                cancel_highlight(Utl.getImageViewOfSpot(spots[i]));
    }

    // ==============================================================================
    // Method : cancel_all_highlights(ImageView[][])
    //
    // Purpose : It removes highlight effects on each tile of the chess board.
    //
    // =============================================================================
    public static void cancel_all_highlights(ImageView[][] imageViews)
    {
        for (int i=0; i<8; i++)
            for (int j=0; j<8; j++)
                imageViews[i][j].setEffect(null);
    }

    // ==============================================================================
    // Method : cancel_allHighlights(Event event)
    //
    // Purpose : It cancels all highlights and change the cursor effect to the normal.
    //
    // =============================================================================
    public static void cancel_allHighlights(Event event)
    {
        // -----------------------------------------------------------------------------
        // The following block removes highlight on the king and CHECK comment, in case.
        // -----------------------------------------------------------------------------
        Game game = GameManager.getTheGame();
        Player player = game.getActivePlayer();
        Spot kingSpot = player.getSpotOfKing();
        Options.cancel_highlight(Utl.getImageViewOfSpot(kingSpot));
        // -----------------------------------------------------------------------------
        // The following block removes other highlights and changes the cursor to normal
        // -----------------------------------------------------------------------------
        Options.cancel_highlight(event);
        Options.cancel_highlightTheWay(event);
        Utl.changeCursorToNormal(event);
    }

    // ==================================================================================<<< highlightTheRoutes >>>=== \\

    // ==============================================================================
    // Method : highlightTheRoutes(Event event)
    //
    // Purpose : This method is used to highlight the route of the move if its not
    // blocked and if the move is legal, if and only if switch_highlightTheRoute=true.
    //
    // =============================================================================
    public static void highlightTheRoutes(Event event)
    {
        if (!isOn_highlightTheRoutes())
            return;

        ImageView selectedImageView = ScreenBoard.get_selectedImageView();
        if (selectedImageView==null)
            return;

        int from_i =  Utl.get_XIndex(selectedImageView);
        int from_j =  Utl.get_YIndex(selectedImageView);
        int to_i = Utl.getSpot(event).get_X();
        int to_j = Utl.getSpot(event).get_Y();

        Game game = GameManager.getTheGame();

        Spot[] spots = game.getSpotsOnTheWay(from_i, from_j, to_i,to_j);

        if ( game.isValidMove(from_i,from_j,to_i,to_j) || game.isValidRemove(from_i,from_j,to_i,to_j) )
        {
            for (int i = 0; i<6; i++)
            {
                if (spots[i]!=null)
                    highlight(Utl.getImageViewOfSpot(spots[i]));
            }

            highlight(event);

        }
        else
        {
            //Report.report(" !!! < The way is blocked > !!!");
        }
    }

    // ==============================================================================
    // Method : isOn_highlightTheRoutes()
    //
    // Purpose : This method is used to determine whether the switch_highlightTheRoute
    // is on or off.
    //
    // =============================================================================
    private static boolean isOn_highlightTheRoutes()
    {
        return switch_highlightTheRoute;
    }

    // ==============================================================================
    // Method : turnOn_highlightTheRoute()
    //
    // Purpose : It turns on the switch_highlightTheRoute. This method is used by
    // MainStageController.set_checkBox_highlightTheRoute and
    // MainStageController.set_checkMenuItem_highlightTheRoute methods.
    //
    // =============================================================================
    public static void turnOn_highlightTheRoute()
    {
        switch_highlightTheRoute = true;
    }

    // ==============================================================================
    // Method : turnOff_highlightTheRoute()
    //
    // Purpose : It turns off the switch_highlightTheRoute. This method is used by
    // MainStageController.set_checkBox_highlightTheRoute and
    // MainStageController.set_checkMenuItem_highlightTheRoute methods.
    //
    // =============================================================================
    public static void turnOff_highlightTheRoute()
    {
        switch_highlightTheRoute = false;
    }

    // =====================================================================================<<< highlightChecks >>>=== \\

    // ==============================================================================
    // Method : highlightCheck(ImageView)
    //
    // Purpose : This method adds an especial highlight effect on the ImageView, which
    // is different from highlight method. It will be used to highlight the King when
    // Check happens.
    //
    // =============================================================================
    public static void highlightCheck(){

        if (!isOn_highlightChecks())
            return;

        Game game = GameManager.getTheGame();
        Spot kingSpot = game.getActivePlayer().getSpotOfKing();
        ImageView imageView = Utl.getImageViewOfSpot(kingSpot);

        InnerShadow effect = new InnerShadow();
        effect.setColor(Color.valueOf("#aa09ea94"));
        effect.setHeight(255);
        effect.setRadius(127);
        effect.setWidth(255);
        imageView.setEffect(effect);

        highlightChecker();
    }

    // ==============================================================================
    // Method : isOn_highlightChecks()
    //
    // Purpose : This method is used to determine whether the switch_highlightChecks
    // is on or off.
    //
    // =============================================================================
    public static boolean isOn_highlightChecks()
    {
        return switch_highlightChecks;
    }

    // ==============================================================================
    // Method : highlightChecker()
    //
    // Purpose : This method adds highlight effect on the ImageView which
    // is contained the piece that Checks the King.
    //
    // =============================================================================
    private static void highlightChecker() {
        if (GameManager.getTheGame()!=null && GameManager.getTheGame().checker!=null)
            highlight(Utl.getImageViewOfSpot(GameManager.getTheGame().checker.getSpot()));
    }

    // ==============================================================================
    // Method : turnOn_highlightChecks()
    //
    // Purpose : It turns on the switch_highlightChecks. This method is used by
    // MainStageController.set_checkBox_highlightChecks and
    // MainStageController.set_checkMenuItem_highlightChecks methods.
    //
    // =============================================================================
    public static void turnOn_highlightChecks()
    {
        if (!switch_highlightChecks)
            switch_highlightChecks = true;
    }

    // ==============================================================================
    // Method : turnOff_highlightChecks()
    //
    // Purpose : It turns off the switch_highlightChecks. This method is used by
    // MainStageController.set_checkBox_highlightChecks and
    // MainStageController.set_checkMenuItem_highlightChecks methods.
    //
    // =============================================================================
    public static void turnOff_highlightChecks()
    {
        if (switch_highlightChecks)
            switch_highlightChecks = false;
    }

    // =====================================================================================<<<  >>>=== \\



    // ==============================================================================<<<  sound effect Methods  >>>=== \\

    // ==============================================================================
    // Method : isOn_soundEffects()
    //
    // Purpose : This method is used to determine whether the isOn_switch_soundEffects
    // is on or off.
    //
    // =============================================================================
    private static boolean isOn_soundEffects()
    {
        return switch_soundEffects;

    }

    // ==============================================================================
    // Method : turnOn_soundEffects()
    //
    // Purpose : It turns on the switch_soundEffects. This method is used by
    // MainStageController.set_checkBox_switch_soundEffects and
    // MainStageController.set_checkMenuItem_switch_soundEffects methods.
    //
    // =============================================================================
    public static void turnOn_soundEffects()
    {
        if (!switch_soundEffects)
            switch_soundEffects = true;
    }

    // ==============================================================================
    // Method : turnOff_soundEffects()
    //
    // Purpose : It turns off the switch_soundEffects. This method is used by
    // MainStageController.set_checkBox_switch_soundEffects and
    // MainStageController.set_checkMenuItem_switch_soundEffects methods.
    //
    // =============================================================================
    public static void turnOff_soundEffects()
    {
        if (switch_soundEffects)
            switch_soundEffects = false;
    }

    // ==============================================================================
    // Method : play()
    //
    // Purpose : The method plays a sound effect which is determined in Sound class
    // if the switch is on.
    //
    // =============================================================================
    public static void playSoundEffect()
    {
        if (isOn_soundEffects())
            Sounds.playSound();

    }

    // ==================================================================================<<<  comments Methods  >>>=== \\

    // ==============================================================================
    // Method : isOn_comments()
    //
    // Purpose : It determines whether the switch_comments is on or off.
    //
    // =============================================================================
    private static boolean isOn_comments()
    {
        return switch_comments;
    }

    // ==============================================================================
    // Method : turnOn_comments()
    //
    // Purpose : It turns on the switch_comments. This method is used by
    // MainStageController.set_checkBox_comments and
    // MainStageController.set_checkMenuItem_comments methods
    //
    // =============================================================================
    public static void turnOn_comments()
    {
        switch_comments = true;
    }

    // ==============================================================================
    // Method : turnOff_comments()
    //
    // Purpose : It turns off the switch_comments. This method is used by
    // MainStageController.set_checkBox_comments and
    // MainStageController.set_checkMenuItem_comments methods.
    //
    // =============================================================================
    public static void turnOff_comments()
    {
        switch_comments = false;
    }

    // ==============================================================================
    // Method : comments(Label)
    //
    // Purpose : It will set the Game.comment on the label.
    //
    // =============================================================================
    public static void comments(Label label)
    {
        if (GameManager.getTheGame()!=null && isOn_comments())
            label.setText(GameManager.getTheGame().getComment());
        else
            label.setText("");
    }

    // ======================================================================================<<<  note Methods  >>>=== \\


    // ==============================================================================
    // Method : isOn_notes()
    //
    // Purpose : It determines whether the switch_notes is on or off.
    //
    // =============================================================================
    private static boolean isOn_notes()
    {
        return switch_notes;
    }

    // ==============================================================================
    // Method : turnOn_notes()
    //
    // Purpose : It turns on the switch_comments. This method is used by
    // MainStageController.set_checkBox_notes and
    // MainStageController.set_checkMenuItem_notes methods
    //
    // =============================================================================
    public static void turnOn_notes()
    {
        switch_notes = true;
    }

    // ==============================================================================
    // Method : turnOff_notes()
    //
    // Purpose : It turns off the switch_notes. This method is used by
    // MainStageController.set_checkBox_notes and
    // MainStageController.set_checkMenuItem_notes methods.
    //
    // =============================================================================
    public static void turnOff_notes()
    {
        switch_notes = false;
    }


    public static void notes(TextArea textArea)
    {
        if (isOn_notes() && GameManager.getTheGame()!=null)
        {
            textArea.setText(GameManager.getTheGame().getNote());
            textArea.setVisible(true);
        }
        else
        {
            textArea.setText("");
            textArea.setVisible(false);
        }
    }










}
