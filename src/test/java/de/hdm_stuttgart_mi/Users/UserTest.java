package de.hdm_stuttgart_mi.Users;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static de.hdm_stuttgart_mi.Users.User.currentUser;

public class UserTest {
    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate moveInDate = LocalDate.parse("21.03.2020",formatter) ;
    User user = new User(1);
    private Roommate testRoommate1 = new Roommate("Karl","H.",0,"01","true",moveInDate, LocalDate.parse("21.03.2020",formatter),"/hdfjszfg");
    private Roommate testRoommate2 = new Roommate("Brigitte","Jansen",8,"01","true",moveInDate, LocalDate.parse("21.03.2020",formatter),"/hdfjszfg");


    @Test public void updateCurrentUser(){

        user = new User(1);
        currentUser.setPhonenumber("0125653472647");
        user.updateCurrentUser();
        Assert.assertEquals(currentUser.getPhonenumber(),"0125653472647");

        currentUser.setPhonenumber("93246372hfz17");
        user.updateCurrentUser();
        Assert.assertEquals(currentUser.getPhonenumber(),"0125653472647");

        currentUser.setPhonenumber("0 2938462");
        user.updateCurrentUser();
        Assert.assertEquals(currentUser.getPhonenumber(),"0125653472647");
    }
    @Test public void testAddNewmate(){
        user.addNewRoommate(testRoommate1);
        Assert.assertEquals(testRoommate1,currentUser);
        user.deleteFromList();

        user.addNewRoommate(testRoommate2);
        Assert.assertEquals(testRoommate2,currentUser);
        user.deleteFromList();
    }

}
