package de.hdm_stuttgart_mi.GroceryList;

import java.time.LocalDate;

public interface Iitem {

    //Setter
    void setContent(String content); //-

    void setAuthor(String author); //-

    void setPrice(long price);

    void setBoughtDate(LocalDate boughtDate);

    void setBoughtBy(String boughtBy);

    void setType(String type); //-


    //Getter
    String getContent();

    String getAuthor();

    long getPrice();

    LocalDate getBoughtDate();

    String getBoughtBy();

    String getType();
}
