package de.hdm_stuttgart_mi.ItemFactory;

import de.hdm_stuttgart_mi.GroceryList.Iitem;



import java.time.LocalDate;

public class ItemFactory {

    //all types for initialize in GUI
    private static final String[] itemFactory = {"Lebensmittel","Reinigung","Sonstiges"};

    public static Iitem getInstance(String type, String content, String author){

        switch (type){
            case "Reinigung":
                return new CleaningItem(type, content, author);
            case "Lebensmittel":
                return new FoodItem(type, content, author);
            default:
                return new DefaultItem(type, content, author);
        }
    }

    public static Iitem getInstance(String type, String content, String author, String price, LocalDate boughtDate, String boughtBy){

        switch (type){
            case "Reinigung":
                return new CleaningItem(type, content, author, price, boughtDate, boughtBy);
            case "Lebensmittel":
                return new FoodItem(type, content, author, price, boughtDate, boughtBy);
            default:
                return new DefaultItem(type, content, author, price, boughtDate, boughtBy);
        }
    }

    public static String[] getAllItemTypes(){
        return itemFactory;
    }

}
