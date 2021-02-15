package de.hdm_stuttgart_mi.Blackboard;

import java.time.LocalDate;

public class Note {

    private String content;
    private final String AUTHOR;
    private final LocalDate TIMESTAMP;
    private boolean isPinned;

    //Setter
    public void setContent(String content) {
        this.content = content;
    }
    public void setPinned(boolean isPinned) {
        this.isPinned = isPinned;
    }

    //Getter
    public String getContent() {
        return content;
    }
    public String getAuthor() {
        return AUTHOR;
    }
    public LocalDate getTimestamp() {
        return TIMESTAMP;
    }
    public boolean getIsPinned() {
        return isPinned;
    }


    public Note(String content, String author, LocalDate timestamp, boolean isPinned) {
        this.content = content;
        this.AUTHOR = author;
        this.TIMESTAMP = timestamp;
        this.isPinned = isPinned;
    }

}
