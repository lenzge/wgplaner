package de.hdm_stuttgart_mi.Controller;

import de.hdm_stuttgart_mi.GroceryList.Iitem;
import de.hdm_stuttgart_mi.notificationAndUsers.Navigation;
import de.hdm_stuttgart_mi.notificationAndUsers.Roommate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static de.hdm_stuttgart_mi.notificationAndUsers.Navigation.currentUser;

public class RoommateController implements Initializable {
    @FXML private ListView roommateListView;
    Navigation nav = new Navigation();


    private void initRoommateListView() throws FileNotFoundException {

        ObservableList<HBox> obsList = FXCollections.observableArrayList();
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd.MM.yyyy");

        for (Roommate roommate : nav.getRoommateList()) {
            if (roommate.getID() != currentUser.getID()) {
                HBox hbox = new HBox();
                hbox.getStyleClass().add("hboxLea");
                Image profilPic = new Image(new FileInputStream(roommate.getProfilepic()));
                ImageView profilPicView = new ImageView(profilPic);
                profilPicView.setFitWidth(70);
                profilPicView.setFitHeight(70);
                Label name = new Label(roommate.getFullname());
                name.setMinWidth(200);
                Label birthday = new Label(roommate.getBirthday().format(formatter));
                birthday.setMinWidth(200);
                Label moveInDate = new Label(roommate.getMoveInDate().format(formatter));
                moveInDate.setMinWidth(200);
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

