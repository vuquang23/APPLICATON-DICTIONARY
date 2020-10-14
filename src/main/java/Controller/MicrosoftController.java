package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MicrosoftController {
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

    /* translate while typing. */
    @FXML
    public void typing(KeyEvent even) {
        if (even.getSource() == English) {
            String english = English.getText();
            String vietnamese = "";
            try {
                vietnamese = MicrosoftTranslatorAPI.Translate(english);
            } catch (Exception e) {
            }
            Vietnamese.setText(vietnamese);
        }
    }

    /* voice. */
    @FXML
    public void listen(ActionEvent event) {
        if (event.getSource() == eListen) {
            String english = English.getText();
            try {
                MicrosoftSpeechAPI.Read(english, "en-US");
            } catch (Exception e) {
            }
        } else {
            String vietnamese = Vietnamese.getText();
            try {
                MicrosoftSpeechAPI.Read(vietnamese, "vi-VN");
            } catch (Exception e) {
            }
        }
    }

    /* init state. */
    public void Run() {
        English.clear();
        Vietnamese.clear();
    }

    @FXML
    void initialize() {

    }
}
