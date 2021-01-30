package de.hdm_stuttgart_mi.Controller;

import de.hdm_stuttgart_mi.Main;
import de.hdm_stuttgart_mi.Users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class SuperController {
    private static final Logger log = LogManager.getLogger(SuperController.class);

    //add menu, set center to welcome
    public void changeFirstScene(ActionEvent event) throws IOException {

        Button button = ((Button) event.getSource());
        BorderPane root = (BorderPane) button.getScene().getRoot();
        //menu left
        Node leftRoot = FXMLLoader.load(getClass().getResource("/fxml/"+button.getId()+".fxml"));
        BorderPane.setMargin(leftRoot,new Insets(0,10,0,0));
        root.setLeft(leftRoot);
        //welcome center
        Node centerRoot = FXMLLoader.load(getClass().getResource("/fxml/welcome.fxml"));
        centerRoot.getStyleClass().add("center");
        root.setCenter(centerRoot);
        log.debug("changed first scene");

    }
    //menu stays, only center changes
    public void changeCenterScene(ActionEvent event) throws IOException {

        Button button = ((Button) event.getSource());
        BorderPane root = (BorderPane) button.getScene().getRoot();
        Node centerRoot = FXMLLoader.load(getClass().getResource("/fxml/"+button.getId()+".fxml"));
        centerRoot.getStyleClass().add("center");
        root.setCenter(centerRoot);
        log.debug("changed center");

    }
    //menu disappears, center changes
    public void changeScene(ActionEvent event) throws IOException {

        Button button = ((Button) event.getSource());
        BorderPane root = (BorderPane) button.getScene().getRoot();
        Node sceneRoot = FXMLLoader.load(getClass().getResource("/fxml/"+button.getId()+".fxml"));
        sceneRoot.getStyleClass().add("center");
        root.setCenter(sceneRoot);
        root.setLeft(null);
        log.debug("changed scene");

    }
    public void terminate(ActionEvent event){
        User user = new User();
        user.updateCurrentUser();
        Main.running=false;
        System.exit(0);
    }
}
