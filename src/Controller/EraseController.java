package Controller;

import javafx.fxml.FXML;
import Dict.*;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;

public class EraseController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    @FXML
    private MainController mainController;
    @FXML
    private TextField word;
    @FXML
    private Button eraseButton;

    @FXML
    private void eraseWord(ActionEvent action) {
        if (action.getSource() == eraseButton) {
            String editWord = word.getText();
            this.mainController.dict.eraseWord(editWord);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Message");
            alert.setContentText("Erase Word Successully!");
            alert.showAndWait();
            word.clear();
        }
    }

    public void Run(MainController currentState) {
        mainController = currentState;
        word.clear();
    }

    @FXML
    void initialize() {

    }
}
