package mos;

//==============================================================================
// File         : MainStageController.java
//
// Current Author: Mostafa Javanmehri
//
// Contact Info: m.javanmehri@gmail.com
//
// Purpose : This class contains the main controller methods needed for MainStage.fxml
//
// Dependencies: Utl, Options, ScreenBoard
//
// Modification Log :
//    --> Created Jul-03-2016
//    --> Updated Aug-21-2016
//
// =============================================================================

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainStageController {

    // ----------------------------------------
    @FXML
    private Button button_startAnewGame = new Button();

    @FXML
    private TextArea textArea = new TextArea();

    @FXML
    Label comment = new Label();

    @FXML
    CheckBox checkBox_highlightTheRoute = new CheckBox();

    @FXML
    CheckBox checkBox_highlightChecks = new CheckBox();

    @FXML
    CheckBox checkBox_soundEffects = new CheckBox();

    @FXML
    CheckBox checkBox_comments = new CheckBox();

    @FXML
    CheckBox checkBox_notes = new CheckBox();

    @FXML
    CheckMenuItem checkMenuItem_highlightTheRoute = new CheckMenuItem();

    @FXML
    CheckMenuItem checkMenuItem_highlightChecks = new CheckMenuItem();

    @FXML
    CheckMenuItem checkMenuItem_soundEffects = new CheckMenuItem();

    @FXML
    CheckMenuItem checkMenuItem_comments = new CheckMenuItem();

    @FXML
    CheckMenuItem checkMenuItem_notes = new CheckMenuItem();

    // --------------- 1 ----------------------
    @FXML
    private ImageView A1 = new ImageView();

    @FXML
    private ImageView B1 = new ImageView();

    @FXML
    private ImageView C1 = new ImageView();

    @FXML
    private ImageView D1 = new ImageView();

    @FXML
    private ImageView E1 = new ImageView();

    @FXML
    private ImageView F1 = new ImageView();

    @FXML
    private ImageView G1 = new ImageView();

    @FXML
    private ImageView H1 = new ImageView();

    // --------------- 2 ----------------------
    @FXML
    private ImageView A2 = new ImageView();

    @FXML
    private ImageView B2 = new ImageView();

    @FXML
    private ImageView C2 = new ImageView();

    @FXML
    private ImageView D2 = new ImageView();

    @FXML
    private ImageView E2 = new ImageView();

    @FXML
    private ImageView F2 = new ImageView();

    @FXML
    private ImageView G2 = new ImageView();

    @FXML
    private ImageView H2 = new ImageView();

    // --------------- 3 ----------------------
    @FXML
    private ImageView A3 = new ImageView();

    @FXML
    private ImageView B3 = new ImageView();

    @FXML
    private ImageView C3 = new ImageView();

    @FXML
    private ImageView D3 = new ImageView();

    @FXML
    private ImageView E3 = new ImageView();

    @FXML
    private ImageView F3 = new ImageView();

    @FXML
    private ImageView G3 = new ImageView();

    @FXML
    private ImageView H3 = new ImageView();

    // --------------- 4 ----------------------
    @FXML
    private ImageView A4 = new ImageView();

    @FXML
    private ImageView B4 = new ImageView();

    @FXML
    private ImageView C4 = new ImageView();

    @FXML
    private ImageView D4 = new ImageView();

    @FXML
    private ImageView E4 = new ImageView();

    @FXML
    private ImageView F4 = new ImageView();

    @FXML
    private ImageView G4 = new ImageView();

    @FXML
    private ImageView H4 = new ImageView();

    // --------------- 5 ----------------------
    @FXML
    private ImageView A5 = new ImageView();

    @FXML
    private ImageView B5 = new ImageView();

    @FXML
    private ImageView C5 = new ImageView();

    @FXML
    private ImageView D5 = new ImageView();

    @FXML
    private ImageView E5 = new ImageView();

    @FXML
    private ImageView F5 = new ImageView();

    @FXML
    private ImageView G5 = new ImageView();

    @FXML
    private ImageView H5 = new ImageView();

    // --------------- 6 ----------------------
    @FXML
    private ImageView A6 = new ImageView();

    @FXML
    private ImageView B6 = new ImageView();

    @FXML
    private ImageView C6 = new ImageView();

    @FXML
    private ImageView D6 = new ImageView();

    @FXML
    private ImageView E6 = new ImageView();

    @FXML
    private ImageView F6 = new ImageView();

    @FXML
    private ImageView G6 = new ImageView();

    @FXML
    private ImageView H6 = new ImageView();

    // --------------- 7 ----------------------
    @FXML
    private ImageView A7 = new ImageView();

    @FXML
    private ImageView B7 = new ImageView();

    @FXML
    private ImageView C7 = new ImageView();

    @FXML
    private ImageView D7 = new ImageView();

    @FXML
    private ImageView E7 = new ImageView();

    @FXML
    private ImageView F7 = new ImageView();

    @FXML
    private ImageView G7 = new ImageView();

    @FXML
    private ImageView H7 = new ImageView();


    // --------------- 8 ----------------------
    @FXML
    private ImageView A8 = new ImageView();

    @FXML
    private ImageView B8 = new ImageView();

    @FXML
    private ImageView C8 = new ImageView();

    @FXML
    private ImageView D8 = new ImageView();

    @FXML
    private ImageView E8 = new ImageView();

    @FXML
    private ImageView F8 = new ImageView();

    @FXML
    private ImageView G8 = new ImageView();

    @FXML
    private ImageView H8 = new ImageView();

    // ----------------------------------------------------

    private static ImageView[][] imageViewsBoard = new ImageView[8][8];



    // ===================================================================
    //                            CONTROL METHODS
    // ===================================================================


    @FXML
    private void initialize() {
        setUpTheBoard();
        Options.notes(textArea);
    }


    @FXML
    private void mouseClick(Event event) throws IOException, CloneNotSupportedException, InterruptedException {

        ScreenBoard.clickOnBoard(event);

        Options.notes(textArea);
        Options.comments(comment);

    }


    @FXML
    private void mouseOn(Event event) throws CloneNotSupportedException {
        ScreenBoard.mouseOn(event);
    }


    @FXML
    private void mouseOut(Event event)
    {
        ScreenBoard.mouseOut(event);
    }


    @FXML
    private void set_button_startAnewGame()
    {
        if (GameManager.getTheGame()==null)
        {
            startAGame();
            Options.notes(textArea);

        }
        else
        {
            try{

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SaveStage.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                //stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("Save");
                stage.setScene(new Scene(root1));
                stage.setAlwaysOnTop(true);
                stage.setResizable(false);
                //stage.sizeToScene();

                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static void  startAGame()
    {
        ScreenBoard.start(imageViewsBoard);
    }

    @FXML
    private void set_checkBox_highlightTheRoute()
    {
        if (checkBox_highlightTheRoute.isSelected())
        {
            Options.turnOn_highlightTheRoute();
            checkMenuItem_highlightTheRoute.setSelected(true);
        }
        else
        {
            Options.turnOff_highlightTheRoute();
            checkMenuItem_highlightTheRoute.setSelected(false);
        }
    }

    @FXML
    private void set_checkMenuItem_highlightTheRoute()
    {
        if (checkMenuItem_highlightTheRoute.isSelected())
        {
            Options.turnOn_highlightTheRoute();
            checkBox_highlightTheRoute.setSelected(true);
        }
        else
        {
            Options.turnOff_highlightTheRoute();
            checkBox_highlightTheRoute.setSelected(false);
        }
    }

    @FXML
    private void set_checkBox_highlightChecks()
    {
        if (checkBox_highlightChecks.isSelected())
        {
            Options.turnOn_highlightChecks();
            checkMenuItem_highlightChecks.setSelected(true);
        }
        else
        {
            Options.turnOff_highlightChecks();
            checkMenuItem_highlightChecks.setSelected(false);
        }

        watchForCheck();
    }

    @FXML
    private void set_checkMenuItem_highlightChecks()
    {
        if (checkMenuItem_highlightChecks.isSelected())
        {
            Options.turnOn_highlightChecks();
            checkBox_highlightChecks.setSelected(true);
        }
        else
        {
            Options.turnOff_highlightChecks();
            checkBox_highlightChecks.setSelected(false);
        }

        watchForCheck();
    }

    @FXML
    private void set_checkBox_soundEffects()
    {
        if (checkBox_soundEffects.isSelected())
        {
            Options.turnOn_soundEffects();
            checkMenuItem_soundEffects.setSelected(true);
        }
        else
        {
            Options.turnOff_soundEffects();
            checkMenuItem_soundEffects.setSelected(false);
        }
    }

    @FXML
    private void set_checkMenuItem_soundEffects()
    {
        if (checkMenuItem_soundEffects.isSelected())
        {
            Options.turnOn_soundEffects();
            checkBox_soundEffects.setSelected(true);
        }
        else
        {
            Options.turnOff_soundEffects();
            checkBox_soundEffects.setSelected(false);
        }
    }

    @FXML
    private void set_checkBox_comments()
    {
        if (checkBox_comments.isSelected())
        {
             Options.turnOn_comments();
             checkMenuItem_comments.setSelected(true);
            Options.comments(comment);

        }
        else
        {
            Options.turnOff_comments();
            checkMenuItem_comments.setSelected(false);
            Options.comments(comment);
        }
    }

    @FXML
    private void set_checkMenuItem_comments()
    {
        if (checkMenuItem_comments.isSelected())
        {
            Options.turnOn_comments();
            checkBox_comments.setSelected(true);
            Options.comments(comment);
        }
        else
        {
            Options.turnOff_comments();
            checkBox_comments.setSelected(false);
            Options.comments(comment);
        }
    }

    @FXML
    private void set_checkBox_notes()
    {
        if (checkBox_notes.isSelected())
        {
            Options.turnOn_notes();
            checkMenuItem_notes.setSelected(true);
        }
        else
        {
            Options.turnOff_notes();
            checkMenuItem_notes.setSelected(false);
        }
        Options.notes(textArea);
    }

    @FXML
    private void set_checkMenuItem_notes()
    {
        if (checkMenuItem_notes.isSelected())
        {
            Options.turnOn_notes();
            checkBox_notes.setSelected(true);
        }
        else
        {
            Options.turnOff_notes();
            checkBox_notes.setSelected(false);
        }
        Options.notes(textArea);
    }

    @FXML
    private void set_menuItem_openAGame()
    {

            try{


                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OpenStage.fxml"));
                Parent root2 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("Load a Game");
                stage.setScene(new Scene(root2));
                stage.setAlwaysOnTop(true);
                stage.setResizable(false);
                stage.show();


            } catch (IOException e) {
                e.printStackTrace();
            }


    }

    @FXML
    private void exit()
    {
        Platform.exit();
        System.exit(0);
    }

    // ===================================================================
    //                            HELPER METHODS
    // ===================================================================


    private void watchForCheck()
    {
        if (GameManager.getTheGame()!=null && Options.isOn_highlightChecks())
        {
            ScreenBoard.watchForCheck();
        }
        else
            Options.cancel_all_highlights(imageViewsBoard);

    }

    private void setUpTheBoard() {
        imageViewsBoard[0][0] = A1;
        imageViewsBoard[0][1] = B1;
        imageViewsBoard[0][2] = C1;
        imageViewsBoard[0][3] = D1;
        imageViewsBoard[0][4] = E1;
        imageViewsBoard[0][5] = F1;
        imageViewsBoard[0][6] = G1;
        imageViewsBoard[0][7] = H1;

        imageViewsBoard[1][0] = A2;
        imageViewsBoard[1][1] = B2;
        imageViewsBoard[1][2] = C2;
        imageViewsBoard[1][3] = D2;
        imageViewsBoard[1][4] = E2;
        imageViewsBoard[1][5] = F2;
        imageViewsBoard[1][6] = G2;
        imageViewsBoard[1][7] = H2;

        imageViewsBoard[2][0] = A3;
        imageViewsBoard[2][1] = B3;
        imageViewsBoard[2][2] = C3;
        imageViewsBoard[2][3] = D3;
        imageViewsBoard[2][4] = E3;
        imageViewsBoard[2][5] = F3;
        imageViewsBoard[2][6] = G3;
        imageViewsBoard[2][7] = H3;

        imageViewsBoard[3][0] = A4;
        imageViewsBoard[3][1] = B4;
        imageViewsBoard[3][2] = C4;
        imageViewsBoard[3][3] = D4;
        imageViewsBoard[3][4] = E4;
        imageViewsBoard[3][5] = F4;
        imageViewsBoard[3][6] = G4;
        imageViewsBoard[3][7] = H4;

        imageViewsBoard[4][0] = A5;
        imageViewsBoard[4][1] = B5;
        imageViewsBoard[4][2] = C5;
        imageViewsBoard[4][3] = D5;
        imageViewsBoard[4][4] = E5;
        imageViewsBoard[4][5] = F5;
        imageViewsBoard[4][6] = G5;
        imageViewsBoard[4][7] = H5;

        imageViewsBoard[5][0] = A6;
        imageViewsBoard[5][1] = B6;
        imageViewsBoard[5][2] = C6;
        imageViewsBoard[5][3] = D6;
        imageViewsBoard[5][4] = E6;
        imageViewsBoard[5][5] = F6;
        imageViewsBoard[5][6] = G6;
        imageViewsBoard[5][7] = H6;

        imageViewsBoard[6][0] = A7;
        imageViewsBoard[6][1] = B7;
        imageViewsBoard[6][2] = C7;
        imageViewsBoard[6][3] = D7;
        imageViewsBoard[6][4] = E7;
        imageViewsBoard[6][5] = F7;
        imageViewsBoard[6][6] = G7;
        imageViewsBoard[6][7] = H7;

        imageViewsBoard[7][0] = A8;
        imageViewsBoard[7][1] = B8;
        imageViewsBoard[7][2] = C8;
        imageViewsBoard[7][3] = D8;
        imageViewsBoard[7][4] = E8;
        imageViewsBoard[7][5] = F8;
        imageViewsBoard[7][6] = G8;
        imageViewsBoard[7][7] = H8;

        setUpTiles();
    }


    private void setUpTiles() {
        for (int i = 0; i<8; i++)
        {
            for(int j = 0; j<8; j++)
            {

                if ((i+j)%2 == 0)
                {
                    imageViewsBoard[i][j].setImage(Img.Tile_Black);
                }
                else
                {
                    imageViewsBoard[i][j].setImage(Img.Tile_White);
                }


            }
        }
    }


    // ===================================================================
    //                            PUBLIC METHODS
    // ===================================================================

    public static ImageView[][] getImageViewsBoard() { return imageViewsBoard; }




}
