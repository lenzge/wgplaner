package de.hdm_stuttgart_mi;

import de.hdm_stuttgart_mi.Users.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;

public class Main extends Application {

    private Thread backupThread = new Thread(new BackupThread());
    //logger
	private static final Logger log = LogManager.getLogger(Main.class);

	public void start(Stage stage) throws Exception {
	    backupThread.setDaemon(true);
	    backupThread.start();

        log.info("Starting GUI from main");

        final String fxmlFile = "/fxml/base.fxml";
        log.debug("Loading FXML for main view from: {}", fxmlFile);

        //parse stylesheet to external form
        final String stylesheet = getClass().getResource("/styles/styles.css").toExternalForm();
        log.debug("parse stylesheet");

        final FXMLLoader loader = new FXMLLoader();
        final Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        loader.setController(this);

        log.debug("Showing JFX scene");
        final Scene scene = new Scene(rootNode);
        /**
         add stylesheet, set title and set the ApplicationIcon
          **/
        scene.getStylesheets().add(stylesheet);
        stage.setTitle("WGPlaner");
        Image image = new Image(new FileInputStream("src/main/resources/icons/startIcon.png")); //"https://icons8.de/icons/set/home-page  https://icons8.de
        stage.getIcons().add(image);
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
	    User user = new User();
        user.updateCurrentUser();
        super.stop();
        log.info("Terminating application");
    }

}

