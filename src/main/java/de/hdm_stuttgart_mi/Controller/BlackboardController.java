package de.hdm_stuttgart_mi.Controller;

import de.hdm_stuttgart_mi.Blackboard.Blackboard;
import de.hdm_stuttgart_mi.Blackboard.Note;
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

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static de.hdm_stuttgart_mi.notificationAndUsers.Navigation.currentUser;

public class BlackboardController extends SuperController implements Initializable {
    @FXML private ListView<HBox> notesListed;
    @FXML private TextField noteContent;
    @FXML private ToggleButton pinnedSelection;

    Blackboard blackboard;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final Logger log = LogManager.getLogger(BlackboardController.class);

    private void initNotesListed(){
        ObservableList<HBox> observableList = FXCollections.observableArrayList();

        for(Note value : blackboard.getNoteList()){
            HBox hBox = new HBox();
                hBox.getStyleClass().add("grocerylist");

            Label content = new Label(value.getContent());
                content.getStyleClass().add("contentLabel");

            ToggleButton pinned = new ToggleButton();
                if (value.getIsPinned()){
                    pinned.setSelected(true);
                }

            Button delete = new Button("Löschen");
                delete.getStyleClass().add("deleteButton");
                delete.setOnAction(event -> deleteNote(value));


            VBox UserAndTime = new VBox(new Label(value.getTimestamp().format(formatter)), new Label(value.getAuthor()));
                UserAndTime.getStyleClass().add("columnthree");


            hBox.getChildren().addAll(content, pinned, UserAndTime, delete);

            HBox.setHgrow(content, Priority.ALWAYS);
            observableList.add(hBox);

        }
        notesListed.setItems(observableList);
        log.info("Blackboard List initialized");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        blackboard = new Blackboard();
        initNotesListed();
        log.info("open Blackboard");
    }

    private void deleteNote(Note value){
        blackboard.deleteNote(value);
        blackboard.safeBlackboard();
        initNotesListed();
    }

    private boolean noteExists(String content){
        for(Note note : blackboard.getNoteList()){
            if (note.getContent().equals(content)){
                return true;
            }
        }
        return false;
    }

    @FXML private void addNote() {
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
            initNotesListed();
            noteContent.setText("");
        }
    }







}
