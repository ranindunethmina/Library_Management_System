package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.dto.UserDTO;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.UserService;
import lk.ijse.tm.UserTM;

import java.io.IOException;
import java.util.List;

public class UserManageFormController {
    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colMobileNo;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private TableColumn<?, ?> colUserNo;

    @FXML
    private TableView<UserTM> tblUser;
    static Stage stage = new Stage();


    UserService userService = (UserService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.USER);

    public void initialize(){
        setCellValueFactory();
        getAllUsers();
    }

    private void getAllUsers() {
        List<UserDTO> allCustomers = userService.getAll();
        ObservableList<UserTM> oblist = FXCollections.observableArrayList();
        for (UserDTO userDTO : allCustomers){
            oblist.add(
                    new UserTM(
                            userDTO.getId(),
                            userDTO.getName(),
                            userDTO.getEmail(),
                            userDTO.getPhone()
                    )
            );
        }
        for (int i = 0; i < oblist.size(); i++) {
            oblist.get(i).getBtnUpdate().setOnAction(event -> {
                System.out.println("Complete button clicked");
                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserUpdateForm.fxml"))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.centerOnScreen();
                stage.show();
            });

            oblist.get(i).getBtnDelete().setOnAction(event -> {
                System.out.println("Delete button clicked");
                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserUpdateForm.fxml"))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.centerOnScreen();
                stage.show();
            });
        }
        tblUser.setItems(oblist);
    }

    private void setCellValueFactory() {
        colUserNo.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colMobileNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("btnUpdate"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
    }
    public static void closeStage(){
        stage.close();
    }
}