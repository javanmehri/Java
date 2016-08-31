package mos;

//==============================================================================
// File         : MainStage.java
//
// Current Author: Mostafa Javanmehri
//
// Contact Info: m.javanmehri@gmail.com
//
// Purpose : This is the main class of the application which contains the main
// method.
//
// Dependencies:
//
// Modification Log :
//    --> Created Jul-03-2016
//    --> Updated Aug-21-2016
//
// =============================================================================


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class MainStage extends Application {

    public static Stage saveDialog;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("MainStage.fxml"));
        primaryStage.setTitle("My Chess");
        primaryStage.setScene(new Scene(root, 1000, 740));

        primaryStage.show();
    }


    public static void main(String[] args) throws FileNotFoundException {

        OutputStream out = new FileOutputStream("log.txt");
        PrintStream stream = new PrintStream(out);
        System.setOut(stream);

        launch(args);
    }
}
