package de.hdm_stuttgart_mi.notificationAndUsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Navigation {

    private List<Roommate> roommateList = new ArrayList<Roommate>();
    public static Roommate currentUser;
    private SimpleDateFormat formatter=new SimpleDateFormat("DD-MM-YYYY");
    private File file = new File("src/main/resources/JSON/roommates.json");
    private static Logger log = LogManager.getLogger(Navigation.class);


    public Navigation(){
        initroommate();
        setCurrentUser(-1);

    }

    //static FormerRoommate formerRoommateList[];

    public void setCurrentUser(int index){
        if(index == -1){
           // currentUser= new Roommate("Default","User",-1,null,false,null,null);
            currentUser = roommateList.get(0);
        }
        else {
            currentUser = roommateList.get(index);
        }
    }

    public Roommate getCurrentUser(){
        return currentUser;
    }

    public  Roommate getRoommate(int i){
        return roommateList.get(i);
    }
   /** public static FormerRoommate getFormer(int i){
        return formerRoommateList[i];
    }
   **/

    public void addCurrentRoomate(Roommate newMate) throws IOException {
        String addRoommate = "{ \"firstname\":\""+newMate.getFirstname()+"\",\n" +
                "  \"lastname\":\""+newMate.getLastname()+"\",\n" +
                "  \"ID\": "+newMate.getID()+",\n" +
                "  \"phonenumber\":\""+newMate.getPhonenumber()+"\",\n" +
                "  \"current\":"+newMate.isCurrent()+",\n" +
                "  \"moveInDate\": \""+newMate.getMoveInDate()+"\",\n" +
                "  \"Birthday\": \""+newMate.getBirthday()+"\"},";
       FileWriter writer = new FileWriter(file,true);
       writer.append(addRoommate);
       roommateList.add(newMate);


    }

    public void initroommate(){

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
                Date moveInDate= formatter.parse(tempJasonObject.getString("moveInDate"));
                Date birthday= formatter.parse(tempJasonObject.getString("Birthday"));
                roommateList.add(new Roommate(firstname, lastname, ID, phonenumber, current, moveInDate, birthday));
    }
    log.info("Rommates initialized");
    }
    catch(Exception e){
        e.printStackTrace();
        log.error("rommate list couldn't be initialized");
    }
    }
}
