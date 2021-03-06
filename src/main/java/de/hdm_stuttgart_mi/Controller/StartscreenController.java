package de.hdm_stuttgart_mi.Controller;

import de.hdm_stuttgart_mi.Users.User;
import de.hdm_stuttgart_mi.Users.Roommate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Paint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static de.hdm_stuttgart_mi.Users.User.currentUser;


public class StartscreenController extends SuperController implements Initializable {

    private static final Logger log = LogManager.getLogger(StartscreenController.class);

    @FXML private MenuButton roommateMenuButton;
    @FXML private PasswordField password_pf;
    //@FXML private BorderPane root;

    User nav = new User();
    private void initMenuButton(){
        roommateMenuButton.setText("Mitbewohner");
        for(int i = 0;i<nav.roommateListLenght();i++ ){
            Roommate roommate = nav.getRoommate(i);
            int id = roommate.getID();
            MenuItem menuitem = new MenuItem(roommate.getFullname());
            menuitem.setId(""+id);
            menuitem.setOnAction(this::changeMenuButtonText);

            roommateMenuButton.getItems().add(menuitem);
        }
    }
    @FXML
    private void check(ActionEvent event) throws IOException {
        if(password_pf.getText().equals(currentUser.getPassword())){
            changeFirstScene(event);
        }
        else{
            password_pf.getStyleClass().add("error");
            password_pf.setText("");
        }

    }
    private void changeMenuButtonText(ActionEvent e){
        roommateMenuButton.setText(((MenuItem)e.getSource()).getText());
        roommateMenuButton.setTextFill(Paint.valueOf("#1d9399"));
        String Id =((MenuItem)e.getSource()).getId();
        int id = Integer.parseInt(Id);
        nav = new User(id);
        log.info("Der aktuelle User "+currentUser.getFullname());
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initMenuButton();
    }
}
