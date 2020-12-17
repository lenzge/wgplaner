package de.hdm_stuttgart_mi.notificationAndUsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestingJSOn {
    private static final Logger log = LogManager.getLogger(TestingJSOn.class);

    public static void main(String[] args) throws ParseException, IOException {
        SimpleDateFormat formatter=new SimpleDateFormat("DD-MM-YYYY");
        Date moveInDate= formatter.parse("21-03-2020") ;
        Date birthday= formatter.parse("21-02-2000") ;
        Roommate dan = new Roommate("Danny","Kruger",3,"0129374368",true,moveInDate,birthday,"/src/resources/images/background.jpg");
        Navigation nav = new Navigation();

        Roommate lara = nav.getRoommate(0);
        log.info(lara.toString());
        nav.addCurrentRoomate(dan);
        log.info(nav.getRoommate(2).toString());
    }


}

