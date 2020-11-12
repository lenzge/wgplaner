package de.hdm_stuttgart_mi.notificationAndUsers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Navigation {

    static Roommate RoommateList[]=new Roommate[2];
    //static FormerRoommate formerRoommateList[];


    public static Roommate getCurrent(int i){
        return RoommateList[i];
    }
   /** public static FormerRoommate getFormer(int i){
        return formerRoommateList[i];
    }
   **/
    SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy");


    public static void addCurrentRoomate(Roommate newMate){
    }

    public void initroommate(){
    File file = new File("../../../../resources/JSON/roommates.json");
    try{
    String content = new String(Files.readAllBytes(Paths.get(file.toURI())),"UTF-8");
    JSONObject json = new JSONObject(content);

    JSONArray jsonArray = json.getJSONArray("currentroommate");
    for(int i=0; i<jsonArray.length();i++){
    JSONObject tempJasonObject = jsonArray.getJSONObject(i);

                String firstname = tempJasonObject.getString("firstname");
                String lastname= tempJasonObject.getString("lastname");
                int ID = tempJasonObject.getInt("ID");
                String phonenumber= tempJasonObject.getString("phonenumber");
                boolean current= tempJasonObject.getBoolean("current");
                Date moveInDate= formatter.parse(tempJasonObject.getString("MoveInDate"));
                Date birthday= formatter.parse(tempJasonObject.getString("Birthday"));
                RoommateList[i]=new Roommate(firstname, lastname, ID, phonenumber, current, moveInDate, birthday);
    }
    }
    catch(Exception e){
        e.printStackTrace();
    }
    }
}
