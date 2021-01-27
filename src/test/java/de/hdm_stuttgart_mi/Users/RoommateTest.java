package de.hdm_stuttgart_mi.Users;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static de.hdm_stuttgart_mi.Controller.ExternMethods.validPhoneNumber;

public class RoommateTest {
    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate moveInDate = LocalDate.parse("21.03.2020",formatter) ;
    Roommate testRoommate = new Roommate("Karl","H.",0,"01","true",moveInDate, LocalDate.parse("21.03.2020",formatter),"/hdfjszfg");

    @Test public void testChangePassword(){
        testRoommate.changePassword("Test");
        Assert.assertEquals(testRoommate.getPassword(),"true");

        testRoommate.changePassword("Test quwgd");
        Assert.assertEquals(testRoommate.getPassword(),"true");
        testRoommate.changePassword("Test123");
        Assert.assertEquals(testRoommate.getPassword(),"true");
        testRoommate.changePassword("Testhskugfjgjazrfhu!123");
        Assert.assertEquals(testRoommate.getPassword(),"true");
        testRoommate.changePassword("Test!1234");
        Assert.assertEquals(testRoommate.getPassword(),"Test!1234");
    }
    @Test public void testSetPhonenumber(){
        testRoommate.setPhonenumber("234376483613984");
        Assert.assertEquals(testRoommate.getPhonenumber(),"234376483613984");
        testRoommate.setPhonenumber("2343kdsjfl83613984");
        Assert.assertEquals("01",testRoommate.getPhonenumber());
        testRoommate.setPhonenumber("234376483 613984");
        Assert.assertEquals(testRoommate.getPhonenumber(),"01");
    }
    @Test public void testValidPhoneNumber(){
        Assert.assertTrue(validPhoneNumber("234376483613984"));
    }
}
