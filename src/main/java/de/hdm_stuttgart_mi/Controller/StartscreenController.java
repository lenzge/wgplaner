package de.hdm_stuttgart_mi.Controller;

import de.hdm_stuttgart_mi.notificationAndUsers.Navigation;
import de.hdm_stuttgart_mi.notificationAndUsers.Roommate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static de.hdm_stuttgart_mi.notificationAndUsers.Navigation.currentUser;


public class StartscreenController extends Supercontroller implements Initializable {

    private static final Logger log = LogManager.getLogger(StartscreenController.class);

    @FXML private MenuButton roommateMenuButton;
    @FXML private GridPane root;

    Navigation nav = new Navigation();
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
    private void changeMenuButtonText(ActionEvent e){
        roommateMenuButton.setText(((MenuItem)e.getSource()).getText());
        roommateMenuButton.setTextFill(Paint.valueOf("#1d9399"));
        String Id =((MenuItem)e.getSource()).getId();
        int id = Integer.parseInt(Id);
        nav = new Navigation(id);
        log.info("Der aktuelle User "+currentUser.getFullname());
    }

    public void changeScene(ActionEvent event) throws IOException {

        final FXMLLoader loader = new FXMLLoader();

        Button button = ((Button)event.getSource());
        Parent sceneRoot;
        if(button.getId().equals("apply_bt")){
            //choose fxml Scene
            sceneRoot = loader.load(getClass().getResourceAsStream("/fxml/menu.fxml"));
            root.getChildren().clear();
            root.getChildren().add(sceneRoot);
        }
        else if(button.getId().equals("newRoommate_bt")){
            sceneRoot = loader.load(getClass().getResourceAsStream("/fxml/newRoommate.fxml"));
            root.getChildren().clear();
            root.getChildren().add(sceneRoot);

        }
        else {
            Label label = new Label("Tut uns Leid es ist etwas schief gelaufen. Bitte starte das Programm neu");
            sceneRoot = label;
        }
       /* //loader.setController(this);
        Scene scene =  new Scene(sceneRoot);
        //add Stylesheet
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        Stage window = (Stage)button.getScene().getWindow();

        window.setScene(scene);
        window.show();*/
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initMenuButton();
    }
}
