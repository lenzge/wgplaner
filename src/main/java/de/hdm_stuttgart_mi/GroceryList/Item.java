package de.hdm_stuttgart_mi.GroceryList;

import java.time.LocalDate;

public abstract class Item implements Iitem{
    private String content;
    private String author;
    private long price;
    private LocalDate boughtDate;
    private String boughtBy;
    private String type;


    //Setter
    @Override
    public void setContent(String content) {
        this.content = content;
    }
    @Override
    public void setAuthor(String author) {
        this.author = author;
    }
    @Override
    public void setPrice(long price) {
        this.price = price;
    }
    @Override
    public void setBoughtDate(LocalDate boughtDate) {
        this.boughtDate = boughtDate;
    }
    @Override
    public void setBoughtBy(String boughtBy) {
        this.boughtBy = boughtBy;
    }
    @Override
    public void setType(String type){ this.type = type; }


    //Getter
    @Override
    public String getContent() {
        return content;
    }
    @Override
    public String getAuthor() {
        return author;
    }
    @Override
    public long getPrice() { return price; }
    @Override
    public LocalDate getBoughtDate() {
        return boughtDate;
    }
    @Override
    public String getBoughtBy() {
        return boughtBy;
    }
    @Override
    public String getType() { return type; }



    public Item(String type, String content, String author, long price, LocalDate boughtDate, String boughtBy) {
        this.content = content;
        this.author = author;
        this.price = price;
        this.boughtDate = boughtDate;
        this.boughtBy = boughtBy;
        this.type = type;
        GroceryList.putOnList(this);

    }

    public Item(String type, String content, String author){
        this.content = content;
        this.author = author;
        this.type = type;
        GroceryList.putOnList(this);
    }
}
