package de.hdm_stuttgart_mi.Blackboard;


import org.junit.Assert;
import org.junit.Test;

import de.hdm_stuttgart_mi.Blackboard.Note;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BlackboardTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    Note testNote = new Note("Test 1 2 3","Max Mustermann", LocalDate.parse("21.01.2021", formatter),false);

    @Test
    public void testChangePinStatus() {
        testNote.setPinned(true);
        Assert.assertTrue(testNote.getIsPinned());

        testNote.setPinned(false);
        Assert.assertFalse(testNote.getIsPinned());
    }

    @Test
    public void testSetContent() {
        testNote.setContent("Test 1 2 3");
        Assert.assertEquals(testNote.getContent(),"Test 1 2 3");

        testNote.setContent("Blub");
        Assert.assertEquals(testNote.getContent(),"Blub");
        testNote.setContent("123456789");
        Assert.assertEquals(testNote.getContent(),"123456789");
    }
}
