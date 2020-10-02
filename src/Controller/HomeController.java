package Controller;
import Dict.*;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import javax.swing.*;
import java.util.ArrayList;

public class HomeController {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    @FXML
    private MainController mainController;

    /*LEFT PANE*/
    @FXML
    private ListView<String> searchList;
    @FXML
    private Button searchButton;
    @FXML
    private TextField inputData;
    @FXML
    private Label showWord;
    @FXML
    private Label showMeaning;

    public void preWordSearch(String s) {
        /*tra ve list cac tu cung tien to*/
        ArrayList<String> preWord = this.mainController.dict.searchPrefix(s);
        searchList.getItems().setAll(preWord);
    }

    public void showRight() {
        speakerButton.setVisible(true);
        String inputText = inputData.getText();
        ArrayList<String> meaning = this.mainController.dict.getMeaning(inputText);
        if (meaning.size() == 0) {
            showWord.setText("Not Found!");
            showMeaning.setText("");
        } else {
            showWord.setText(inputText);
            String ret = "";
            for (int i = 0; i < meaning.size(); ++i) {
                String[] WORD = meaning.get(i).split("-");
                for (int j = 0; j < WORD.length; ++j) {
                    ret += "- " + WORD[j].trim() + "\n";
                }
            }
            showMeaning.setText(ret);
        }
    }

    @FXML
    public void findMeaning(ActionEvent event) {
        if (event.getSource() == searchButton) {
            showRight();
        }
    }

    @FXML
    private Button speakerButton;

    @FXML
    void speaker(ActionEvent event) {
        if (event.getSource() == speakerButton) {
            String s = showWord.getText();
            try {
                GoogleAPI.read(s, "en");
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
        showWord.setText("");
        showMeaning.setText("");
        ArrayList<String> allWord = this.mainController.dict.showWordList();
        searchList.getItems().setAll(allWord);
        speakerButton.setVisible(false);
    }

    @FXML
    void initialize() {

    }
}
