package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.dto.BranchDTO;
import lk.ijse.notification.PopUps;
import lk.ijse.service.BranchService;
import lk.ijse.service.ServiceFactory;
import lk.ijse.tm.BranchTM;
import lk.ijse.util.AlertTypes;

import java.util.Optional;
import java.util.regex.Pattern;

public class BranchManageFormController {
    @FXML
    private JFXButton btnSave;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<BranchTM> tblBranch;

    @FXML
    private TextField txtBranchContact;

    @FXML
    private Label lblBranchId;

    @FXML
    private TextField txtBranchLoacation;

    @FXML
    private TextField txtBranchName;


    BranchService branchService = (BranchService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BRANCH);

    public void initialize(){
        setDisableItemTrue();
        setCellValueFactory();
        getAllBranches();
        tableRowSelectAction();
    }

    public void btnSaveOnAction(ActionEvent event) {
        boolean isUserValidated = validateBranch();
        if (isUserValidated) {

            Long isSaved = branchService.save(new BranchDTO(
                    txtBranchName.getText(),
                    txtBranchContact.getText(),
                    txtBranchLoacation.getText()
            ));
            if (isSaved > -1) {
                PopUps.popUps(AlertTypes.CONFORMATION, "Sucessful", "Branch Added sucessfully!");
                clearFields();
            } else {
                PopUps.popUps(AlertTypes.CONFORMATION, "Unsucessful", "something went wrong!");
            }
            getAllBranches();
            setDisableItemTrue();
        }
    }

    public void btnUpdateOnAction(ActionEvent event) {
        boolean isUserValidated = validateBranch();
        if (isUserValidated) {

            boolean isSaved = branchService.update(new BranchDTO(
                    (long) Integer.parseInt(lblBranchId.getText()),
                    txtBranchName.getText(),
                    txtBranchContact.getText(),
                    txtBranchLoacation.getText()
            ));
            if (isSaved) {
                PopUps.popUps(AlertTypes.CONFORMATION, "Sucessful", "Branch Updated sucessfully!");
                clearFields();
            } else {
                PopUps.popUps(AlertTypes.CONFORMATION, "Unsucessful", "something went wrong!");
            }
            getAllBranches();
            setDisableItemTrue();
        }
    }

    private void tableRowSelectAction() {
        tblBranch.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setDisableItemFlase();
            BranchTM selectedItem = (BranchTM) tblBranch.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                return;
            }
            lblBranchId.setText(String.valueOf(selectedItem.getId()));
            txtBranchName.setText(selectedItem.getName());
            txtBranchLoacation.setText(selectedItem.getLocation());
            txtBranchContact.setText(selectedItem.getContact());
        });
    }

    public void btnDeleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to Delete?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {

            boolean delete = branchService.delete(get());
            if (delete) {
                PopUps.popUps(AlertTypes.CONFORMATION, "Sucessful", "Branch Delete Successful !");
                clearFields();
            } else {
                PopUps.popUps(AlertTypes.CONFORMATION, "Unsucessful", "something went wrong!");
            }
            getAllBranches();
            setDisableItemTrue();
        }
    }

    private BranchDTO get() {
        String id = lblBranchId.getText();
        BranchDTO branchDTO = branchService.get(Long.valueOf(id));
        return branchDTO;
    }

    public void btnAddNewBranchAction(MouseEvent mouseEvent) {
        setDisableItemFlase();
    }
    private void setDisableItemFlase() {
        txtBranchName.setDisable(false);
        txtBranchContact.setDisable(false);
        txtBranchLoacation.setDisable(false);
        btnSave.setDisable(false);
        btnSave.setOpacity(1);
    }
    private void setDisableItemTrue() {
        txtBranchName.setDisable(true);
        txtBranchContact.setDisable(true);
        txtBranchLoacation.setDisable(true);
        btnSave.setDisable(true);
        btnSave.setOpacity(0.5);
    }
    private void clearFields() {
        lblBranchId.setText("");
        txtBranchName.setText("");
        txtBranchContact.setText("");
        txtBranchLoacation.setText("");
    }
    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
    }
    private void getAllBranches() {
        ObservableList<BranchTM> branchTMS = FXCollections.observableArrayList();
        for (BranchDTO branchDTO : branchService.getAll()){
            branchTMS.add(new BranchTM(
                    branchDTO.getId(),
                    branchDTO.getName(),
                    branchDTO.getContact(),
                    branchDTO.getLocation()
            ));
        }
        tblBranch.setItems(branchTMS);
    }
    private boolean validateBranch(){
        String nameText = txtBranchName.getText();
        boolean isNameValidated = Pattern.matches("[A-Za-z/.\\s]{3,}", nameText);
        if (!isNameValidated) {
            PopUps.popUps(AlertTypes.ERROR, "Invalid", "Invalid Branch Name");
            return false;
        }
        String cnText = txtBranchContact.getText();
        boolean isCNValidated = Pattern.matches("[0][0-9]{9}", cnText);
        if (!isCNValidated) {
            PopUps.popUps(AlertTypes.ERROR, "Invalid", "Invalid Branch Number");
            return false;
        }
        String loacationText = txtBranchLoacation.getText();
        boolean isLocationValidated = Pattern.matches("[A-Za-z0-9/.\\s]{3,}", loacationText);
        if (!isLocationValidated) {
            PopUps.popUps(AlertTypes.ERROR, "Invalid", "Invalid Branch Address");
            return false;
        }
        return true;
    }
}
