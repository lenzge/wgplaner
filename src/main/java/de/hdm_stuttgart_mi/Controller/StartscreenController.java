package de.hdm_stuttgart_mi.Controller;

import de.hdm_stuttgart_mi.notificationAndUsers.Navigation;
import de.hdm_stuttgart_mi.notificationAndUsers.Roommate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

import static de.hdm_stuttgart_mi.notificationAndUsers.Navigation.currentUser;


public class StartscreenController implements Initializable {

    private static final Logger log = LogManager.getLogger(StartscreenController.class);

    @FXML private MenuButton roommateMenuButton;
    @FXML private Button newRoommate_bt;
    Navigation nav = new Navigation();
    private void initMenuButton(){
        roommateMenuButton.setText("Mitbewohner");
        for(int i = 0;i<nav.roommateListLenght();i++ ){
            Roommate roommate = nav.getRoommate(i);
            int id = roommate.getID();
            MenuItem menuitem = new MenuItem(roommate.getFullname());
            menuitem.setId(""+id);
            menuitem.setOnAction(event -> initUser(menuitem));
            roommateMenuButton.getItems().add(menuitem);
        }
    }
    private void initUser(MenuItem menuItem){
        roommateMenuButton.setText(menuItem.getText());
        String Id =menuItem.getId();
        int id = Integer.parseInt(Id);
        nav = new Navigation(id);
        log.info("Der aktuelle User "+currentUser.getFullname());

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initMenuButton();
    }
}
