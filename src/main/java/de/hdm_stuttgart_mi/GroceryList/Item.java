package de.hdm_stuttgart_mi.GroceryList;

import de.hdm_stuttgart_mi.notificationAndUsers.Roommate;

import java.time.LocalDate;
import java.util.Date;

public class Item {
    private String content;
    private String author;
    private long price;
    private LocalDate boughtDate;
    private String boughtBy;


    //Setter
    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setBoughtDate(LocalDate boughtDate) {
        this.boughtDate = boughtDate;
    }

    public void setBoughtBy(String boughtBy) {
        this.boughtBy = boughtBy;
    }


    //Getter
    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public long getPrice() { return price; }

    public LocalDate getBoughtDate() {
        return boughtDate;
    }

    public String getBoughtBy() {
        return boughtBy;
    }



    public Item(String content, String author, long price, LocalDate boughtDate, String boughtBy) {
        this.content = content;
        this.author = author;
        this.price = price;
        this.boughtDate = boughtDate;
        this.boughtBy = boughtBy;
        GroceryList.putOnList(this);

    }

    public Item(String content, String author){
        this.content = content;
        this.author = author;
        GroceryList.putOnList(this);
    }
}
