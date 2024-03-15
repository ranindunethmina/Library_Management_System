package lk.ijse.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserTM {
    private long id;
    private String name;
    private String email;
    private String phoneNo;
    private JFXButton btnUpdate;
    private JFXButton btnDelete;

    {
        btnUpdate = new JFXButton("Update");
        btnDelete = new JFXButton("Delete");

        // Set button styles
        btnDelete.setCursor(javafx.scene.Cursor.HAND);
        btnDelete.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff");

        btnUpdate.setCursor(javafx.scene.Cursor.HAND);
        btnUpdate.setStyle("-fx-background-color: #00ff00; -fx-text-fill: #ffffff");

        btnUpdate.setPrefWidth(100);
        btnDelete.setPrefWidth(100);
        btnDelete.setPrefHeight(30);
        btnUpdate.setPrefHeight(30);
    }

    public UserTM(Long id, String name, String email, String phoneNo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
    }

}