package de.hdm_stuttgart_mi.Controller;

import de.hdm_stuttgart_mi.notificationAndUsers.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static de.hdm_stuttgart_mi.notificationAndUsers.Navigation.currentUser;

public class profilController extends Supercontroller implements Initializable {
    @FXML private ImageView profilePic;
    @FXML private ImageView ownProfilePic;
    @FXML private Button profil_bt;
    @FXML private Button birthday_bt;
    @FXML private Label birthday_lb;
    @FXML private Label moveInDate;
    @FXML private Label phonenumber;
    @FXML private Label fullname_lb;
    @FXML private HBox profilpics;
    @FXML private DatePicker birthdaypicker;

    final private FileInputStream currentPp = new FileInputStream(currentUser.getProfilepic());
    final private Image currentProfilePic= new Image(currentPp);
    final private DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd.MM.yyyy");
    Navigation nav = new Navigation();

    public profilController() throws FileNotFoundException { }


    private void initPhonenumber(){
        phonenumber.setText("Deine eingetragene Handynummer ist: "+currentUser.getPhonenumber());
    }

    private void initMoveInDate(){
        moveInDate.setText("Du bist am "+currentUser.getMoveInDate().format(formatter)+" eingezogen");
    }

//OnAction method for "Bearbeiten"-Button
    @FXML
    private void changeBirthday(){
        //If the Button was already pressed before it changes back to "Bearbeiten" and sets the new Birthdate
        if(birthdaypicker.isVisible()){

            birthdaypicker.setVisible(false);
            birthdaypicker.setDisable(true);
            birthday_bt.setText("Bearbeiten");

            if(birthdaypicker.getValue()!=null){
                currentUser.setBirthday(birthdaypicker.getValue());
                birthday_lb.setText(currentUser.getBirthday().format(formatter));
                nav.updateCurrentUser();
            }

        }
        //if the Button wasn't pressed before it sets the Datepicker visable and enables it
       else {
            birthday_bt.setText("Übernehmen");
            birthdaypicker.setVisible(true);
            birthdaypicker.setDisable(false);
        }

    }


    private void setOwnPp() throws FileNotFoundException {
        FileInputStream currentPp = new FileInputStream(currentUser.getProfilepic());
        Image currentProfilePic= new Image(currentPp);
        ownProfilePic.setImage(currentProfilePic);
        ownProfilePic.setFitHeight(80);
        ownProfilePic.setFitWidth(80);
    }

    private void setProfilePics() throws FileNotFoundException {

        //clear HBox before initializing it again
        profilpics.getChildren().clear();

        ToggleGroup toggleGroup = new ToggleGroup();

        File directory = new File("src/main/resources/images");

        //all Filenames in the directory are written into a Stringarray
        String[] list = directory.list();

        //make sure array is not null
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
                        changeProfilepic(button);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                });

                toggleGroup.getToggles().add(button);
                profilpics.getChildren().add(button);
            }
        }
    }

    private void changeProfilepic(ToggleButton button) throws FileNotFoundException {

        currentUser.setProfilepic(button.getId());
        nav.updateCurrentUser();
        setOwnPp();

        FileInputStream currentPp = new FileInputStream(currentUser.getProfilepic());
        Image currentProfilePic= new Image(currentPp);

        profilePic.setImage(currentProfilePic);
        profilePic.setFitWidth(55);
        profilePic.setFitHeight(55);

        setProfilePics();
    }

    @FXML
    public void deleteUser(ActionEvent e) throws IOException {
        Navigation nav = new Navigation();
        nav.deleteFromList();
        super.changeScene(e);
    }



    @Override public void initialize(URL location, ResourceBundle resources){
        fullname_lb.setText(currentUser.getFullname());
        profilePic.setImage(currentProfilePic);
        profilePic.setFitWidth(55);
        profilePic.setFitHeight(55);
        profil_bt.setText(currentUser.getFullname());
        initMoveInDate();
        initPhonenumber();

        birthday_lb.setText(currentUser.getBirthday().format(formatter));
        try {
            setOwnPp();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            setProfilePics();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
