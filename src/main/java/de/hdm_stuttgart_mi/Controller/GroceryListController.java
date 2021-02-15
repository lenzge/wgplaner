package de.hdm_stuttgart_mi.Controller;

import de.hdm_stuttgart_mi.GroceryList.GroceryList;
import de.hdm_stuttgart_mi.GroceryList.Iitem;
import de.hdm_stuttgart_mi.GroceryList.STATUS;
import de.hdm_stuttgart_mi.ItemFactory.ItemFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import static de.hdm_stuttgart_mi.Users.User.currentUser;


public class GroceryListController implements Initializable {

    @FXML private ListView<HBox> itemlistView;
    @FXML private TextField itemContent;
    @FXML private MenuButton itemType;

    //global
    private GroceryList groceryList;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String filenameLightTrashIcon = "src/main/resources/icons/trash.png";
    String filenameDarkTrashIcon = "src/main/resources/icons/darkTrash.png";

    //debugger
    private static final Logger log = LogManager.getRootLogger();


    //loading gui

    //depending on ItemFactory
    private void initItemTypes(){
        itemType.setText("Lebensmittel"); //default, because food is the most used type
        for(String type : ItemFactory.getAllItemTypes()) {
            MenuItem menuitem = new MenuItem(type);
            menuitem.setOnAction(event -> initType(menuitem));
            itemType.getItems().add(menuitem);
            log.debug(type + " added as menuitem");
        }
    }
    private void initType(MenuItem menuItem){
        itemType.setText(menuItem.getText());
        log.debug(itemType.getText() + " is the chosen Itemtype");
    }

    //loads every item from itemList into the ListView
    private void initItemListView() throws FileNotFoundException {

        ObservableList<HBox> obsList = FXCollections.observableArrayList();

        for(Iitem value : groceryList.getItemList()){
            HBox hbox = new HBox();
                hbox.getStyleClass().add("grocerylist");
            Label content = new Label(value.getContent());
                content.getStyleClass().add("contentLabel");
            Label type = new Label(value.getType());
                type.getStyleClass().add("typeLabel");
                type.setVisible(false); //only visible while hovering
            Button delete = new Button();
                if (ProfileController.colorOn ) delete.setGraphic(ExternMethods.getImageView(filenameDarkTrashIcon, 20, 20));
                else delete.setGraphic(ExternMethods.getImageView(filenameLightTrashIcon, 20, 20));
                delete.getStyleClass().add("deleteButton");
                delete.setVisible(false); //only visible while hovering
                delete.setOnAction(event -> {
                    try {
                        deleteItem(value);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        log.error("Icon wasn't found");
                    }
                });

            //bought items
            if (value.getPrice() != null) {
                VBox bought = new VBox (new Label(value.getBoughtBy()), new Label(value.getBoughtDate().format(formatter)));
                    bought.getStyleClass().add("columnthree");
                Label price = new Label(value.getPrice());
                    price.getStyleClass().add("columnfour");

                hbox.getChildren().addAll(content, type, bought, price, delete);
            }

            //non-bought items
            else {
                TextField price = new TextField("€");
                    price.getStyleClass().add("columnthree");
                Button check = new Button ("kaufen");
                    check.getStyleClass().add("columnfour");
                    check.setOnAction(event -> {
                        try {
                            checkItem(value, price);
                        } catch (FileNotFoundException e) {
                            log.error("Icon wasn't found");
                            e.printStackTrace();
                        }
                    });

                hbox.getChildren().addAll(content, type, price, check, delete);
            }

            HBox.setHgrow(content, Priority.ALWAYS);
            hbox.setOnMouseEntered(event -> visible(type,delete));
            hbox.setOnMouseExited(event -> hidden(type,delete));
            obsList.add(hbox);

        }
        itemlistView.setItems(obsList);
        log.info("ListView initialzied");
    }

    //start
    @Override public void initialize(URL location, ResourceBundle resources){
        groceryList = new GroceryList();
        try {
            initItemListView();
        } catch (FileNotFoundException e) {
            log.error("Icon wasn't found");
            e.printStackTrace();
        }
        initItemTypes();
        log.info("open GroceryList");
    }



    //edit functions

    //change item from non-bought to bought
    private void checkItem(Iitem value, TextField textfield) throws FileNotFoundException {
        boolean tryBuy = groceryList.boughtItem(value, textfield.getText(), currentUser);

        //error, if price is no price
        if(!tryBuy) {
            textfield.setText("€");
            textfield.getStyleClass().add("error");
            textfield.setOnMouseClicked((event) -> {textfield.getStyleClass().remove("error"); });
        }
        else {
            groceryList.safeItems();
            groceryList.initItems();
            initItemListView();
        }
    }

    //delete item from itemList
    private void deleteItem(Iitem value) throws FileNotFoundException {
        groceryList.deleteItem(value);
        groceryList.safeItems();
        initItemListView();
    }

    //add item to itemList
    @FXML private void addItem() throws FileNotFoundException {
        STATUS status = groceryList.addItem(itemType.getText(), itemContent.getText(), currentUser);
        switch (status){
            //item needs content
            case EMPTY:
                itemContent.getStyleClass().add("error");
                itemContent.setTooltip(new Tooltip("Leeres Eingabefeld"));
                //remove error
                itemContent.setOnMouseClicked((event) -> {itemContent.getStyleClass().remove("error"); itemContent.setTooltip(null); });
                break;
            //item shouldn't already exists in itemList
            case EXISTS:
                itemContent.getStyleClass().add("error");
                itemContent.setTooltip(new Tooltip("Item existiert schon"));
                itemContent.setOnMouseClicked((event) -> {itemContent.getStyleClass().remove("error"); itemContent.setTooltip(null);});
                break;
            case RIGHT:
                groceryList.addItem(itemType.getText(), itemContent.getText(), currentUser);
                groceryList.safeItems();
                groceryList.initItems();
                initItemListView();
                itemContent.setText("");
        }

    }


    // helper functions

    //for elements, which should only visible while hovering
    private void visible(Label type, Button delete){
        type.setVisible(true);
        delete.setVisible(true);
    }
    private void hidden (Label type, Button delete){
        type.setVisible(false);
        delete.setVisible(false);
    }

}
