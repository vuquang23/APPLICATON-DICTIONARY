package Controller;

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
    }

    @FXML
    public void Handle(ActionEvent action) {
        if (action.getSource() == homeButton) {
            showhomePane();
            homeController.Run(this);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FXML_File/Home.fxml"));
            homePane = fxmlLoader.load();
            homeController = fxmlLoader.getController();
        } catch (IOException e) {
            System.out.println("Error load search_pane.");
        }
    }

}
