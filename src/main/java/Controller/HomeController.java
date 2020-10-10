package Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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


    public void preWordSearch(String s) {
        /*tra ve list cac tu cung tien to*/
        ArrayList<String> preWord = this.mainController.dict.searchPrefix(s);
        searchList.getItems().setAll(preWord);
    }

    public void showRight() {
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

    @FXML
    public void selectWordList(MouseEvent event) {
        String inputText = searchList.getSelectionModel().getSelectedItem();
        if (inputText == null) return;
        inputData.setText(inputText);
        preWordSearch(inputText);
        showRight();
    }

    @FXML
    public void Run(MainController currentState) {
        this.mainController = currentState;
        inputData.clear();
        engine = webView.getEngine();
        engine.reload();
        ArrayList<String> allWord = this.mainController.dict.showWordList();
        searchList.getItems().setAll(allWord);
    }

    @FXML
    void initialize() {

    }
}
