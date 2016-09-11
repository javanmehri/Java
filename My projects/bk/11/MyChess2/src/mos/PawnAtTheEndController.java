package mos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOS on 2016-09-11.
 */

public class PawnAtTheEndController {

    @FXML
    Button button_select = new Button();

    @FXML
    Button button_cancel = new Button();

    @FXML
    ListView listView = new ListView();


    private List<String> list = new ArrayList<String>();
    private ObservableList<String> observableList = FXCollections.observableList(list);

    @FXML
    private void initialize()
    {
        list = new ArrayList<String>();
        observableList = FXCollections.observableList(list);

        list_PossibleOptions();
        listView.setItems(observableList);
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


    public void list_PossibleOptions()
    {
        String[] options = GameManager.getTheGame().getActivePlayer().get_PieceBag().get_types();

        for (int i =0; i< options.length; i++)
        {
            observableList.add(options[i]);
        }
    }


}
