package de.hdm_stuttgart_mi.notificationAndUsers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestingJSOn {


    public static void main(String[] args) throws ParseException, IOException {
        SimpleDateFormat formatter=new SimpleDateFormat("DD-MM-YYYY");
        Date moveInDate= formatter.parse("21-03-2020") ;
        Date birthday= formatter.parse("21-02-2000") ;
        Roommate dan = new Roommate("Danny","Kruger",3,"0129374368",true,moveInDate,birthday);
        Navigation nav = new Navigation();

        nav.initroommate();
        Roommate lara = nav.getCurrent(0);
        System.out.println(lara.toString());
        nav.addCurrentRoomate(dan);
        System.out.println(nav.getCurrent(2).toString());
    }


}

