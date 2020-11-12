package de.hdm_stuttgart_mi.notificationAndUsers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

public class TestingJSOn {
    SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy");
    public static int index=1;

    public Roommate initCurrentUser(){
        File file = new File("../../../../resources/JSON/roommates.json");
        Roommate currentUser = new Roommate();
        try{
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())),"UTF-8");
            JSONObject json = new JSONObject(content);

            JSONArray jsonArray = json.getJSONArray("currentroommate");

            JSONObject tempJasonObject = jsonArray.getJSONObject(index);

            currentUser.setFirstname(tempJasonObject.getString("firstname"));
            currentUser.setLastname(tempJasonObject.getString("lastname"));
            currentUser.setID(tempJasonObject.getInt("ID"));
            currentUser.setPhonenumber(tempJasonObject.getString("phonenumber"));
            currentUser.setCurrent(tempJasonObject.getBoolean("current"));
            currentUser.setMoveInDate(formatter.parse(tempJasonObject.getString("MoveInDate")));
            currentUser.setBirthday(formatter.parse(tempJasonObject.getString("Birthday")));

        }
        catch(Exception e){
            e.printStackTrace();
        }
           return currentUser;
        }

    }

