package de.hdm_stuttgart_mi;

import de.hdm_stuttgart_mi.Users.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

public class Main extends Application {
    private File file = new File("src/main/resources/JSON/roommates.json");
    private File backupFile = new File("src/main/resources/JSON/backup.json");
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    //logger
	private static final Logger log = LogManager.getLogger(Main.class);

	public void start(Stage stage) throws Exception {
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
        User user = new User();
        user.updateCurrentUser();
        super.stop();
        log.info("Terminating application");
    }

    private void backup(){
        JSONParser parser = new JSONParser();
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())), "UTF-8");
            JSONObject json = (JSONObject) parser.parse(content);

            JSONArray jsonArray = (JSONArray) json.get("roommates");

            try (FileWriter writer = new FileWriter(backupFile)) {
                writer.write(jsonArray.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
                log.error("couldn't write into JSON Backup File");
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("rommate list couldn't be initialized");
        }
    }
}

