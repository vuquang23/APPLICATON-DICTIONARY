package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

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

    /* Add a Word to Dict. */
    @FXML
    private void addWord(ActionEvent action) {
        if (action.getSource() == addButton) {
            String editWord = target.getText();
            String byWord = explain.getText();
            int done = this.mainController.dict.insertAWord(editWord, byWord);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Message");
            String status = "";
            switch (done) {
                case 0:
                    status = "Error: Invalid Format Word!";
                    break;
                case 1:
                    status = "Error: Word Existed in Dictionary!";
                    break;
                default:
                    status = "Add Word Successfully!";
            }
            alert.setContentText(status);
            alert.showAndWait();
            target.clear();
            explain.clear();
        }
    }

    /* init state */
    public void Run(MainController currentState) {
        mainController = currentState;
        target.clear();
        explain.clear();
    }

    @FXML
    void initialize() {

    }
}
