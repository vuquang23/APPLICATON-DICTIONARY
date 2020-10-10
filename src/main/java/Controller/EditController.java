package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditController {
    @FXML
    private MainController mainController;
    @FXML
    private TextField Target;
    @FXML
    private TextField newTarget;
    @FXML
    private TextField newExplain;
    @FXML
    private Button changeButton;

    @FXML
    private void changeWord(ActionEvent action) {
        if (action.getSource() == changeButton) {
            String target = Target.getText();
            String newtarget = newTarget.getText();
            String newexplain = newExplain.getText();
            int check = this.mainController.dict.editWord(target, newtarget, newexplain);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Message");
            String status;

            switch (check) {
                case 1:
                    status = "Error: Invalid Format Word!";
                    break;
                case 2:
                    status = "Error: Word Need To Edit Not In Dictionary!";
                    break;
                case 3:
                    status = "Error: New Word Existed In Dictionary!";
                    break;
                default:
                    status = "Edit Successfully!";
            }

            alert.setContentText(status);
            alert.showAndWait();
            Target.clear();
            newTarget.clear();
            newExplain.clear();
        }
    }

    public void Run(MainController currentState) {
        mainController = currentState;
        Target.clear();
        newTarget.clear();
        newExplain.clear();
    }

    @FXML
    void initialize() {

    }
}
