package de.hdm_stuttgart_mi.Controller;

import de.hdm_stuttgart_mi.FxmlGuiDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Supercontroller {
    private static final Logger log = LogManager.getLogger(Supercontroller.class);

    public void changeFirstScene(ActionEvent event) throws IOException {

        Button button = ((Button) event.getSource());
        BorderPane root = (BorderPane) ((Button) event.getSource()).getScene().getRoot();
        Node sceneRoot = FXMLLoader.load(getClass().getResource("/fxml/"+button.getId()+".fxml"));
        BorderPane.setMargin(sceneRoot,new Insets(0,10,0,0));
        root.setLeft(sceneRoot);
        Node sceneCenter = FXMLLoader.load(getClass().getResource("/fxml/welcome.fxml"));
        sceneCenter.getStyleClass().add("center");
        root.setCenter(sceneCenter);

    }
    public void changeCenterScene(ActionEvent event) throws IOException {

        Button button = ((Button) event.getSource());
        BorderPane root = (BorderPane) ((Button) event.getSource()).getScene().getRoot();
        Node sceneRoot = FXMLLoader.load(getClass().getResource("/fxml/"+button.getId()+".fxml"));
        root.setCenter(sceneRoot);
        sceneRoot.getStyleClass().add("center");

    }
    public void changeScene(ActionEvent event) throws IOException {

        Button button = ((Button) event.getSource());
        BorderPane root = (BorderPane) ((Button) event.getSource()).getScene().getRoot();
        log.info(root);
        Node sceneRoot = FXMLLoader.load(getClass().getResource("/fxml/"+button.getId()+".fxml"));
        sceneRoot.getStyleClass().add("center");
        root.setCenter(sceneRoot);
        root.setLeft(null);


    }
    public void terminate(ActionEvent event){
        System.exit(0);
    }
}
