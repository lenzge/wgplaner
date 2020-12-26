package de.hdm_stuttgart_mi.ItemFactory;

import de.hdm_stuttgart_mi.GroceryList.Iitem;

import java.time.LocalDate;

public class ItemFactory {

    public static Iitem getInstance(String type, String content, String author){

        switch (type){
            case "cleaning":
                return new CleaningItem(type, content, author);
            case "food":
                return new FoodItem(type, content, author);
            case "furniture":
                return new FurnitureItem(type, content, author);
            default:
                return new DefaultItem(type, content, author);
        }
    }

    public static Iitem getInstance(String type, String content, String author, String price, LocalDate boughtDate, String boughtBy){

        switch (type){
            case "cleaning":
                return new CleaningItem(type, content, author, price, boughtDate, boughtBy);
            case "food":
                return new FoodItem(type, content, author, price, boughtDate, boughtBy);
            case "furniture":
                return new FurnitureItem(type, content, author, price, boughtDate, boughtBy);
            default:
                return new DefaultItem(type, content, author, price, boughtDate, boughtBy);
        }
    }

}
