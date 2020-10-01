package Controller;
import Dict.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private AnchorPane contentPane;

    /*Import dictionary*/
    public Dictionary dict;

    @FXML
    private Button homeButton;
    @FXML
    private AnchorPane homePane;
    @FXML
    private HomeController homeController;

    public void showhomePane() {
        AnchorPane anchorpane = new AnchorPane();
        AnchorPane.setTopAnchor(homePane, 0.0);
        AnchorPane.setLeftAnchor(homePane, 0.0);
        AnchorPane.setRightAnchor(homePane, 0.0);
        AnchorPane.setBottomAnchor(homePane, 0.0);
        this.contentPane.getChildren().setAll(homePane);
        anchorpane = null;
    }

    @FXML
    private Button editButton;
    @FXML
    private AnchorPane editPane;
    @FXML
    private EditController editController;

    public void showeditPane() {
        AnchorPane anchorpane = new AnchorPane();
        AnchorPane.setTopAnchor(editPane, 0.0);
        AnchorPane.setLeftAnchor(editPane, 0.0);
        AnchorPane.setRightAnchor(editPane, 0.0);
        AnchorPane.setBottomAnchor(editPane, 0.0);
        this.contentPane.getChildren().setAll(editPane);
        anchorpane = null;
    }

    @FXML
    private Button addButton;
    @FXML
    private AnchorPane addPane;
    @FXML
    private AddController addController;

    public void showaddPane() {
        AnchorPane anchorpane = new AnchorPane();
        AnchorPane.setTopAnchor(addPane, 0.0);
        AnchorPane.setLeftAnchor(addPane, 0.0);
        AnchorPane.setRightAnchor(addPane, 0.0);
        AnchorPane.setBottomAnchor(addPane, 0.0);
        this.contentPane.getChildren().setAll(addPane);
        anchorpane = null;
    }

    @FXML
    private Button eraseButton;
    @FXML
    private AnchorPane erasePane;
    @FXML
    private EraseController eraseController;

    public void showerasePane() {
        AnchorPane anchorpane = new AnchorPane();
        AnchorPane.setTopAnchor(erasePane, 0.0);
        AnchorPane.setLeftAnchor(erasePane, 0.0);
        AnchorPane.setRightAnchor(erasePane, 0.0);
        AnchorPane.setBottomAnchor(erasePane, 0.0);
        this.contentPane.getChildren().setAll(erasePane);
        anchorpane = null;
    }

    @FXML
    public void Handle(ActionEvent action) {
        if (action.getSource() == homeButton) {
            showhomePane();
            homeController.Run(this);
        } else if (action.getSource() == editButton) {
            showeditPane();
            editController.Run(this);
        } else if (action.getSource() == addButton) {
            showaddPane();
            addController.Run(this);
        } else if (action.getSource() == eraseButton) {
            showerasePane();
            eraseController.Run(this);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            dict = new Dictionary();
        } catch (Exception e) {
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FXML_File/Home.fxml"));
            homePane = fxmlLoader.load();
            homeController = fxmlLoader.getController();
        } catch (IOException e) {
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FXML_File/Edit.fxml"));
            editPane = fxmlLoader.load();
            editController = fxmlLoader.getController();
        } catch (IOException e) {
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FXML_File/Add.fxml"));
            addPane = fxmlLoader.load();
            addController = fxmlLoader.getController();
        } catch (IOException e) {
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FXML_File/Erase.fxml"));
            erasePane = fxmlLoader.load();
            eraseController = fxmlLoader.getController();
        } catch (IOException e) {
        }
    }

}
