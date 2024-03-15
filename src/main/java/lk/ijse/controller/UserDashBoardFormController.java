package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.util.DateAndTime;
import lk.ijse.util.Navigation;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class UserDashBoardFormController {
    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private AnchorPane childPane;

    @FXML
    private ImageView imgHome;

    @FXML
    private ImageView imgLogOut;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane root;

    public void initialize() throws IOException {
        lblDate.setText(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
        DateAndTime.manageTime(lblTime);
        home();
    }

    private void home() throws IOException {
        setU("HomeForm");
    }

    public void setU(String location) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/" + location + ".fxml"));
        childPane.getChildren().removeAll();
        childPane.getChildren().setAll(fxml);
    }

    public void btnHomeOnAction(ActionEvent event) throws IOException {
        home();
    }

    public void btnLogOutOnAction(ActionEvent event) {
        btnLogOut.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to logout?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                try {
                    new Navigation().setUi("LoginForm",event);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void btnBarrowBookOnAction(ActionEvent event) throws IOException {
        setU("BarrowBookForm");
    }

    public void btnReturnBookOnAction(ActionEvent event) throws IOException {
        setU("ReturnBookForm");
    }

    public void btnBookTransacOnAction(ActionEvent event) throws IOException {
        setU("BookTranscationForm");
    }
}