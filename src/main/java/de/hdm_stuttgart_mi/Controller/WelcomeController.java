package de.hdm_stuttgart_mi.Controller;

import de.hdm_stuttgart_mi.notificationAndUsers.Roommate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import static de.hdm_stuttgart_mi.notificationAndUsers.Navigation.currentUser;

public class WelcomeController implements Initializable {
    @FXML private Label welcomeText;
    private String catsmile = " =^.^=";

    public String getTime(){
        LocalDateTime date =  LocalDateTime.now();
        int hour = date.getHour();
        if(hour > 18){return "Guten Abend ";}
        if(hour > 11){return "Hallo ";}
        if(hour > 5){return "Guten Morgen ";}
        return "";
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        welcomeText.setText(getTime()+currentUser.getFirstname()+catsmile);
    }
}
