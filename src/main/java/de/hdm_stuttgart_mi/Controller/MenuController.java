package de.hdm_stuttgart_mi.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import static de.hdm_stuttgart_mi.notificationAndUsers.User.currentUser;

public class MenuController extends SuperController implements Initializable {

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



    @Override public void initialize(URL location, ResourceBundle resources){
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
