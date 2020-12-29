package de.hdm_stuttgart_mi.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static de.hdm_stuttgart_mi.notificationAndUsers.Navigation.currentUser;

public class profilController extends Supercontroller implements Initializable {
    @FXML private ImageView profilePic;
    @FXML private Button profil_bt;

    @FXML private Label birthday_lb;
    @FXML private ImageView ownProfilePic;
    @FXML private HBox profilpics;
    private String catsmile = " =^.^=";

    FileInputStream currentPp = new FileInputStream(currentUser.getProfilepic());
    private Image currentProfilePic= new Image(currentPp);
    private DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public profilController() throws FileNotFoundException {
    }
    private void setOwnPp(){
        ownProfilePic.setImage(currentProfilePic);
        ownProfilePic.setFitHeight(70);
        ownProfilePic.setFitWidth(70);
    }
    private void setProfilePics() throws FileNotFoundException {

        ToggleGroup toggleGroup = new ToggleGroup();

        File directory = new File("src/main/resources/images");
        //all Filenames in the directory are written into a Stringarray
        String[] list = directory.list();
        //sicherstellen dass die Liste icht null ist
        assert list != null;
        for(String profilepicPath : list) {
            String filename = "src/main/resources/images/" + profilepicPath;
            FileInputStream input = new FileInputStream(filename);

            if(!(filename.equals(currentUser.getProfilepic()))) {

                ToggleButton button = new ToggleButton();
                Image profilePic = new Image(input);
                ImageView showProfilePic = new ImageView(profilePic);
                showProfilePic.setFitWidth(100);
                showProfilePic.setFitHeight(100);
                button.setGraphic(showProfilePic);
                button.setId("src/main/resources/images/" + profilepicPath);

                button.setOnAction(event -> {
                    try {
                        profilButton(button);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                });

                toggleGroup.getToggles().add(button);
                profilpics.getChildren().add(button);
            }
        }
    }
    private void profilButton(ToggleButton button) throws FileNotFoundException {

        currentUser.setProfilepic(button.getId());
        setOwnPp();
        setProfilePics();
    }


    @Override public void initialize(URL location, ResourceBundle resources){
        profilePic.setImage(currentProfilePic);
        profilePic.setFitWidth(55);
        profilePic.setFitHeight(55);
        profil_bt.setText(currentUser.getFullname());

        birthday_lb.setText(currentUser.getBirthday().format(formatter));
        setOwnPp();
        try {
            setProfilePics();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
