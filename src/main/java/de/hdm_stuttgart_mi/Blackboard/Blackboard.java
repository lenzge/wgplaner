package de.hdm_stuttgart_mi.Blackboard;


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

    List<Note> noteList = new ArrayList<>();

    File file = new File("src\\main\\resources\\JSON\\notes.json");

    DateTimeFormatter date = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public void initNote() {
        JSONParser parser = new JSONParser();
        try{

            String jsonContent = new String(Files.readAllBytes(Paths.get(file.toURI())),"UTF-8");
            JSONObject json = (JSONObject) parser.parse(jsonContent);

            JSONArray jsonArray = (JSONArray) json.get("notes");
            for(int x = 0; x < jsonArray.size(); x++){
                JSONObject jsonObjectFromArray = (JSONObject) jsonArray.get(x);

                String content = (String) jsonObjectFromArray.get("content");
                String author = (String) jsonObjectFromArray.get("author");
                LocalDate timestamp = LocalDate.parse((String) jsonObjectFromArray.get("timestamp"), date);
                boolean isPinned = (boolean) jsonObjectFromArray.get("isPinned");

                Note note = new Note(content, author, timestamp, isPinned);
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void safeBlackboard() {
        JSONObject obj = new JSONObject();
        JSONArray notes = new JSONArray();
        for (Note note : noteList){
            JSONObject entries = new JSONObject();
            entries.put("content",note.getContent());
            entries.put("author",note.getAuthor());
            entries.put("timestamp",note.getTimestamp());
            entries.put("isPinned",note.getIsPinned());
            notes.add(entries);
        }
        obj.put("note",notes);
        System.out.println(obj);

        try(FileWriter writer = new FileWriter(file)){
            writer.write(obj.toJSONString());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
