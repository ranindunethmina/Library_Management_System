package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import lk.ijse.dto.UserDTO;
import lk.ijse.notification.PopUps;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.UserService;
import lk.ijse.util.AlertTypes;

import java.util.regex.Pattern;


public class SignInFormController {
    @FXML
    private TextField txtConformPassword;

    @FXML
    private TextField txtPhoneNo;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    UserService userService = (UserService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.USER);

    public void createAccountOnAction(ActionEvent event) {
        boolean isUserValidated = validateUser();
        if (isUserValidated) {
            if (txtPassword.getText().equals(txtConformPassword.getText())) {
                Long id = userService.save(new UserDTO(
                        txtName.getText(),
                        txtEmail.getText(),
                        txtPhoneNo.getText(),
                        txtUserName.getText(),
                        txtPassword.getText()
                ));
                if (id != -1L) {
                    PopUps.popUps(AlertTypes.CONFORMATION, "Sucessful", "User Saved successful !");
                    clearFields();
                } else {
                    PopUps.popUps(AlertTypes.CONFORMATION, "Unsucessful", "something went wrong !");
                }
            } else {
                PopUps.popUps(AlertTypes.WARNING, "Password No match", "Please Try Again !");
            }
        }
    }

    public void conformPassword(ContextMenuEvent contextMenuEvent) {
    }

    private void clearFields(){
        txtName.setText("");
        txtEmail.setText("");
        txtPhoneNo.setText("");
        txtUserName.setText("");
        txtPassword.setText("");
        txtConformPassword.setText("");
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
        String pw = txtPassword.getText();
        boolean isBuyerNameValidated = Pattern.matches("[A-Za-z0-9/.\\s]{3,}", pw);
        if (!isBuyerNameValidated) {
            PopUps.popUps(AlertTypes.ERROR, "Invalid", "Invalid PassWord");
            return false;
        }
        return true;
    }
}