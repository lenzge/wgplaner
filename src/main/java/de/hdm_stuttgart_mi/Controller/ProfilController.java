package de.hdm_stuttgart_mi.Controller;

import de.hdm_stuttgart_mi.notificationAndUsers.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static de.hdm_stuttgart_mi.notificationAndUsers.Navigation.currentUser;

public class ProfilController extends Supercontroller implements Initializable {

    private static final Logger log = LogManager.getLogger(ProfilController.class);
    final private DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd.MM.yyyy");
    Navigation nav = new Navigation();

    @FXML private ImageView ownProfilePic;
    @FXML private ImageView editIconView;
    @FXML private Button edit;
    @FXML private Label birthday_lb;
    @FXML private Label moveInDate;
    @FXML private Label phonenumber;
    @FXML private Label fullname_lb;
    @FXML private HBox profilpics;
    @FXML private DatePicker birthdaypicker;
    @FXML private TextField phonenumber_tf;
    @FXML private Button apply_bt;



   // first Init Methods so everything is set up

    private void initPhonenumber(){
        phonenumber.setText(currentUser.getPhonenumber());
    }

    private void initMoveInDate(){
        moveInDate.setText("Du bist am "+currentUser.getMoveInDate().format(formatter)+" eingezogen");
    }
    private void initIcon() throws FileNotFoundException {
        FileInputStream editIconPath = new FileInputStream("src/main/resources/icons/stift.png");
        Image editIcon= new Image(editIconPath);
        editIconView.setImage(editIcon);
        editIconView.setFitHeight(20);
        editIconView.setFitWidth(20);
    }
    private void setOwnPp() throws FileNotFoundException {
        FileInputStream currentPp = new FileInputStream(currentUser.getProfilepic());
        Image currentProfilePic= new Image(currentPp);
        ownProfilePic.setImage(currentProfilePic);
        ownProfilePic.setFitHeight(80);
        ownProfilePic.setFitWidth(80);
    }

//OnAction method for "Bearbeiten"-Button
    @FXML
    private void edit(){

            apply_bt.setVisible(true);
            edit.setVisible(false);
            birthdaypicker.setVisible(true);
            birthdaypicker.setDisable(false);
            profilpics.setVisible(true);
            phonenumber_tf.setVisible(true);
    }
//On action Method that apllys changes
   @FXML private void apply(ActionEvent event) throws IOException {
       boolean applyable = true;

       if (birthdaypicker.getValue() != null) {
           currentUser.setBirthday(birthdaypicker.getValue());
           birthday_lb.setText(currentUser.getBirthday().format(formatter));
           nav.updateCurrentUser();
       }
       if (!(phonenumber_tf.getText().equals(""))) {
           if (phonenumber_tf.getText().matches("^(\\d+)$")) {
               currentUser.setPhonenumber(phonenumber_tf.getText());
               initPhonenumber();
           } else {
               phonenumber_tf.setText("Nur Zahlen in der Telefonnummer erlaubt");
               phonenumber_tf.getStyleClass().add("error");
               applyable = false;
           }
       }

       if (applyable) {
           edit.setVisible(true);
           apply_bt.setVisible(false);

           birthdaypicker.setVisible(false);
           birthdaypicker.setDisable(true);
           profilpics.setVisible(false);
           phonenumber_tf.setVisible(false);
           phonenumber_tf.setText("");
           birthdaypicker.setValue(null);


           BorderPane root = (BorderPane) ((Button) event.getSource()).getScene().getRoot();
           Node sceneRoot = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
           BorderPane.setMargin(sceneRoot,new Insets(0,10,0,0));
           root.setLeft(sceneRoot);
       }
   }
   //OnMouseClicked Method that clears the Textfield when clicked on it
   @FXML private void clear(MouseEvent mouseEvent) {
            TextField textfield = (TextField) mouseEvent.getSource();
            textfield.getStyleClass().remove("error");
            textfield.setText("");
        }

   //OnActionEvent Method that delets the User that is currently logged in and switches back to login screen
   @FXML public void deleteUser(ActionEvent e) throws IOException {
        nav.deleteFromList();
        super.changeScene(e);
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
                Image profilePicImage = new Image(input);
                ImageView showProfilePic = new ImageView(profilePicImage);

                showProfilePic.setFitWidth(80);
                showProfilePic.setFitHeight(80);

                button.setGraphic(showProfilePic);
                button.setId("src/main/resources/images/" + profilepicPath);

                button.setOnAction(event -> {
                    try {
                        changeProfilepic(button);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        log.error("Profilpicbutton event failed");
                    }
                });

                toggleGroup.getToggles().add(button);
                profilpics.getChildren().add(button);
            }
        }
    }

    //OnActionEvent Method for Profilpicbuttons

    private void changeProfilepic(ToggleButton button) throws FileNotFoundException {
        currentUser.setProfilepic(button.getId());
        nav.updateCurrentUser();
        setOwnPp();
        setProfilePics();
    }





    @Override public void initialize(URL location, ResourceBundle resources){
        fullname_lb.setText(currentUser.getFullname());

        initMoveInDate();
        initPhonenumber();

        birthday_lb.setText(currentUser.getBirthday().format(formatter));
        try {
            setOwnPp();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("current ProfilPic was not initialized");
        }
        try {
            setProfilePics();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("ProfilPics to choose were not initialized");
        }
        try {
            initIcon();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("Icon was not initialized");
        }
    }
}
