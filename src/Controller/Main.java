package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.geometry.*;
import javafx.scene.layout.VBox;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Create the FXMLLoader
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));


        // Create the Scene
        Scene scene = new Scene(root);
        // Set the Scene to the Stage
        stage.setScene(scene);
        // Set the Title to the Stage
        stage.setTitle("A FXML Example with a Controller");
        // Display the Stage
        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
