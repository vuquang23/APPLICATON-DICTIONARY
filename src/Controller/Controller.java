package Controller;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label myMessage;

    @FXML
    void getRandom(ActionEvent event) {
        myMessage.setText("haha");
    }

    @FXML
    void initialize() {

    }
}
