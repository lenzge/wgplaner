package de.hdm_stuttgart_mi.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BaseController implements Initializable {

    @FXML private BorderPane root;
    private static final Logger log = LogManager.getRootLogger();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Node startscreen = null;
        try {
            startscreen = FXMLLoader.load(getClass().getResource("/fxml/startscreen.fxml"));
            log.info("base initialized");
        } catch (IOException e) {
            log.error("startscreen couldnt be initialized in Base");
            e.printStackTrace();
        }
        root.setCenter(startscreen);
    }
  
}
