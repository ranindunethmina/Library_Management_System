package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import lk.ijse.dto.UserDTO;
import lk.ijse.notification.PopUps;
import lk.ijse.projection.UserIds;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.UserService;
import lk.ijse.util.AlertTypes;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class UserUpdateFormController {
    @FXML
    private ComboBox<String> cmbUserIds;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNo;

    @FXML
    private TextField txtUserName;

    @FXML
    private ImageView wrongID;

    UserService userService = (UserService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.USER);

    public void initialize(){
        loadAllIds();
    }

    private void loadAllIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<UserIds> allIds = userService.getAllIds();
        for (UserIds ids : allIds){
            obList.add(String.valueOf(ids.getId()));
        }
        cmbUserIds.setItems(obList);
    }

    public UserDTO get(){
        String id = (String) cmbUserIds.getValue();
        UserDTO userDTO = userService.get(Long.valueOf(id));
        return userDTO;
    }

    public void cmbUserIdsOnAction(ActionEvent event) {
        UserDTO userDTO = get();
        setValues(userDTO);
    }

    private void setValues(UserDTO userDTO) {
        txtName.setText(userDTO.getName());
        txtEmail.setText(userDTO.getEmail());
        txtPhoneNo.setText(userDTO.getPhone());
        txtUserName.setText(userDTO.getUserName());
    }

    public void changeAccountOnAction(ActionEvent event) {
        boolean isUserValidated = validateUser();
        if (isUserValidated) {
            UserDTO userDTO = userService.get(Long.valueOf((String) cmbUserIds.getValue()));
            userService.update(setNewValues(userDTO));
            clearFields();
        }
    }

    private UserDTO setNewValues(UserDTO userDTO) {
        userDTO.setName(txtName.getText());
        userDTO.setEmail(txtEmail.getText());
        userDTO.setPhone(txtPhoneNo.getText());
        userDTO.setUserName(txtUserName.getText());
        return userDTO;
    }

    public void deleteAccountOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to Delete?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {

            boolean isdeleted =userService.delete(get());
            if (isdeleted) {
                PopUps.popUps(AlertTypes.CONFORMATION, "Sucessful", "Book Deleted Sucessfully !");
                clearFields();
            } else {
                PopUps.popUps(AlertTypes.CONFORMATION, "Unsucessful", "something went wrong !");
            }
        }
    }

    public void btnCancelOnAction(ActionEvent event) {
        UserManageFormController.closeStage();
    }

    private void clearFields(){
        txtName.setText("");
        txtEmail.setText("");
        txtPhoneNo.setText("");
        txtUserName.setText("");
    }
    private boolean validateUser(){
        String nameText = txtName.getText();
        boolean isNameValidated = Pattern.matches("[A-Za-z/.\\s]{3,}", nameText);
        if (!isNameValidated) {
            PopUps.popUps(AlertTypes.ERROR, "Invalid", "Invalid User Name");
            return false;
        }
        String eMail =txtEmail.getText();
        boolean isMailValidated =Pattern.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b",eMail);
        if (!isMailValidated) {
            PopUps.popUps(AlertTypes.ERROR, "Invalid", "Invalid User's E-mail");
            return false;
        }
        String cnText = txtPhoneNo.getText();
        boolean isCNValidated = Pattern.matches("[0][0-9]{9}", cnText);
        if (!isCNValidated) {
            PopUps.popUps(AlertTypes.ERROR, "Invalid", "Invalid Mobile Number");
            return false;
        }
        String unameText = txtUserName.getText();
        boolean isuNameValidated = Pattern.matches("[A-Za-z/.\\s]{3,}", unameText);
        if (!isuNameValidated) {
            PopUps.popUps(AlertTypes.ERROR, "Invalid", "Invalid User Name");
            return false;
        }
        return true;
    }

}