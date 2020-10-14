package Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EraseController {
    @FXML
    private MainController mainController;
    @FXML
    private TextField word;
    @FXML
    private Button EraseButton;

    /* erase a word from dict. */
    @FXML
    private void eraseWord(ActionEvent action) {
        if (action.getSource() == EraseButton) {
            String editWord = word.getText();
            int check = this.mainController.dict.eraseWord(editWord);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Message");
            String status;
            switch (check) {
                case 0:
                    status = "Error: Invalid Format Word!";
                    break;
                case 1:
                    status = "Error: Word Not Exist in Dictionary!";
                    break;
                default:
                    status = "Erased Successfully!";
            }
            alert.setContentText(status);
            alert.showAndWait();
            word.clear();
        }
    }

    /* init state. */
    public void Run(MainController currentState) {
        mainController = currentState;
        word.clear();
    }

    @FXML
    void initialize() {

    }
}
