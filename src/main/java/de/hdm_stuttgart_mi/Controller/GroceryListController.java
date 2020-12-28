package de.hdm_stuttgart_mi.Controller;

import de.hdm_stuttgart_mi.GroceryList.GroceryList;
import de.hdm_stuttgart_mi.GroceryList.Iitem;
import de.hdm_stuttgart_mi.GroceryList.Item;
import de.hdm_stuttgart_mi.ItemFactory.ItemFactory;
import de.hdm_stuttgart_mi.notificationAndUsers.Navigation;
import de.hdm_stuttgart_mi.notificationAndUsers.Roommate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    @FXML private TextField itemContent;
    @FXML private MenuButton itemType;
    @FXML private Button addItem;

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

    private void initItemTypes(){
        itemType.setText("food");
        for(String type : ItemFactory.getAllItemTypes()) {
            MenuItem menuitem = new MenuItem(type);
            menuitem.setId(type);
            menuitem.setOnAction(event -> initType(menuitem));
            itemType.getItems().add(menuitem);
        }
    }

    private void initType(MenuItem menuItem){
        itemType.setText(menuItem.getText());
    }

    private void initItemListView(){

        GroceryList groceryList = new GroceryList();
        ObservableList<HBox> obsList = FXCollections.<HBox>observableArrayList();
        for(Iitem value : groceryList.getItemList()){
            HBox hbox = new HBox();
            hbox.setSpacing(5);
            hbox.setPrefHeight(50);
            hbox.setAlignment(Pos.CENTER);

            if (value.getPrice() != null) {
                Label type = new Label(value.getType());
                type.setStyle("-fx-text-fill: transparent");
                type.setPrefWidth(170);
                Label label= new Label(value.getContent());
               // label.setPrefWidth(720);
                label.setMinWidth(76);
                label.setMaxWidth(2000);
                VBox vbox = new VBox (new Label(value.getBoughtBy()),
                        new Label(value.getBoughtDate().format(formatter)));
                vbox.setPrefWidth(170);
                vbox.setMinWidth(76);
                vbox.setAlignment(Pos.CENTER_LEFT);
                Label price = new Label(value.getPrice());
                price.setPrefWidth(155);
                price.setMinWidth(76);
                hbox.getChildren().addAll(
                        label,
                        type,
                        vbox,
                        price
                );
                hbox.setOnMouseEntered((event) -> { type.setStyle("-fx-text-fill: #2f2e33");});
                hbox.setOnMouseExited((event) -> { type.setStyle("-fx-text-fill: transparent");});
                HBox.setHgrow(label, Priority.SOMETIMES);
            }
            else {
                Label type = new Label(value.getType());
                type.setStyle("-fx-text-fill: transparent");
                type.setPrefWidth(170);
                Label label = new Label(value.getContent());
                //label.setPrefWidth(720);
                label.setMinWidth(76);
                label.setMaxWidth(2000);
                TextField textfield = new TextField("€");
                textfield.setPrefWidth(170);
                textfield.setMinWidth(76);
                Button button = new Button ("check");
                button.setOnAction(event -> checkItem(textfield.getText(), value));
                button.setPrefWidth(155);
                button.setMinWidth(76);
                hbox.getChildren().addAll(
                        label,
                        type,
                        textfield,
                        button
                );
                hbox.setOnMouseEntered((event) -> { type.setStyle("-fx-text-fill: #2f2e33");});
                hbox.setOnMouseExited((event) -> { type.setStyle("-fx-text-fill: transparent");});
                HBox.setHgrow(label, Priority.SOMETIMES);
                HBox.setHgrow(button, Priority.SOMETIMES);
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

    @FXML private void addItem() {
        GroceryList groceryList = new GroceryList();
        groceryList.addItem(itemType.getText(), itemContent.getText(), currentUser);
        groceryList.safeItems();
        initItemListView();
        itemContent.setText("");
    }


    @Override public void initialize(URL location, ResourceBundle resources){
        initItemListView();
        initItemTypes();
        profilePic.setImage(currentProfilePic);
        profilePic.setFitWidth(55);
        profilePic.setFitHeight(55);
        roommateName.setText(currentUser.getFullname());
    }


}
