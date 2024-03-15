package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BookTranscationFormController {
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
    private TableView<?> tblTransaction;
}
