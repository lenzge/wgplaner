package de.hdm_stuttgart_mi.Controller;

import de.hdm_stuttgart_mi.Blackboard.Blackboard;
import de.hdm_stuttgart_mi.Blackboard.Note;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static de.hdm_stuttgart_mi.Controller.ProfileController.colorOn;
import static de.hdm_stuttgart_mi.Users.User.currentUser;

public class BlackboardController extends SuperController implements Initializable {

    @FXML private ListView<HBox> notesListed;
    @FXML private TextField noteContent;
    @FXML private ToggleButton pinnedSelection;
    @FXML private ImageView pinIcon;

    //global
    Blackboard blackboard;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String TrashIconDarkMode = "src/main/resources/icons/trash.png";
    String PinIconDarkMode = "src/main/resources/icons/pin.png";
    String TrashIconLightMode = "src/main/resources/icons/darkTrash.png";
    String PinIconLightMode = "src/main/resources/icons/darkpin.png";

    //debugger
    private static final Logger log = LogManager.getLogger(BlackboardController.class);

    //Toggle Button in the blackboard.fxml
    public BlackboardController() throws FileNotFoundException{
    }
    FileInputStream pinIconPath = new FileInputStream(PinIconDarkMode);
    private final Image PIN = new Image(pinIconPath);

    //load nodeList into the NoteListView
    private void initNoteListView() throws FileNotFoundException {
        ObservableList<HBox> observableList = FXCollections.observableArrayList();

        for(Note value : blackboard.getNoteList()){

            HBox hBox = new HBox();
                hBox.getStyleClass().add("grocerylist");

            //shows the content
            Label content = new Label(value.getContent());
                content.getStyleClass().add("contentLabelWrap");
                content.setWrapText(true);

            // shows if the Note is pinned and change the pinned status
            ToggleButton pinned = new ToggleButton();
                pinned.getStyleClass().add("pinButtonListView");

                pinned.setGraphic(createPinImage());
                if (value.getIsPinned()){
                    pinned.setSelected(true);
                    pinned.setOnAction(event -> {
                        try {
                            blackboard.changePinNote(value,false);
                            blackboard.safeBlackboard();
                            blackboard.initNote();
                            initNoteListView();
                        } catch (IOException e) {
                            e.printStackTrace();
                            log.error("Icon wasn't found");
                        }
                    });

                } else {
                    pinned.setSelected(false);
                    pinned.setOnAction(event -> {
                        try {
                            blackboard.changePinNote(value,true);
                            blackboard.safeBlackboard();
                            blackboard.initNote();
                            initNoteListView();
                        } catch (IOException e) {
                            e.printStackTrace();
                            log.error("Icon wasn't found");
                        }
                    });

                }



            // Delete button
            Button delete = new Button();
                delete.setGraphic(createTrashImage());
                delete.getStyleClass().add("deleteButton");
                delete.setVisible(false); // only visible while hovering
                delete.setOnAction(event -> {
                    try {
                        deleteNote(value);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        log.error("Icon wasn't found");
                    }
                });

            VBox UserAndTime = new VBox(new Label(value.getTimestamp().format(formatter)), new Label(value.getAuthor()));
                UserAndTime.getStyleClass().add("userAndTimeVbox");

            hBox.getChildren().addAll(content, pinned, UserAndTime, delete);
            HBox.setHgrow(content, Priority.ALWAYS);
            hBox.setOnMouseEntered(event -> visible(delete));
            hBox.setOnMouseExited(event -> hidden(delete));

            observableList.add(hBox);
        }
        notesListed.setItems(observableList);
        log.info("BlackboardListView initialized");
    }

    //Images for the Buttons in light and dark mode
    private ImageView createTrashImage() throws FileNotFoundException {
        if (!colorOn){
            FileInputStream inputStream = new FileInputStream(TrashIconDarkMode);
            Image trashIcon = new Image(inputStream);
            ImageView trash = new ImageView(trashIcon);
            trash.setFitHeight(20);
            trash.setFitWidth(20);
            return trash;
        } else {
            FileInputStream inputStream = new FileInputStream(TrashIconLightMode);
            Image trashIcon = new Image(inputStream);
            ImageView trash = new ImageView(trashIcon);
            trash.setFitHeight(20);
            trash.setFitWidth(20);
            return trash;
        }
    }

    private ImageView createPinImage() throws FileNotFoundException {
        if (!colorOn){
            FileInputStream inputStream = new FileInputStream(PinIconDarkMode);
            Image pinIconList = new Image(inputStream);
            ImageView pinOnList = new ImageView(pinIconList);
            pinOnList.setFitWidth(20);
            pinOnList.setFitHeight(20);
            return pinOnList;
        } else {
            FileInputStream inputStream = new FileInputStream(PinIconLightMode);
            Image pinIconList = new Image(inputStream);
            ImageView pinOnList = new ImageView(pinIconList);
            pinOnList.setFitWidth(20);
            pinOnList.setFitHeight(20);
            return pinOnList;
        }
    }

    //start
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        blackboard = new Blackboard();
        pinIcon.setImage(PIN);
        try {
            initNoteListView();
        } catch (FileNotFoundException e) {
            log.error("Icon wasn't found");
            e.printStackTrace();
        }
        log.info("open Blackboard");
    }

    //delete note from noteList
    private void deleteNote(Note value) throws FileNotFoundException {
        blackboard.deleteNote(value);
        blackboard.safeBlackboard();
        initNoteListView();
    }

    //does the new note already exists?
    private boolean noteExists(String content){
        for(Note note : blackboard.getNoteList()){
            if (note.getContent().equals(content)){
                return true;
            }
        }
        return false;
    }

    // add note to the noteList
    @FXML private void addNote() throws FileNotFoundException {
        if(noteContent.getText() == null || noteContent.getText().equals("")){
            noteContent.getStyleClass().add("error");
            noteContent.setTooltip(new Tooltip("Leeres Eingabefeld"));
            noteContent.setOnMouseClicked((event) -> {noteContent.getStyleClass().remove("error"); noteContent.setTooltip(null);});
        } else if(noteExists(noteContent.getText())){
            noteContent.getStyleClass().add("error");
            noteContent.setTooltip(new Tooltip("Notiz existiert schon"));
            noteContent.setOnMouseClicked((event) -> {noteContent.getStyleClass().remove("error"); noteContent.setTooltip(null);});
        } else {
            blackboard.addNote(noteContent.getText(),currentUser,LocalDate.now(),pinnedSelection.isSelected());
            blackboard.safeBlackboard();
            blackboard.initNote();
            initNoteListView();
            noteContent.setText("");
        }
    }

    //for the elements, which should be only visible while hovering
    private void visible(Button delete) {
        delete.setVisible(true);
    }
    private void hidden(Button delete){
        delete.setVisible(false);
    }
}
