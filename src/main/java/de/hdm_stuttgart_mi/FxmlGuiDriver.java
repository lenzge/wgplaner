package de.hdm_stuttgart_mi;

import de.hdm_stuttgart_mi.notificationAndUsers.Navigation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static de.hdm_stuttgart_mi.notificationAndUsers.Navigation.currentUser;

/**
 * Driver class for a simple JavaFX demonstration.
 *
 */
public class FxmlGuiDriver extends Application {


	private static final Logger log = LogManager.getLogger(FxmlGuiDriver.class);

    /**
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        log.info("Starting GUI");

        String fxmlFile = "/fxml/startscreen.fxml";    //Orginal:  \"/fxml/hello.fxml\";
             log.debug("Loading FXML for main view from: {}", fxmlFile);

                  /*stylesheet als ExternalForm Kennzeichnen*/
        final String stylesheet = getClass().getResource("/styles/stylesLena.css").toExternalForm();


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
    @Override
    public void stop() throws Exception {
        Navigation nav = new Navigation();
        nav.updateCurrentUser();
        super.stop();
        log.info("Terminating application");
    }
}
