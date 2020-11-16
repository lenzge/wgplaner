package de.hdm_stuttgart_mi.Blackboard;

import java.util.Date;

public class Note {

    private String content;
    private String author;
    private Date timestamp;
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Note(String content, String author, Date timestamp, boolean isPinned ){
        this.content = content;
        this.author = author;
        this.timestamp = timestamp;
        this.isPinned = isPinned;
    }
}
