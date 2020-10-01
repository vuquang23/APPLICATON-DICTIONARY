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

public class AddController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    @FXML
    private MainController mainController;
    @FXML
    private TextField target;
    @FXML
    private TextField explain;
    @FXML
    private Button addButton;

    @FXML
    private void addWord(ActionEvent action) {
        if (action.getSource() == addButton) {
            String editWord = target.getText();
            String byWord = explain.getText();
            this.mainController.dict.insertAWord(editWord, byWord);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Message");
            alert.setContentText("Add Word Successully!");
            alert.showAndWait();
            target.clear();
            explain.clear();
        }
    }

    public void Run(MainController currentState) {
        mainController = currentState;
        target.clear();
        explain.clear();
    }

    @FXML
    void initialize() {

    }
}
