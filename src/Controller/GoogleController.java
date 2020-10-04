package Controller;

import Dict.*;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class GoogleController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField English;
    @FXML
    private TextField Vietnamese;
    @FXML
    private Button eListen;
    @FXML
    private Button vListen;

    @FXML
    public void typing(KeyEvent even) {
        if (even.getSource() == English) {
            String english = English.getText();
            String vietnamese = "";
            try {
                vietnamese = GoogleAPI.trans("en", "vi", english);
//                vietnamese = "ebcde";
            } catch (Exception e) {
            }
            Vietnamese.setText(vietnamese);
        }
    }

    @FXML
    public void listen(ActionEvent event) {
        if (event.getSource() == eListen) {
            String english = English.getText();
            try {
                GoogleAPI.read(english, "en");
            } catch (Exception e) {
            }
        } else {
            String vietnamese = Vietnamese.getText();
            try {
                GoogleAPI.read(vietnamese, "vi");
            } catch (Exception e) {
            }
        }
    }

    public void Run() {
        English.clear();
        Vietnamese.clear();
    }

    @FXML
    void initialize() {

    }
}
