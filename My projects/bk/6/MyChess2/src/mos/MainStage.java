package mos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class MainStage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainStage.fxml"));
        primaryStage.setTitle("My Chess");
        primaryStage.setScene(new Scene(root, 1000, 740));

        /*
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        */

        primaryStage.show();
    }


    public static void main(String[] args) throws FileNotFoundException {

        OutputStream out = new FileOutputStream("log.txt");
        PrintStream stream = new PrintStream(out);
        System.setOut(stream);


        //System.setIn(new FileInputStream(filename));

        //System.out.println("********");
        launch(args);
    }
}
