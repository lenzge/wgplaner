package de.hdm_stuttgart_mi.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML private BorderPane root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Node startscreen = null;
        try {
            startscreen = FXMLLoader.load(getClass().getResource("/fxml/startscreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.setCenter(startscreen);
    }
  
}
