package mos;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by MOS on 2016-08-27.
 */
public class SaveStageController {

    @FXML
    Button  button_save = new Button();

    @FXML
    Button button_cancel = new Button();

    @FXML
    TextField saveName = new TextField();


    @FXML
    private void setButton_cancel()
    {
        close();
    }

    @FXML
    private void setButton_save()
    {
        Game game = GameManager.getTheGame();
        String fileName = saveName.getText();

        try {

            // write object to file
            FileOutputStream fos = new FileOutputStream("./saves/"+fileName+".mos");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(game);
            oos.flush();
            oos.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        MainStageController.startAGame();
        close();

    }


    private void close()
    {
        // get a handle to the stage
        Stage stage = (Stage) button_cancel.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

}

