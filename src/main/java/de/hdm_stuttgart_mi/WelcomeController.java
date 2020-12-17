package de.hdm_stuttgart_mi;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import static de.hdm_stuttgart_mi.notificationAndUsers.Navigation.currentUser;

public class WelcomeController implements Initializable {
    @FXML private Button blackboard_bt;
    @FXML private Button grocerylist_bt;
    @FXML private Button roommates_bt;
    @FXML private Label welcomeText;
    @FXML private ImageView profilePic;
    @FXML private Label roommateName;
    private String catsmile = " =^.^=";

    FileInputStream input = new FileInputStream(currentUser.getProfilepic());
    private Image currentProfilePic= new Image(input);


    public WelcomeController() throws FileNotFoundException {
    }

    @FXML public void blackboardButton(){
        blackboard_bt.setText("gedrückt");
    }
    @FXML public void groceryListButton(){
        grocerylist_bt.setText("gedrückt");
    }
    @FXML public void roommatesButton(){ roommates_bt.setText("gedrückt"); }

    public String getTime(){
           Date date = new Date();
           int hours = date.getHours();
           if(hours > 18){return "Guten Abend ";}
           if(hours > 11){return "Hallo ";}
           if(hours > 5){return "Guten Morgen ";}
           return "";
    }


    @Override public void initialize(URL location, ResourceBundle resources){
        welcomeText.setText(getTime()+currentUser.getFirstname()+catsmile);
        profilePic.setImage(currentProfilePic);
        profilePic.setFitWidth(55);
        profilePic.setFitHeight(55);
        roommateName.setText(currentUser.getFullname());
    }

}
