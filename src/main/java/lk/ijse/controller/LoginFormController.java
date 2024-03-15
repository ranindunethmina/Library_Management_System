package lk.ijse.controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.UserDTO;
import lk.ijse.notification.PopUps;
import lk.ijse.service.LoginService;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.UserService;
import lk.ijse.util.AlertTypes;
import lk.ijse.util.DateAndTime;
import lk.ijse.util.Navigation;

import java.io.IOException;

public class LoginFormController {
    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink btnSign;

    @FXML
    private ToggleButton btnToogle;

    @FXML
    private ImageView imgEye;

    @FXML
    private Label lblDateAndTime;

    @FXML
    private Label lblShownPw;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtpassword;

    public static String username;

    LoginService loginService = (LoginService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.LOGIN);
    UserService userService = (UserService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.USER);


    public void initialize(){
        DateAndTime.manageDateAndTime(lblDateAndTime);
        lblShownPw.setVisible(false);
    }

    public void btnLoginOnAction(ActionEvent event) throws IOException {
        username = txtUserName.getText();
        if (txtUserName.equals(null) || txtUserName.getText().equals("admin")){
            new Navigation().setUi("AdminDashBoardForm",event);

        }else if (userService.isExists(txtUserName.getText())){
            if (getUser(txtUserName.getText(), txtpassword.getText())) {
                new Navigation().setUi("UserDashBoardForm",event);
            }else {
                PopUps.popUps(AlertTypes.INFORMATION, "No Match", "Password Incorrect!");
            }
        }else {
            PopUps.popUps(AlertTypes.WARNING, "No Match", "Please Enter Valid Username!");
        }
    }

    public boolean getUser(String username, String password){
        UserDTO userDTO = userService.getCustomerUsingUsername(username);
        return userDTO.getPassword().equals(password);
    }

    public void btnToogleOnAction(ActionEvent event) {
        if (btnToogle.isSelected()){
            lblShownPw.setVisible(true);
            lblShownPw.textProperty().bind(Bindings.concat(txtpassword.getText()));
        }else{
            lblShownPw.setVisible(false);
        }
    }

    public void btnSignOnAction(ActionEvent event) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/SignInForm.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = new Stage();
        stage.setTitle("User Registration");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void txtUserNameOnAction(ActionEvent event) {
        txtpassword.requestFocus();
    }

    public void txtPasswordOnAction(ActionEvent event) {
        btnLogin.requestFocus();
    }

    public void PwFieldKeyTyped(KeyEvent keyEvent) {
        lblShownPw.textProperty().bind(Bindings.concat(txtpassword.getText()));
    }
}