package de.hdm_stuttgart_mi.Blackboard;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Blackboard {

    public static void main(String[] args) {

        try{

            File file = new File("src\\main\\resources\\JSON\\notes.json");
            String jsonContent = new String(Files.readAllBytes(Paths.get(file.toURI())),"UTF-8");
            JSONObject json = new JSONObject(jsonContent);

            JSONArray jsonArray = json.getJSONArray("notes");

            for(int x = 0; x < jsonArray.length(); x++){
                JSONObject jsonObjectFromArray = jsonArray.getJSONObject(x);

                String content = jsonObjectFromArray.getString("content");
                String author = jsonObjectFromArray.getString("author");

                System.out.println("Note: " + content);
                System.out.println("Author: " + author);
            }

        } catch(Exception e){
            e.printStackTrace();
        }

    }
}
