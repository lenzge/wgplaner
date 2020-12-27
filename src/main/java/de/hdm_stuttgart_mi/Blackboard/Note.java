package de.hdm_stuttgart_mi.Blackboard;

import java.time.LocalDate;

public class Note {

    private String content;
    private String author;
    private LocalDate timestamp;
    private boolean isPinned;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public void setIsPinned() {
        this.isPinned = isPinned;
    }

    public boolean getIsPinned(){
        return isPinned;
    }

    public Note(String content, String author, LocalDate timestamp, boolean isPinned ){
        this.content = content;
        this.author = author;
        this.timestamp = timestamp;
        this.isPinned = isPinned;
    }
}
