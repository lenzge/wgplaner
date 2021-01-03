package de.hdm_stuttgart_mi;

import de.hdm_stuttgart_mi.notificationAndUsers.Navigation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main extends Application {

    //logger
	private static final Logger log = LogManager.getLogger(Main.class);

	public void start(Stage stage) throws Exception {
        log.info("Starting GUI from main");

        final String fxmlFile = "/fxml/main.fxml";
        log.debug("Loading FXML for main view from: {}", fxmlFile);

        //parse stylesheet to external form
        final String stylesheet = getClass().getResource("/styles/styles.css").toExternalForm();
        log.debug("parse stylesheet");

        final FXMLLoader loader = new FXMLLoader();
        final Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        loader.setController(this);

        log.debug("Showing JFX scene");
        final Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(stylesheet);
        stage.setTitle("WGPlaner");
        stage.setScene(scene);

        stage.show();
    }

    //start
    public static void main(String[] args) {
        launch(args);
    }

    //stop
    @Override
    public void stop() throws Exception {
        Navigation nav = new Navigation();
        nav.updateCurrentUser();
        super.stop();
        log.info("Terminating application");
    }
}
