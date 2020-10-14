package Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.ArrayList;

public class HomeController {
    @FXML
    private MainController mainController;

    /*LEFT PANE*/
    @FXML
    private ListView<String> searchList;
    @FXML
    private TextField inputData;
    @FXML
    private WebView webView;
    private WebEngine engine;

    /* show searchlist */
    public void preWordSearch(String s) {
        ArrayList<String> preWord = this.mainController.dict.searchPrefix(s);
        searchList.getItems().setAll(preWord);
    }

    /* show content of a word. */
    public void showRight() {
        starButton.setVisible(true);
        String inputText = inputData.getText();
        String meaning = this.mainController.dict.getMeaning(inputText);
        if (meaning.equals("-1")) {
            engine.loadContent("<h1> NOT FOUND! </h1>");
        } else {
            engine.loadContent(meaning);
        }
    }

    @FXML
    private Button speakerButton;

    /* voice. */
    @FXML
    void speaker(ActionEvent event) {
        if (event.getSource() == speakerButton) {
            String s = inputData.getText();
            try {
                MicrosoftSpeechAPI.Read(s, "en-US");
            } catch (Exception e) {
            }
        }
    }

    /* handle search key event. */
    @FXML
    public void startSearch(KeyEvent event) {
        if (event.getSource() == inputData) {
            searchList.getItems().clear();
            String inputText = inputData.getText();
            if (inputText.equals("")) {
                ArrayList<String> allWord = this.mainController.dict.showWordList();
                searchList.getItems().setAll(allWord);
            } else {
                preWordSearch(inputText);
            }

        }
    }

    /* select a word in searchlist. */
    @FXML
    public void selectWordList(MouseEvent event) {
        String inputText = searchList.getSelectionModel().getSelectedItem();
        if (inputText == null) return;
        inputData.setText(inputText);
        preWordSearch(inputText);
        showRight();
    }

    @FXML
    private Button starButton;

    /* mark a word as favourite. */
    public void starWord(ActionEvent event) {
        if (event.getSource() == starButton) {
            boolean ok = this.mainController.dict.insertToBookMark(inputData.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Message");
            String status;
            if (!ok) {
                status = "Existed In Bookmark!";
            } else {
                status = "Inserted to Bookmark!";
            }
            alert.setContentText(status);
            alert.showAndWait();
        }
    }

    /* init state. */
    @FXML
    public void Run(MainController currentState) {
        this.mainController = currentState;
        inputData.clear();
        engine = webView.getEngine();
        engine.load("");
        ArrayList<String> allWord = this.mainController.dict.showWordList();
        searchList.getItems().setAll(allWord);
        starButton.setVisible(false);
    }

    @FXML
    void initialize() {

    }
}
