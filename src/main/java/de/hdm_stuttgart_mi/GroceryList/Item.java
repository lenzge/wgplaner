package de.hdm_stuttgart_mi.GroceryList;

import java.util.Date;

public class Item {
    private String content;
    private int price;
    private Date bought;

    public String getContent() {
        return content;
    }

    public double getPrice() {
        return price;
    }

    public void setDate(Date bought) {
        this.bought = new Date (bought.getTime());
    }

    public Date getDate() {
        return new Date(bought.getTime());
    }



    Item(String content, int price){
        this.content = content;
        this.price = price;
        bought = null;
        GroceryList.addItem(this);
    }
}
