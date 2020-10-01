package Controller;

import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;

import java.util.*;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class HomeController {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    private MainController mainPaneController;

    /*LEFT PANE*/
    @FXML
    private ListView<String> searchList;
    @FXML
    private Button searchButton;
    @FXML
    private TextField inputData;

//    public void preWordSearch(String s) {
//        /*tra ve list cac tu cung tien to*/
//        ArrayList <String> preWord = this.mainPaneController.;
//        searchList.getItems().setAll(preWord);
//    }
//
//    public void startSearch(KeyEvent event) {
//        if (event.getSource() == inputData) {
//            String inputText = inputData.getText();
//            if (inputText.isEmpty()) {
//                searchList.getItems().clear();
//            } else {
//                preWordSearch(inputText);
//            }
//        }
//    }
//
//    @FXML
//    public void selectWordFromsearchList(MouseEvent event) {
//        String inputText = searchList.getSelectionModel().getSelectedItem();
//        if (inputText == null) return;
//        inputData.setText(inputText);
//        preWordSearch(inputText);
//        searchMeaning(inputText);
//    }

    public void Run(MainController currentState) {
        this.mainPaneController = currentState;
    }

    @FXML
    void initialize() {

    }
}
