package lk.ijse.notification;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import lk.ijse.util.AlertTypes;
import org.controlsfx.control.Notifications;

public class PopUps {
    public static void popUps(AlertTypes alertTypes, String title, String test){
        Image img ;
        switch (alertTypes){
            case ERROR ->img=new Image("assets/alert/cross.png");
            case WARNING -> img=new Image("assets/alert/warning.png");
            case INFORMATION -> img=new Image("assets/alert/information.png");
            case CONFORMATION -> img=new Image("assets/alert/verified.png");
            default -> throw new IllegalStateException("Unexpected value: " + alertTypes);
        }
        Notifications notificationBuilder= Notifications.create()
                .title(title)
                .text(test)
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.CENTER_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {}
                });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }
}
