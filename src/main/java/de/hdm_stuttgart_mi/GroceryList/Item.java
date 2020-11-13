package de.hdm_stuttgart_mi.GroceryList;

import de.hdm_stuttgart_mi.notificationAndUsers.Roommate;

import java.util.Date;

public class Item {
    private String content;
    private String author;
    private int price;
    private Date boughtDate;
    private String boughtBy;


    //Setter
    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setBoughtDate(Date boughtDate) {
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

    public int getPrice() {
        return price;
    }

    public Date getBoughtDate() {
        return boughtDate;
    }

    public String getBoughtBy() {
        return boughtBy;
    }


    public Item(String content, String author, int price, Date boughtDate, String boughtBy) {
        this.content = content;
        this.author = author;
        this.price = price;
        this.boughtDate = boughtDate;
        this.boughtBy = boughtBy;
    }

    Item(String content, String author){
        this.content = content;
        this.author = author;
        //GroceryList.putOnList(this);
    }
}
