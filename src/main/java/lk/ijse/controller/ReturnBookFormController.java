package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

public class ReturnBookFormController {
    @FXML
    private TableColumn<?, ?> ColBarrowDate;

    @FXML
    private TableColumn<?, ?> ColBookId;

    @FXML
    private TableColumn<?, ?> ColGenre;

    @FXML
    private TableColumn<?, ?> ColReturnDate;

    @FXML
    private TableColumn<?, ?> ColTitle;

    @FXML
    private JFXButton btnReturn;

    @FXML
    private Label lblBookId;

    @FXML
    private Label lblReturnDate;

    @FXML
    private Label lblTitle;

    @FXML
    void btnReturnOnAction(ActionEvent event) {

    }
}
