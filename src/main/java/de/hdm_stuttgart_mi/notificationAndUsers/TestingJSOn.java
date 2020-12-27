package de.hdm_stuttgart_mi.notificationAndUsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestingJSOn {
    private static final Logger log = LogManager.getLogger(TestingJSOn.class);

    public static void main(String[] args) throws ParseException, IOException {
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate moveInDate = LocalDate.parse("21.03.2020",formatter) ;
        LocalDate birthday= LocalDate.parse("21.02.2020",formatter)  ;
       // Roommate dan = new Roommate("Danny","Kruger",3,"0129374368",true,moveInDate,birthday,"src/main/resources/images/Königstiger.jpg");
        Navigation nav = new Navigation();

        Roommate lara = nav.getRoommate(0);
        log.info(lara.toString() + lara.getProfilepic() + "" + lara.getID());
       // nav.addCurrentRoomate(dan);
        log.info(nav.getRoommate(3).toString() + nav.getRoommate(3).getProfilepic() +" "+ nav.getRoommate(3).getID());
    }


}

