package de.hdm_stuttgart_mi.Blackboard;


import de.hdm_stuttgart_mi.notificationAndUsers.Roommate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class TestBlackboard {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        Blackboard blackboard = new Blackboard();
        Roommate testRoommate = new Roommate("Marcel", "Alex", 333, "01712151427", true, LocalDate.parse("10.11.2010",formatter), LocalDate.parse("20.12.2020",formatter),"/src/resources/images/Lucifer.jpg");

        blackboard.initNote();

//        Note note = new Note("Katze nicht rauslassen",testRoommate.getFullname(),LocalDate.parse("22.12.2222",formatter),true);
        blackboard.addNote("Katze nicht rauslassen",testRoommate, LocalDate.now(),true);
        //System.out.println(Arrays.toString(blackboard.getNoteList().toArray()));
        blackboard.safeBlackboard();
        blackboard.initNote();
        System.out.println(blackboard.toString());

    }
}
