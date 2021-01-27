package de.hdm_stuttgart_mi.Users;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static de.hdm_stuttgart_mi.Users.User.currentUser;

public class UserTest {
    @Test public void updateCurrentUser(){

        User user = new User(1);
        currentUser.setPhonenumber("0125653472647");
        user.updateCurrentUser();
        Assert.assertEquals(currentUser.getPhonenumber(),"0125653472647");

        currentUser.setPhonenumber("93246372hfz17");
        user.updateCurrentUser();
        Assert.assertEquals(currentUser.getPhonenumber(),"0125653472647");
    }
}
