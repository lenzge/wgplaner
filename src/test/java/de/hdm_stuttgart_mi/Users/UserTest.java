package de.hdm_stuttgart_mi.Users;

import de.hdm_stuttgart_mi.Main;
import org.junit.Assert;
import org.junit.Test;

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
    @Test public void testBackup(){
        Main main = new Main();
        main.backup();
    }
}
