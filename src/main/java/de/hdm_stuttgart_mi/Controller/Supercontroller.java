package de.hdm_stuttgart_mi.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static de.hdm_stuttgart_mi.notificationAndUsers.Navigation.currentUser;

public class Supercontroller {
    public void changeScene(ActionEvent event) throws IOException {

        final FXMLLoader loader = new FXMLLoader();
        //choose fxml Scene
        Button button = ((Button)event.getSource());
        Parent sceneRoot;
        if(button.getId().equals("apply_bt")){
            sceneRoot = loader.load(getClass().getResourceAsStream("/fxml/welcome.fxml"));
        }
        else{
            sceneRoot = loader.load(getClass().getResourceAsStream("/fxml/newRoommate.fxml"));
        }
        //loader.setController(this);
        Scene scene =  new Scene(sceneRoot);
        //add Stylesheet
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
}
