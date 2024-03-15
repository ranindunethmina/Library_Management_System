package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.BranchDTO;
import lk.ijse.projection.loadBranchName;
import lk.ijse.service.BookService;
import lk.ijse.service.BranchService;
import lk.ijse.service.ServiceFactory;

import java.util.List;

public class BarrowBookFormController {
    @FXML
    private TableColumn<?, ?> ColAuthor;

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
    private JFXButton btnBarrow;

    @FXML
    private JFXComboBox<?> cmbChooseBranch;

    @FXML
    private Label lblAuthor;

    @FXML
    private Label lblAvailability;

    @FXML
    private Label lblBookId;

    @FXML
    private Label lblGenre;

    @FXML
    private TableView<?> tblBarrowBook;

    @FXML
    private TextField txtBookTitle;


    BookService bookService = (BookService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BOOK);
    BranchService branchService = (BranchService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BRANCH);
    
    public void initialize(){
        loadBranchName();
    }

    private void loadBranchName() {
        List<loadBranchName> idsAndTitles = branchService.getbranchName();
        ObservableList obList = FXCollections.observableArrayList();
        for (loadBranchName bookIdsAndTitles : idsAndTitles){
            obList.add(bookIdsAndTitles);
        }
        cmbChooseBranch.setItems(obList);
    }

    @FXML
    void btnBarrowOnAction(ActionEvent event) {

    }

    @FXML
    void cmbChooseBranchOnAction(ActionEvent event) {
        setValuesToFields(getBranch());
    }

    private void setValuesToFields(BranchDTO branchDTO) {
        cmbChooseBranch.setId(branchDTO.getName());
    }

    private BranchDTO getBranch() {
        BranchDTO branchDTO = branchService.get(split(String.valueOf(cmbChooseBranch.getValue())));
        return  branchDTO;
    }
    private long split(String value){
        String[] parts = value.split(" ");
        System.out.println(parts[0]);
        return Long.parseLong(parts[0]);
    }
    @FXML
    void txtSearchOnAction(ActionEvent event) {
        BookDTO bookDTO = bookService.get(Long.valueOf((txtBookTitle.getText())));
        if (bookDTO != null) {
            lblBookId.setText(String.valueOf(bookDTO.getIsbn()));
            lblGenre.setText(bookDTO.getGenre());
            lblAuthor.setText(bookDTO.getAuthor());
            lblAvailability.setText(String.valueOf(bookDTO.getQty()));
        }
        new Alert(Alert.AlertType.ERROR, "something happened!").show();
    }
}
