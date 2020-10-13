package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.ArrayList;

public class BookmarkController {
    @FXML
    private MainController mainController;
    @FXML
    private ListView<String> bookmarkList;
    @FXML
    private WebView webView;
    private WebEngine engine;

    private String curString = "";

    public void showRight() {
        String inputText = curString;
        String meaning = this.mainController.dict.getMeaning(inputText);
        if (meaning.equals("-1")) {
            engine.loadContent("<h1> NOT FOUND! </h1>");
        } else {
            engine.loadContent(meaning);
        }
    }


    @FXML
    private Button speakerButton;
    @FXML
    private Button removeButton;

    @FXML
    void mouseEvent(ActionEvent event) {
        if (event.getSource() == speakerButton) {
            String s = curString;
            try {
                MicrosoftSpeechAPI.Read(s, "en-US");
            } catch (Exception e) {
            }

        } else {
            this.mainController.dict.removeFromBookMark(curString);
            reload();
        }
    }


    @FXML
    public void selectWordList(MouseEvent event) {
        String inputText = bookmarkList.getSelectionModel().getSelectedItem();
        if (inputText == null) return;
        curString = inputText;
        showRight();
    }

    void reload() {
        engine.load("");
        ArrayList<String> allWord = this.mainController.dict.showBookMark();
        bookmarkList.getItems().setAll(allWord);
        curString = "";
    }

    @FXML
    public void Run(MainController currentState) {
        this.mainController = currentState;
        engine = webView.getEngine();
        curString = "";
        reload();
    }

    @FXML
    void initialize() {

    }
}
