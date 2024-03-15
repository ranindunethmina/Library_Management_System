package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.dto.BookDTO;
import lk.ijse.notification.PopUps;
import lk.ijse.service.BookService;
import lk.ijse.service.ServiceFactory;
import lk.ijse.tm.BookTM;
import lk.ijse.util.AlertTypes;

import java.util.Optional;
import java.util.regex.Pattern;

public class BookManageFormController {
    @FXML
    private JFXButton btnAdd;

    @FXML
    private ComboBox<String> cmbGenre;

    @FXML
    private ComboBox<String> cmbQty;

    @FXML
    private TableColumn<?, ?> colBookAuthor;

    @FXML
    private TableColumn<?, ?> colBookQty;

    @FXML
    private TableColumn<?, ?> colBookGenre;

    @FXML
    private TableColumn<?, ?> colBookTitle;

    @FXML
    private TableColumn<?, ?> colISBN;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<BookTM> tblBooks;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtBookTitle;

    @FXML
    private TextField txtIsbn;

    BookService bookService = (BookService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BOOK);

    public void initialize(){
        loadBookGenresToGenreComboBox();
        setValuesToStatusComboBox();
        setCellValueFactory();
        getAllBooks();
        tableRowSelectAction();
        setDisableItemTrue();
    }

    private void tableRowSelectAction() {
        tblBooks.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setDisableItemFlase();
            BookTM selectedItem = (BookTM) tblBooks.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                return;
            }
            txtIsbn.setText(String.valueOf(selectedItem.getIsbn()));
            txtBookTitle.setText(selectedItem.getTitle());
            cmbGenre.setValue(selectedItem.getGenre());
            txtAuthor.setText(selectedItem.getAuthor());
            cmbQty.setValue(String.valueOf(selectedItem.getQty()));
        });
    }

    private void getAllBooks() {
        ObservableList<BookTM> bookTMSTMS = FXCollections.observableArrayList();
        for (BookDTO bookDTO : bookService.getAll()){
            bookTMSTMS.add(new BookTM(
                    bookDTO.getIsbn(),
                    bookDTO.getTitle(),
                    bookDTO.getGenre(),
                    bookDTO.getAuthor(),
                    bookDTO.getQty()
            ));
        }
        tblBooks.setItems(bookTMSTMS);
    }

    private void setCellValueFactory() {
        colISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colBookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colBookGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colBookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colBookQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    public void btnAddOnAction(ActionEvent event) {
        boolean isUserValidated = validateBook();
        if (isUserValidated) {

            Long isSaved = bookService.save(new BookDTO(
                    txtBookTitle.getText(),
                    txtAuthor.getText(),
                    String.valueOf(cmbGenre.getValue()),
                    Integer.parseInt(txtIsbn.getText()),
                    Integer.parseInt(String.valueOf(cmbQty.getValue()))
            ));
            if (isSaved > -1) {
                PopUps.popUps(AlertTypes.CONFORMATION, "Sucessful", "Book Added Sucessfully!");
                clearFields();
                getAllBooks();
            } else {
                PopUps.popUps(AlertTypes.CONFORMATION, "Unsucessful", "something went wrong!");
            }
            setDisableItemTrue();
        }
    }
    public void loadBookGenresToGenreComboBox(){
        String [] genres = {"Novel","Short Stories","History","Biography", "Educational"};
        ObservableList<String> obList = FXCollections.observableArrayList();
        for (String genre : genres){
            obList.add(genre);
        }
        cmbGenre.setItems(obList);
    }
    public void setValuesToStatusComboBox(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        int [] qty = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        for (int x : qty){
            obList.add(String.valueOf(x));
        }
        cmbQty.setItems(obList);
    }
    public void clearFields(){
        txtBookTitle.setText("");
        txtAuthor.setText("");
        cmbGenre.setItems(null);
        txtIsbn.setText("");
        cmbQty.setItems(null);
        datePicker.setValue(null);
    }

    public void btnUpdateOnAction(ActionEvent event) {
        boolean isUserValidated = validateBook();
        if (isUserValidated) {

            boolean isSaved = bookService.update(new BookDTO(
                    txtBookTitle.getText(),
                    txtAuthor.getText(),
                    String.valueOf(cmbGenre.getValue()),
                    Integer.parseInt(txtIsbn.getText()),
                    Integer.parseInt(String.valueOf(cmbQty.getValue()))
            ));
            if (isSaved) {
                PopUps.popUps(AlertTypes.CONFORMATION, "Sucessful", "Book Updated Sucessfully !");
                clearFields();
                getAllBooks();
            } else {
                PopUps.popUps(AlertTypes.CONFORMATION, "Unsucessful", "something went wrong !");
            }
            setDisableItemTrue();
        }
    }
    public void btnDeleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to Delete?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {

            boolean isdeleted = bookService.delete(get());
            if (isdeleted) {
                PopUps.popUps(AlertTypes.CONFORMATION, "Sucessful", "Book Deleted Sucessfully !");
                clearFields();
                getAllBooks();
            } else {
                PopUps.popUps(AlertTypes.CONFORMATION, "Unsucessful", "something went wrong !");
            }
            setDisableItemTrue();
        }
    }

    public void txtSearchOnAction(ActionEvent event) {

    }

    private BookDTO get() {
        String id = txtIsbn.getText();
        BookDTO bookDTO = bookService.get(Long.valueOf(id));
        return bookDTO;
    }
    public void btnAddNewBookAction(MouseEvent mouseEvent) {
        setDisableItemFlase();
    }
    private void setDisableItemFlase() {
        txtBookTitle.setDisable(false);
        txtIsbn.setDisable(false);
        txtAuthor.setDisable(false);
        cmbGenre.setDisable(false);
        cmbQty.setDisable(false);
    }
    private void setDisableItemTrue() {
        txtBookTitle.setDisable(true);
        txtIsbn.setDisable(true);
        txtAuthor.setDisable(true);
        cmbGenre.setDisable(true);
        cmbQty.setDisable(true);
    }
    private boolean validateBook(){
        String titleText = txtBookTitle.getText();
        boolean isBookNameValidated = Pattern.matches("[A-Za-z0-9/.\\s]{3,}", titleText);
        if (!isBookNameValidated) {
            PopUps.popUps(AlertTypes.ERROR, "Invalid", "Invalid Book Title");
            return false;
        }
        String author = txtAuthor.getText();
        boolean isauthorNameValidated = Pattern.matches("[A-Za-z0-9/.\\s]{3,}", author);
        if (!isauthorNameValidated) {
            PopUps.popUps(AlertTypes.ERROR, "Invalid", "Invalid Author Name");
            return false;
        }
        String isbnText = txtIsbn.getText();
        boolean isValidated = Pattern.matches("[0-9/.\\s]", isbnText);
        if (!isValidated) {
            PopUps.popUps(AlertTypes.ERROR, "Invalid", "Invalid ISBN");
            return false;
        }
        String qtyValue = cmbQty.getValue();
        boolean isqtyValidated = Pattern.matches("[0-9/.\\s]", qtyValue);
        if (!isqtyValidated) {
            PopUps.popUps(AlertTypes.ERROR, "Invalid", "Invalid QTY");
            return false;
        }
        String genreValue = cmbGenre.getValue();
        boolean isgenreValidated = Pattern.matches("[A-Za-z0-9/.\\s]{3,}", genreValue);
        if (!isgenreValidated) {
            PopUps.popUps(AlertTypes.ERROR, "Invalid", "Invalid Genre");
            return false;
        }
        return true;
    }
}