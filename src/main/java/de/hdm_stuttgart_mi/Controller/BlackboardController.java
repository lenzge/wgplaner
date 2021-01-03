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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static de.hdm_stuttgart_mi.notificationAndUsers.Navigation.currentUser;

public class BlackboardController extends Supercontroller implements Initializable {
    @FXML private ListView<HBox> notesListed;
    @FXML private TextField noteContent;
    @FXML private CheckBox pinned;

    Blackboard blackboard;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final Logger log = LogManager.getLogger(BlackboardController.class);

    private void initNotesListed(){
        ObservableList<HBox> observableList = FXCollections.observableArrayList();

        for(Note value : blackboard.getNoteList()){
            HBox hBox = new HBox();
                hBox.getStyleClass().add("hboxLena");

            Label content = new Label(value.getContent());
                content.getStyleClass().add("contentLabel");

            CheckBox pinned = new CheckBox();
                if (value.getIsPinned() == true){
                    pinned.setSelected(true);
                }
                //pinned.getStyleClass().add("toggle-button");


            //Label pinned = new Label();
              //  pinned.getStyleClass().add("contentLabel");

            Button delete = new Button("Löschen");
                delete.getStyleClass().add("deleteButton");
                delete.setOnAction(event -> deleteNote(value));

            Label date = new Label(value.getTimestamp().format(formatter));
                date.getStyleClass().add("columnthree");

            Label createdPin = new Label(value.getAuthor());


            hBox.getChildren().addAll(content, pinned, date, createdPin, delete);

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

    @FXML private void addNote(){
        if (noteContent.getText() == null || noteContent.getText().equals("")){
            noteContent.getStyleClass().add("error");
            noteContent.setTooltip(new Tooltip("Leer"));
            //remove error
            noteContent.setOnMouseClicked((event) -> {noteContent.getStyleClass().remove("error"); noteContent.setTooltip(null);});
        }
        else if (noteDoubled(noteContent.getText())){
            noteContent.getStyleClass().add("error");
            noteContent.setTooltip(new Tooltip("Existiert schon"));
            //remove error
            noteContent.setOnMouseClicked((event) -> {noteContent.getStyleClass().remove("error"); noteContent.setTooltip(null);});
        }
        else{
            blackboard.addNote(noteContent.getText(), currentUser, LocalDate.now(), pinned.isSelected());
            blackboard.safeBlackboard();
            blackboard.initNote();
            initNotesListed();
            noteContent.setText("");
        }
    }

    private boolean noteDoubled(String content){
        for(Note note:blackboard.getNoteList()){
            if (note.getContent().equals(content)){
                return true;
            }
        }
        return false;
    }



}
