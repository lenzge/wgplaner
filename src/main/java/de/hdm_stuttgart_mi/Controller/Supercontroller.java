package de.hdm_stuttgart_mi.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import static de.hdm_stuttgart_mi.notificationAndUsers.Navigation.currentUser;

public class Supercontroller {
    public void changeScene(ActionEvent event) throws IOException {

        final FXMLLoader loader = new FXMLLoader();
        //choose fxml Scene. To add a scenechange simply add another else if with the button ID and scenefilename
        Button button = ((Button)event.getSource());
        Parent sceneRoot;
        if(button.getId().equals("apply_bt")){
            sceneRoot = loader.load(getClass().getResourceAsStream("/fxml/welcome.fxml"));
        }
        else if(button.getId().equals("blackboard_bt")){
            sceneRoot = loader.load(getClass().getResourceAsStream("/fxml/hello.fxml"));
        }
        else if(button.getId().equals("grocerylist_bt")){
            sceneRoot = loader.load(getClass().getResourceAsStream("/fxml/grocerylist.fxml"));
        }
        else if(button.getId().equals("roommates_bt")){
            sceneRoot = loader.load(getClass().getResourceAsStream("/fxml/hello.fxml"));
        }
        else if(button.getId().equals("newRoommate_bt")){
            sceneRoot = loader.load(getClass().getResourceAsStream("/fxml/newRoommate.fxml"));
        }
        else if(button.getId().equals("profil_bt")){
            sceneRoot = loader.load(getClass().getResourceAsStream("/fxml/profil.fxml"));
        }
        else if(button.getId().equals("deleteUser_bt")){
            sceneRoot = loader.load(getClass().getResourceAsStream("/fxml/startscreen.fxml"));
        }
        else {
            Label label = new Label("Tut uns Leid es ist etwas schief gelaufen. Bitte starte das Programm neu");
            sceneRoot=label;
        }
        //loader.setController(this);
        Scene scene =  new Scene(sceneRoot);
        //add Stylesheet
        scene.getStylesheets().add(getClass().getResource("/styles/stylesLena.css").toExternalForm());
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
}
