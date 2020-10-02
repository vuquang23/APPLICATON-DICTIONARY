package Controller;
import javafx.fxml.FXML;
import Dict.*;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
    private Button EraseButton;

    @FXML
    private void eraseWord(ActionEvent action) {
        if (action.getSource() == EraseButton) {
            String editWord = word.getText();
            boolean check = this.mainController.dict.eraseWord(editWord);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Message");
            if (check) {
                alert.setContentText("Erase Word Successully!");
            } else {
                alert.setContentText("Error: Word Not In Dict!!!");
            }
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
