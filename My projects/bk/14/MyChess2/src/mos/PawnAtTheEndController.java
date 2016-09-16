package mos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOS on 2016-09-11.
 */

public class PawnAtTheEndController {

    @FXML
    Button button_replace = new Button();

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


    @FXML
    private void set_replace()
    {
        Player player = GameManager.getTheGame().getActivePlayer();
        Board board = GameManager.getTheGame().getBoard();
        board.spotOfPawnAtTheEnd.occupy(player.get_PieceBag().pick(get_selected()));

        ScreenBoard.updateTheScreen();
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
            if (options[i]!=null)
                observableList.add(options[i]);
        }
    }

    private String get_selected()
    {
        String selected;
        selected = (String)listView.getSelectionModel().getSelectedItem();
        return selected;
    }

}
