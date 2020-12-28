package de.hdm_stuttgart_mi.Controller;

import de.hdm_stuttgart_mi.GroceryList.GroceryList;
import de.hdm_stuttgart_mi.GroceryList.Iitem;
import de.hdm_stuttgart_mi.GroceryList.Item;
import de.hdm_stuttgart_mi.notificationAndUsers.Navigation;
import de.hdm_stuttgart_mi.notificationAndUsers.Roommate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import static de.hdm_stuttgart_mi.notificationAndUsers.Navigation.currentUser;

public class GroceryListController implements Initializable {

    @FXML private ListView<HBox> itemlistView;

    @FXML private Button blackboard_bt;
    @FXML private Button grocerylist_bt;
    @FXML private Button roommates_bt;
    @FXML private ImageView profilePic;
    @FXML private Label roommateName;


    Navigation nav = new Navigation(3);
    FileInputStream input = new FileInputStream(currentUser.getProfilepic());
    private Image currentProfilePic= new Image(input);
    public GroceryListController() throws FileNotFoundException {
    }


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    private void initItemListView(){

        GroceryList groceryList = new GroceryList();
        ObservableList<HBox> obsList = FXCollections.<HBox>observableArrayList();
        for(Iitem value : groceryList.getItemList()){
            HBox hbox = new HBox();
            if (value.getPrice() != null) {
                hbox.getChildren().addAll(
                        new Label(value.getContent()),
                        new VBox (new Label(value.getPrice()),
                                  new Label(value.getBoughtDate().format(formatter))),
                        new Label(value.getBoughtBy())
                );
            }
            else {
                TextField textfield = new TextField("price");
                Button button = new Button ("check");
                button.setOnAction(event -> checkItem(textfield.getText(), value));
                hbox.getChildren().addAll(
                        new Label(value.getContent()),
                        textfield,
                        button
                );
            }
            obsList.add(hbox);
        }

        itemlistView.setItems(obsList);
    }

    private void checkItem(String price, Iitem value) {
        GroceryList groceryList = new GroceryList();
        groceryList.boughtItem(value, price, currentUser);
        groceryList.safeItems();
        initItemListView();
    }


    @FXML private void blackboardButton(){
        blackboard_bt.setText("gedrückt");
    }
    @FXML private void groceryListButton(){
        grocerylist_bt.setText("gedrückt");
    }
    @FXML private void roommatesButton(){ roommates_bt.setText("gedrückt"); }




    @Override public void initialize(URL location, ResourceBundle resources){
        initItemListView();
        profilePic.setImage(currentProfilePic);
        profilePic.setFitWidth(55);
        profilePic.setFitHeight(55);
        roommateName.setText(currentUser.getFullname());
    }

}
