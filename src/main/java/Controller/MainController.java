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

    /* set up search func. */
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


    /*set up edit func. */
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


    /* set up add func. */
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


    /* set up erase func. */
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


    /* set up Favourite func. */
    @FXML
    private Button bookmarkButton;
    @FXML
    private AnchorPane bookmarkPane;
    @FXML
    private BookmarkController bookmarkController;
    public void showbookmarkPane() {
        AnchorPane anchorpane = new AnchorPane();
        AnchorPane.setTopAnchor(bookmarkPane, 0.0);
        AnchorPane.setLeftAnchor(bookmarkPane, 0.0);
        AnchorPane.setRightAnchor(bookmarkPane, 0.0);
        AnchorPane.setBottomAnchor(bookmarkPane, 0.0);
        this.contentPane.getChildren().setAll(bookmarkPane);
        anchorpane = null;
    }


    /* set up with API */
    @FXML
    private Button microsoftButton;
    @FXML
    private AnchorPane microsoftPane;
    @FXML
    private MicrosoftController microsoftController;
    public void showmicrosoftPane() {
        AnchorPane anchorpane = new AnchorPane();
        AnchorPane.setTopAnchor(microsoftPane, 0.0);
        AnchorPane.setLeftAnchor(microsoftPane, 0.0);
        AnchorPane.setRightAnchor(microsoftPane, 0.0);
        AnchorPane.setBottomAnchor(microsoftPane, 0.0);
        this.contentPane.getChildren().setAll(microsoftPane);
        anchorpane = null;
    }


    /* set up back pane*/
    @FXML
    private Button backButton;
    @FXML
    private AnchorPane backPane;
    public void showbackPane() {
        AnchorPane anchorpane = new AnchorPane();
        AnchorPane.setTopAnchor(backPane, 0.0);
        AnchorPane.setLeftAnchor(backPane, 0.0);
        AnchorPane.setRightAnchor(backPane, 0.0);
        AnchorPane.setBottomAnchor(backPane, 0.0);
        this.contentPane.getChildren().setAll(backPane);
        anchorpane = null;
    }

    /* options. */
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
        } else if (action.getSource() == microsoftButton) {
            showmicrosoftPane();
            microsoftController.Run();
        } else if (action.getSource() == bookmarkButton) {
            showbookmarkPane();
            bookmarkController.Run(this);
        } else {
            showbackPane();
        }
    }

    /* init. */
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

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FXML_File/Microsoft.fxml"));
            microsoftPane = fxmlLoader.load();
            microsoftController = fxmlLoader.getController();
        } catch (IOException e) {
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FXML_File/Back.fxml"));
            backPane = fxmlLoader.load();
        } catch (IOException e) {
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FXML_File/Bookmark.fxml"));
            bookmarkPane = fxmlLoader.load();
            bookmarkController = fxmlLoader.getController();
        } catch (IOException e) {
        }

        showbackPane();
    }

}
