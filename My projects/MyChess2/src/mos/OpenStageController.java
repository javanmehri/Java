package mos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Created by MOS on 2016-08-27.
 */

public class OpenStageController {

    @FXML
    Button button_cancel = new Button();

    @FXML
    Button button_load = new Button();


    @FXML
    private void set_load()
    {

    }


    @FXML
    private void set_cancel()
    {
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
