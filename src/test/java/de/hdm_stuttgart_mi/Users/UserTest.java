package de.hdm_stuttgart_mi.Users;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserTest {
    @Test public void testDeleteUser(){
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate moveInDate = LocalDate.parse("21.03.2020",formatter) ;
        Roommate user1 = new Roommate("Karl","H.",0,"","true",moveInDate,LocalDate.parse("21.03.2020",formatter),"/hdfjszfg");
        Roommate user2 = new Roommate("Thomas","M.",1,"","true",moveInDate,LocalDate.parse("21.03.2020",formatter),"/hdfjszfg");
        Roommate user3 = new Roommate("Miriam","S.",2,"","true",moveInDate,LocalDate.parse("21.03.2020",formatter),"/hdfjszfg");

    }
}
