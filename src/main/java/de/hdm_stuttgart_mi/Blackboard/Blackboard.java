package de.hdm_stuttgart_mi.Blackboard;

import de.hdm_stuttgart_mi.notificationAndUsers.Roommate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Blackboard {

    //Logger
    private static final Logger log = LogManager.getLogger(Blackboard.class);

    //Global collection
    List<Note> noteList;

    //JSON File
    File file = new File("src\\main\\resources\\JSON\\notes.json");

    //Date Format
    DateTimeFormatter date = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public void initNote() {
        noteList = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try{
            String jsonContent = new String(Files.readAllBytes(Paths.get(file.toURI())),"UTF-8");
            JSONObject json = (JSONObject) parser.parse(jsonContent);

            JSONArray jsonArray = (JSONArray) json.get("note");
            for (Object o : jsonArray) {
                JSONObject jsonObjectFromArray = (JSONObject) o;

                String content = (String) jsonObjectFromArray.get("content");
                String author = (String) jsonObjectFromArray.get("author");
                LocalDate timestamp = LocalDate.parse((String) jsonObjectFromArray.get("timestamp"), date);
                boolean isPinned = (boolean) jsonObjectFromArray.get("isPinned");

                Note note = new Note(content, author, timestamp, isPinned);
                noteList.add(note);
                log.debug("add note: " + note.toString());
            }
            log.info("Blackboard initialized");

        } catch(Exception e){
            e.printStackTrace();
            log.error("IOException");
        }
    }
    public void safeBlackboard() {
        JSONObject obj = new JSONObject();
        JSONArray notes = new JSONArray();
        for (Note note : noteList){
            JSONObject entries = new JSONObject();
            entries.put("content",note.getContent());
            entries.put("author",note.getAuthor());
            entries.put("timestamp",note.getTimestamp().format(date));
            entries.put("isPinned",note.getIsPinned());
            notes.add(entries);
        }
        obj.put("note",notes);
        System.out.println(obj);

        try(FileWriter writer = new FileWriter(file)){
            writer.write(obj.toJSONString());
            log.info("Notes safed");
        }catch (IOException e){
            e.printStackTrace();
            log.error("IOException");
        }
    }

    public List<Note> getNoteList(){
        return noteList;
    }

    public Blackboard(){
        log.debug("try to initialize blackboard");
        initNote();
    }

    public void addNote(String content, Roommate author, LocalDate timestamp, boolean isPinned){
        Note note = new Note(content, author.getFullname(), timestamp, isPinned);
        noteList.add(note);
        log.info(note.toString() + " added");

    }

    public void deleteNote(Note note){


        log.info(note.toString() + " deleted");
    }

    public void pinnNote(Note note, boolean isPinned){


        log.info(note.toString() + " is pinned");
        log.info(note.toString() + " isn´t pinned anymore");
    }


    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Note note: noteList) {
            stringBuilder.append(note.getContent()).append(", ");
            stringBuilder.append(note.getAuthor()).append(", ");
            stringBuilder.append(note.getTimestamp()).append(", ");
            stringBuilder.append(note.getIsPinned()).append("\n");
        }
        return stringBuilder.toString();
    }

}
