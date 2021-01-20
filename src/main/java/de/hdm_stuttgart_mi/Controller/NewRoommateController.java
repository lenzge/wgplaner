package de.hdm_stuttgart_mi.Controller;

import de.hdm_stuttgart_mi.Users.Roommate;
import de.hdm_stuttgart_mi.Users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static de.hdm_stuttgart_mi.Controller.ExternMethods.*;
import static de.hdm_stuttgart_mi.Controller.ProfileController.colorOn;

public class NewRoommateController extends SuperController implements Initializable {
    private static final Logger log = LogManager.getLogger(NewRoommateController.class);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private User user = new User();

    private LocalDate birthday;
    private String profilePic;

    @FXML
    private DatePicker birthday_dp;
    @FXML
    private HBox profilepicList;
    @FXML
    private TextField firstname_tf;
    @FXML
    private TextField lastname_tf;
    @FXML
    private TextField phonenumber_tf;
    @FXML
    private ImageView backbutton;
    @FXML
    private PasswordField password_tf;


    @FXML
    private void datepicked() {
        String value = birthday_dp.getValue().format(formatter);
        birthday = LocalDate.parse(value, formatter);
        log.debug(birthday);
    }


    @FXML
    private void apply(ActionEvent e) throws IOException {
        boolean applyable = true;
        String firstname = "", lastname = "", phonenumber = "",password="";
        if (validName(firstname_tf.getText())) {
            firstname = firstname_tf.getText();
        } else {
            firstname_tf.setText("Bitte trage deinen Vornamen ein. Es sind keine Zahlen oder Sonderzeichen erlaubt");
            firstname_tf.getStyleClass().add("error");
            applyable = false;
        }

        if (validName(lastname_tf.getText())) {
            lastname = lastname_tf.getText();
        } else {
            lastname_tf.setText("Bitte trage deinen Nachnamen ein. Es sind keine Zahlen oder Sonderzeichen erlaubt");
            lastname_tf.getStyleClass().add("error");
            applyable = false;
        }

        if(validPassword(password_tf.getText())){
            password=password_tf.getText();
        }
        else {
            password_tf.setText("");
            password_tf.getStyleClass().add("error");
            applyable = false;
        }

        if (validPhoneNumber(phonenumber_tf.getText())) {
            phonenumber = phonenumber_tf.getText();
        } else {
            phonenumber_tf.setText("Nur Zahlen in der Telefonnummer erlaubt");
            phonenumber_tf.getStyleClass().add("error");
            applyable = false;
        }

        if (birthday_dp.getValue() == null) {
            birthday_dp.getStyleClass().add("error");
            applyable = false;
        }

        if (profilePic==null){
            applyable = false;
            profilepicList.getStyleClass().add("error");
        }

        if (applyable) {
            Roommate newRoommate = new Roommate(firstname, lastname, 0, phonenumber, password, LocalDate.now(), birthday, profilePic);
            log.debug("Roommate created");


            user.addNewRoommate(newRoommate);
            int id = user.getRoommate(user.roommateListLenght() - 1).getID();
            user = new User(id);

            super.changeFirstScene(e);
        }


    }

    private void initIcon() throws FileNotFoundException {

        String ColorOffPath = "src/main/resources/icons/back.png";
        String ColorOnPath = "src/main/resources/icons/darkback.png";
        if (colorOn) {
            fillImageView(backbutton, ColorOnPath, 30, 25);
        } else {
            fillImageView(backbutton, ColorOffPath, 30, 25);
        }

    }

    private void initProfilpics() throws FileNotFoundException {

        ToggleGroup toggleGroup = new ToggleGroup();

        File directory = new File("src/main/resources/images");
        //all Filenames in the directory are written into a Stringarray
        String[] list = directory.list();
        //sicherstellen dass die Liste nicht null ist
        assert list != null;
        for (String profilepicPath : list) {
            ToggleButton button = new ToggleButton();

            ImageView showProfilePic = getImageView("src/main/resources/images/" + profilepicPath, 100, 100);

            button.setGraphic(showProfilePic);
            button.setId("src/main/resources/images/" + profilepicPath);

            button.setOnAction(event -> profilButton(button));

            toggleGroup.getToggles().add(button);
            profilepicList.getChildren().add(button);
        }
    }

    private void profilButton(ToggleButton button) {
        profilepicList.getStyleClass().remove("error");
        profilePic = button.getId();
        button.setSelected(true);
        log.debug(profilePic);
    }

    @FXML
    private void clear(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof TextField) {
            TextField textfield = (TextField) mouseEvent.getSource();
            textfield.getStyleClass().remove("error");
            textfield.setText("");
        }
        if (mouseEvent.getSource() instanceof DatePicker) {
            DatePicker textfield = (DatePicker) mouseEvent.getSource();
            textfield.getStyleClass().remove("error");
        }
    }
    @FXML private void showPassword(MouseEvent e){
        while(e.isDragDetect()){

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            initProfilpics();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("Profilepics not initialized");
        }
        try {
            initIcon();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("Icon not initialized");
        }
    }
}