package de.hdm_stuttgart_mi.Controller;

import de.hdm_stuttgart_mi.Users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static de.hdm_stuttgart_mi.Controller.ExternMethods.fillImageView;
import static de.hdm_stuttgart_mi.Users.User.currentUser;

public class MenuController extends SuperController implements Initializable {


    @FXML private ImageView profilePic;
    @FXML private ImageView shutdownIcon;
    @FXML private Button profil;
    private static final Logger log = LogManager.getRootLogger();

    private void initPp() throws FileNotFoundException {
        fillImageView(profilePic,currentUser.getProfilepic(),55,55);
    }
    private void initShutdownIcon() throws FileNotFoundException {
        String shutdownIconPath = "src/main/resources/icons/shutdown.png";
        fillImageView(shutdownIcon,shutdownIconPath,25,25);
    }
    @FXML private void back(ActionEvent e) throws IOException {
        User user = new User();
        user.updateCurrentUser();
        changeScene(e);
    }



    @Override public void initialize(URL location, ResourceBundle resources){
        try {
            initPp();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("Profilepicture couldn't be initialized in menu");
        }
        profil.setText(currentUser.getFullname());
        try {
            initShutdownIcon();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("ShutdownIcon couldn't be initialized in menu");
        }
    }

}
