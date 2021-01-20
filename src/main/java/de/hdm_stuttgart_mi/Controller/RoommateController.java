package de.hdm_stuttgart_mi.Controller;

import de.hdm_stuttgart_mi.Users.Roommate;
import de.hdm_stuttgart_mi.Users.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.io.FileNotFoundException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static de.hdm_stuttgart_mi.Controller.ExternMethods.getImageView;
import static de.hdm_stuttgart_mi.Users.User.currentUser;

public class RoommateController implements Initializable {
    @FXML private ListView roommateListView;
    User user = new User();


    private void initRoommateListView() throws FileNotFoundException {

        ObservableList<HBox> obsList = FXCollections.observableArrayList();
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd.MM.yyyy");

        for (Roommate roommate : user.getRoommateList()) {

            if (roommate.getID() != currentUser.getID()) {
                //new HBox for each roommate
                HBox hbox = new HBox();
                hbox.getStyleClass().add("roommate");
                //roommates current Profilepicture
                ImageView profilPicView = getImageView(roommate.getProfilepic(),70,70);
                //name of roommate
                Label name = new Label(roommate.getFullname());
                name.setMinWidth(200);
                //roommate birthday
                Label birthday = new Label(roommate.getBirthday().format(formatter));
                birthday.setMinWidth(200);
                //Move in day
                Label moveInDate = new Label(roommate.getMoveInDate().format(formatter));
                moveInDate.setMinWidth(200);
                //phonenumber
                Label phone = new Label(roommate.getPhonenumber());
                phone.setMinWidth(200);

                HBox.setHgrow(name, Priority.ALWAYS);
                hbox.getChildren().addAll(profilPicView,name,moveInDate,phone,birthday);

                obsList.add(hbox);
            }

        }
        roommateListView.setItems(obsList);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initRoommateListView();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    }

