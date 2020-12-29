package de.hdm_stuttgart_mi.Controller;

import de.hdm_stuttgart_mi.notificationAndUsers.Navigation;
import de.hdm_stuttgart_mi.notificationAndUsers.Roommate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class NewRoommateController extends Supercontroller implements Initializable {
    private static final Logger log = LogManager.getLogger(NewRoommateController.class);
    private DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private LocalDate birthday;
    private String profilePic;
    @FXML private DatePicker birthday_dp;
    @FXML private HBox profilepicList;
    @FXML private TextField firstname_tf;
    @FXML private TextField lastname_tf;
    @FXML private TextField phonenumber_tf;
    @FXML private Label birthday_lb;
    Navigation nav = new Navigation();
    @FXML private void datepicked(){
        String value = birthday_dp.getValue().format(formatter);
        birthday = LocalDate.parse(value,formatter);
        log.info(birthday);
    }
    @FXML private void apply(ActionEvent e) throws IOException {
        boolean applyable=true;
        String firstname="",lastname="",phonenumber="";
        if(firstname_tf.getText() ==null || firstname_tf.getText().equals("")||firstname_tf.getText().matches("[^a-zA-Z]")){
            firstname_tf.setText("Bitte trage deinen Vornamen ein. Es sind keine Zahlen erlaubt");
            firstname_tf.setStyle("-fx-text-fill: red");
            applyable=false;}
        else {firstname = firstname_tf.getText();}
        if(lastname_tf.getText() ==null || lastname_tf.getText().equals("")||lastname_tf.getText().matches("[^a-zA-Z]")){
            lastname_tf.setText("Bitte trage deinen Nachnamen ein. Es sind keine Zahlen erlaubt");
            lastname_tf.setStyle("-fx-text-fill: red");
            applyable=false;}
        else{lastname = lastname_tf.getText();}
        if(phonenumber_tf.getText().matches("^(\\d+)$"))
        {
         phonenumber = phonenumber_tf.getText();
        }
        else {
            phonenumber_tf.setText("Nur Zahlen in der Telefonnummer erlaubt");
            phonenumber_tf.setStyle("-fx-text-fill: red");
            applyable=false;}
        if(birthday_dp.getValue()==null){
            birthday_dp.setStyle("-fx-border-color: red;");
            applyable=false;
        }
        if(applyable)
        {
            Roommate newRoommate = new Roommate(firstname,lastname,0,phonenumber,true,LocalDate.now(),birthday,profilePic);
            log.debug("Roommate created");
            nav.addCurrentRoomate(newRoommate);
            int id= nav.getRoommate(nav.roommateListLenght()-1).getID();
            nav = new Navigation(id);

            super.changeScene(e);
        }



    }

    private void initProfilpics() throws FileNotFoundException {

        ToggleGroup toggleGroup = new ToggleGroup();

        File directory = new File("src/main/resources/images");
       //all Filenames in the directory are written into a Stringarray
        String[] list = directory.list();
       //sicherstellen dass die Liste icht null ist
        assert list != null;
        for(String profilepicPath : list){
            ToggleButton button = new ToggleButton();
            FileInputStream input = new FileInputStream("src/main/resources/images/"+profilepicPath);
            Image profilePic= new Image(input);
            ImageView showProfilePic = new ImageView(profilePic);
            showProfilePic.setFitWidth(100);
            showProfilePic.setFitHeight(100);
            button.setGraphic(showProfilePic);
            button.setId("src/main/resources/images/"+profilepicPath);

            button.setOnAction(event -> profilButton(button));

            toggleGroup.getToggles().add(button);
            profilepicList.getChildren().add(button);
        }
    }
   private void profilButton(ToggleButton button){

        profilePic = button.getId();
        button.setSelected(true);
        log.debug(profilePic);
   }

   @FXML private void clear(MouseEvent mouseEvent){
     TextField textfield = (TextField) mouseEvent.getSource();
     textfield.setStyle("-fx-text-fill: #CBCBCB");
     textfield.setText("");

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            initProfilpics();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("File not found");
        }

    }
}
