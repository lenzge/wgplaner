package de.hdm_stuttgart_mi.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class NewRoommateController implements Initializable {
    private static final Logger log = LogManager.getLogger(NewRoommateController.class);
    private DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private LocalDate birthday;
    private String profilePic;
    @FXML private DatePicker birthday_dp;
    @FXML private HBox profilepicList;
    @FXML private void datepicked(){
        String value = birthday_dp.getValue().format(formatter);
        birthday = LocalDate.parse(value,formatter);
        log.info(birthday);
    }
    private void initProfilpics() throws FileNotFoundException {
        File directory = new File("src/main/resources/images");
        String[] list = directory.list();
        assert list != null;
        for(String profilepicPath : list){
            Button button = new Button();
            FileInputStream input = new FileInputStream("src/main/resources/images/"+profilepicPath);
            Image profilePic= new Image(input);
            ImageView showProfilePic = new ImageView(profilePic);
            showProfilePic.setFitWidth(100);
            showProfilePic.setFitHeight(100);
            button.setGraphic(showProfilePic);
            button.setOnAction(event -> profilButton(button));
            profilepicList.getChildren().add(button);
        }
    }
   private void profilButton(Button button){
        ImageView imageView = (ImageView) button.getGraphic();
        String profilepicPath = imageView.getImage().getUrl();
        profilePic = profilepicPath;
        log.debug(profilePic);
   }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            initProfilpics();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("File not found");
        }
    }
}
