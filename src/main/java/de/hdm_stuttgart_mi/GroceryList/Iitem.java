package de.hdm_stuttgart_mi.GroceryList;

import java.time.LocalDate;

public interface Iitem {

    //Setter


    void boughtItem (String price, LocalDate boughtDate, String boughtBy);


    //Getter
    String getContent();

    String getAuthor();

    String getPrice();

    LocalDate getBoughtDate();

    String getBoughtBy();

    String getType();
}
