package de.hdm_stuttgart_mi.Users;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RoommateTest {
    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate moveInDate = LocalDate.parse("21.03.2020",formatter) ;
    Roommate testRoommate = new Roommate("Karl","H.",0,"","true",moveInDate, LocalDate.parse("21.03.2020",formatter),"/hdfjszfg");

    @Test public void testChangePassword(){
        testRoommate.changePassword("Test");
        Assert.assertEquals(testRoommate.getPassword(),"true");
        testRoommate.changePassword("");
        Assert.assertEquals(testRoommate.getPassword(),"true");
        testRoommate.changePassword("dhuadgzgrgsfjzsgezf");
        Assert.assertEquals(testRoommate.getPassword(),"true");
        testRoommate.changePassword("Test12345");
        Assert.assertEquals(testRoommate.getPassword(),"Test12345");
    }
}
