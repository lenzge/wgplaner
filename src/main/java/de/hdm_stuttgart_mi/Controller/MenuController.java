package de.hdm_stuttgart_mi.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static de.hdm_stuttgart_mi.notificationAndUsers.Navigation.currentUser;

public class MenuController extends Supercontroller implements Initializable {

    @FXML private Label welcomeText;
    @FXML private ImageView profilePic;
    @FXML private ImageView shutdownIcon;
    @FXML private Button profil;
    @FXML private GridPane changing;
    @FXML private GridPane root;

    private String catsmile = "=^.^=";


    FileInputStream shutdownIconPath = new FileInputStream("src/main/resources/icons/shutdown.png");
    private Image shutdown= new Image(shutdownIconPath);


    public MenuController() throws FileNotFoundException {
    }
    public void initPp() throws FileNotFoundException {
        FileInputStream pp = new FileInputStream(currentUser.getProfilepic());
        Image currentProfilePic= new Image(pp);
        System.out.println(currentProfilePic.getUrl());
        profilePic.setImage(currentProfilePic);
        profilePic.setFitWidth(55);
        profilePic.setFitHeight(55);
    }

    public String getTime(){
           LocalDateTime date =  LocalDateTime.now();
           int hour = date.getHour();
           if(hour > 18){return "Guten Abend ";}
           if(hour > 11){return "Hallo ";}
           if(hour > 5){return "Guten Morgen ";}
           return "";
    }
    public void changeScene(ActionEvent event) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        //choose fxml Scene. To add a scenechange simply add another else if with the button ID and scenefilename
        Button button = ((Button)event.getSource());
        Parent sceneRoot;
        if(button.getId().equals("deleteUser_bt")||button.getId().equals("logout_bt")) {
            sceneRoot = loader.load(getClass().getResourceAsStream("/fxml/startscreen.fxml"));
            root.getChildren().clear();
            root.getChildren().add(sceneRoot);
        }

        else {
            sceneRoot = loader.load(getClass().getResourceAsStream("/fxml/"+button.getId()+".fxml"));
            changing.getChildren().clear();
            changing.getChildren().add(sceneRoot);
        }
       /* changing.getChildren().clear();
        changing.getChildren().add(sceneRoot);*/
    }

    @Override public void initialize(URL location, ResourceBundle resources){
        welcomeText.setText(getTime()+currentUser.getFirstname()+catsmile);
        try {
            initPp();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        profil.setText(currentUser.getFullname());
        shutdownIcon.setImage(shutdown);
        shutdownIcon.setFitWidth(25);
        shutdownIcon.setFitHeight(25);
    }

}