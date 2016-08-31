package mos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOS on 2016-08-27.
 */

public class OpenStageController {

    @FXML
    Button button_cancel = new Button();

    @FXML
    Button button_load = new Button();

    @FXML
    ListView listView = new ListView();


    final File folder = new File("./saves/");

    List<String> list = new ArrayList<String>();
    ObservableList<String> observableList = FXCollections.observableList(list);

    // ObservableArray filenames = new ObservableArray();

    @FXML
    private void initialize()
    {
        listFilesForFolder(folder);
        listView.setItems(observableList);
    }

    @FXML
    private void set_load()
    {
        String selectedFile;
        selectedFile = (String)listView.getSelectionModel().getSelectedItem();
        //System.out.println("./saves/"+selectedFile+".mos");

        try {

            FileInputStream fis = new FileInputStream("./saves/"+selectedFile+".mos");
            ObjectInputStream oin = new ObjectInputStream(fis);
            Game game = (Game) oin.readObject();
            GameManager.setGame(game);

            ScreenBoard.updateTheScreen();
            close();

            //System.out.println();

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void set_delete()
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


    public void listFilesForFolder(final File folder) {

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory())
            {
                listFilesForFolder(fileEntry);
            }
            else
            {
                String fullName = fileEntry.getName();
                //observableList.add(get_nameOf_MOS_files(fullName));

                if (is_MOS_files(fullName))
                {
                    observableList.add(get_nameOf_MOS_files(fullName));

                }
            }
        }
    }


    private boolean is_MOS_files(String file)
    {
        String name = "";
        String type = "";
        Character ch;

        for (int i=0; i<file.length() ; i++)
        {
            ch = file.charAt(i);
            if (ch.equals('.'))
            {
                name = file.substring(0,i);
                type = file.substring(i+1);
                break;
            }
        }

        if (type.equals("mos"))
            return true;
        else
            return false;
    }


    private String get_nameOf_MOS_files(String file)
    {
        String name = "";
        String type = "";
        Character ch;

        System.out.println(" full name:"+file);

        for (int i=0; i<file.length() ; i++)
        {
            ch = file.charAt(i);
            if (ch.equals('.'))
            {
                name = file.substring(0,i);
                type = file.substring(i+1);
                System.out.println(" name:<"+name+">");
                System.out.println(" type:<"+type+">");
                if (type.equals("mos"))
                    return name;
            }
        }

        return null;

    }





}
