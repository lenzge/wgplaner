package de.hdm_stuttgart_mi.GroceryList;
import java.time.LocalDate;
import java.util.Date;

public class GroceryList {

    //Collection/JasonFile with all items


    //If an item has a Date, it obviously got bought
    public boolean isBought(Item item) {
        return item.getDate() != null;
    }

    //If an item got bought last month it gets deleted
    public void isOld(Item item){
        //if (got bought last month) delete item
    }

    //Price as String
    public String priceToString(Item item){
        return "price";
    }

    public static void addItem(Item item) {

    }

}
