package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DictionaryApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML_File/Main.fxml"));
        primaryStage.setTitle("En - Vi Dictionary");
        primaryStage.setScene(new Scene(root, 700, 450));
        primaryStage.show();
    }
}