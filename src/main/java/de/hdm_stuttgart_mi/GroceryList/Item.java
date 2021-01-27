package de.hdm_stuttgart_mi.GroceryList;

import java.time.LocalDate;

public abstract class Item implements Iitem{
    private final String CONTENT;
    private final String AUTHOR;
    private String price;
    private LocalDate boughtDate;
    private String boughtBy;
    private final String TYPE;


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
        return CONTENT;
    }
    @Override
    public String getAuthor() {
        return AUTHOR;
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
    public String getType() { return TYPE; }



    public Item(String type, String content, String author, String price, LocalDate boughtDate, String boughtBy) {
        this.CONTENT = content;
        this.AUTHOR = author;
        this.price = price;
        this.boughtDate = boughtDate;
        this.boughtBy = boughtBy;
        this.TYPE = type;

    }

    public Item(String type, String content, String author){
        this.CONTENT = content;
        this.AUTHOR = author;
        this.TYPE = type;
    }

    @Override
    public String toString() {
        return "Item{" +
                "content='" + CONTENT + '\'' +
                ", author='" + AUTHOR + '\'' +
                ", price='" + price + '\'' +
                ", boughtDate=" + boughtDate +
                ", boughtBy='" + boughtBy + '\'' +
                ", type='" + TYPE + '\'' +
                '}';
    }
}
