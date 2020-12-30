package de.hdm_stuttgart_mi.Controller;

import de.hdm_stuttgart_mi.notificationAndUsers.Navigation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static de.hdm_stuttgart_mi.notificationAndUsers.Navigation.currentUser;

public class WelcomeController extends Supercontroller implements Initializable {

    @FXML private Label welcomeText;
    @FXML private ImageView profilePic;
    @FXML private Button profil_bt;
    private String catsmile = " =^.^=";

    FileInputStream input = new FileInputStream(currentUser.getProfilepic());
    private Image currentProfilePic= new Image(input);


    public WelcomeController() throws FileNotFoundException {
    }

    public String getTime(){
           LocalDateTime date =  LocalDateTime.now();
           int hour = date.getHour();
           if(hour > 18){return "Guten Abend ";}
           if(hour > 11){return "Hallo ";}
           if(hour > 5){return "Guten Morgen ";}
           return "";
    }


    @Override public void initialize(URL location, ResourceBundle resources){
        welcomeText.setText(getTime()+currentUser.getFirstname()+catsmile);
        profilePic.setImage(currentProfilePic);
        profilePic.setFitWidth(55);
        profilePic.setFitHeight(55);
        profil_bt.setText(currentUser.getFullname());
    }

}
