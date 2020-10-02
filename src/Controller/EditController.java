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

public class EditController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    @FXML
    private MainController mainController;
    @FXML
    private TextField edit;
    @FXML
    private TextField by;
    @FXML
    private Button changeButton;

    @FXML
    private void changeWord(ActionEvent action) {
        if (action.getSource() == changeButton) {
            String editWord = edit.getText();
            String byWord = by.getText();
            boolean check = this.mainController.dict.editWord(editWord, byWord);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Message");
            if (check == true) {
                alert.setContentText("Edit Word Successully!");
            } else {
                alert.setContentText("Error: Word Not In Dict!!!");
            }
            alert.showAndWait();
            edit.clear();
            by.clear();
        }
    }

    public void Run(MainController currentState) {
        mainController = currentState;
        edit.clear();
        by.clear();
    }

    @FXML
    void initialize() {

    }
}
