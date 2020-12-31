package de.hdm_stuttgart_mi.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Supercontroller {
    public void changeScene(ActionEvent event) throws IOException {

        final FXMLLoader loader = new FXMLLoader();
        //choose fxml Scene. To add a scenechange simply add another else if with the button ID and scenefilename
        Button button = ((Button)event.getSource());
        Parent sceneRoot;
        if(button.getId().equals("apply_bt")){
            sceneRoot = loader.load(getClass().getResource("/fxml/welcome.fxml"));
        }
        else if(button.getId().equals("newRoommate_bt")){
            sceneRoot = loader.load(getClass().getResource("/fxml/newRoommate.fxml"));

        }

        else {
            Label label = new Label("Tut uns Leid es ist etwas schief gelaufen. Bitte starte das Programm neu");
            sceneRoot=label;
        }
        //loader.setController(this);
        Scene scene =  new Scene(sceneRoot);
        //add Stylesheet
        scene.getStylesheets().add(getClass().getResource("/styles/stylesLena.css").toExternalForm());
        Stage window = (Stage)(button).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    public void terminate(ActionEvent event){
        System.exit(0);
    }
}
