package de.hdm_stuttgart_mi.notificationAndUsers;

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


public class Navigation {

    private List<Roommate> roommateList;
    private DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private File file = new File("src/main/resources/JSON/roommates.json");

    public static Roommate currentUser;

    private static Logger log = LogManager.getLogger(Navigation.class);


    public Navigation(){
        initroommate();

    }
    public Navigation(int i){
        initroommate();
        setCurrentUser(i);
    }

    //static FormerRoommate formerRoommateList[];

    private void setCurrentUser(int index){
        if(index == -1){
           // currentUser= new Roommate("Default","User",-1,null,false,null,null);
            currentUser = roommateList.get(0);
        }
        else {
            currentUser = roommateList.get(index);
        }
    }

    public int roommateListLenght(){
        return roommateList.size();
    }

    public  Roommate getRoommate(int i){
        return roommateList.get(i);
    }
   /** public static FormerRoommate getFormer(int i){
        return formerRoommateList[i];
    }
   **/
    public void saveRoommates(){
        JSONObject obj = new JSONObject();
        JSONArray list = new JSONArray();
        for (Roommate roommate : roommateList) {
            JSONObject innerObj = new JSONObject();
            innerObj.put("firstname", roommate.getFirstname());
            innerObj.put("lastname", roommate.getLastname());
            innerObj.put("ID", roommate.getID());
            innerObj.put("phonenumber", roommate.getPhonenumber());
            innerObj.put("current", roommate.isCurrent());
            innerObj.put("moveInDate", roommate.getMoveInDate().format(formatter));
            innerObj.put("birthday", roommate.getBirthday().format(formatter));
            innerObj.put("profilepic", roommate.getProfilepic());
            list.add(innerObj);
        }
        obj.put("roommates", list);
        log.debug(obj);

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void addCurrentRoomate(Roommate newMate){
        newMate.setID(roommateListLenght());
        roommateList.add(newMate);
        saveRoommates();
    }

    public void initroommate(){
        roommateList = new ArrayList<Roommate>();
        JSONParser parser = new JSONParser();
    try{
        String content = new String(Files.readAllBytes(Paths.get(file.toURI())),"UTF-8");
        JSONObject json = (JSONObject) parser.parse(content);

        JSONArray jsonArray = (JSONArray) json.get("roommates");
        for(int i=0;i<jsonArray.size();i++) {
            JSONObject tempJasonObject = (JSONObject) jsonArray.get(i);

                String firstname    = (String) tempJasonObject.get("firstname");
            //log.debug("firstname initialized");
                String lastname     = (String) tempJasonObject.get("lastname");
            //log.debug("lastname initialized");
                int ID            = i;
            //log.debug("id initialized");
                String phonenumber  = (String) tempJasonObject.get("phonenumber");
            //log.debug("phonenumber initialized");
                boolean current     = (boolean)tempJasonObject.get("current");
            //log.debug("current initialized");
                LocalDate moveInDate     = LocalDate.parse((String) tempJasonObject.get("moveInDate"), formatter);
                LocalDate birthday       = LocalDate.parse((String) tempJasonObject.get("birthday"), formatter);
                String profilePic   = (String) tempJasonObject.get("profilepic");

                /*int ID = Math.toIntExact(id);*/

                roommateList.add(new Roommate(firstname, lastname, ID, phonenumber, current, moveInDate, birthday,profilePic));
                log.debug("roommate initialized");
        }
        log.info("Rommates initialized");
    }

    catch(Exception e){
        e.printStackTrace();
        log.error("rommate list couldn't be initialized");
    }
    }
}
