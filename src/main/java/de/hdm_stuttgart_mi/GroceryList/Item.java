package de.hdm_stuttgart_mi.GroceryList;

import java.time.LocalDate;

public abstract class Item implements Iitem{
    private final String content;
    private final String author;
    private String price;
    private LocalDate boughtDate;
    private String boughtBy;
    private final String type;


    //Setter

    @Override
    public void boughtItem (String price, LocalDate boughtDate, String boughtBy) {

        this.price = price;
        this.boughtDate = boughtDate;
        this.boughtBy = boughtBy;
    }

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
    public String getPrice() { return price; }
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



    public Item(String type, String content, String author, String price, LocalDate boughtDate, String boughtBy) {
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
