package mos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainStage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainStage.fxml"));
        primaryStage.setTitle("My Chess");
        primaryStage.setScene(new Scene(root, 840, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
