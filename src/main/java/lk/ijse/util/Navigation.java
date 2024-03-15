package lk.ijse.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static Stage stage;
    public void setUi(String location, ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/"+location+".fxml"));
        Parent customerFormParent = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(customerFormParent));
        stage.setTitle("Online Library Management System");
        stage.centerOnScreen();
        stage.show();
    }
    
    public static void close(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

}