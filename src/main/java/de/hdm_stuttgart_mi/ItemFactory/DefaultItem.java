package de.hdm_stuttgart_mi.ItemFactory;

import de.hdm_stuttgart_mi.GroceryList.Item;

import java.time.LocalDate;

public class DefaultItem extends Item {

    public DefaultItem(String type, String content, String author, long price, LocalDate boughtDate, String boughtBy) {
        super(type, content, author, price, boughtDate, boughtBy);
    }

    public DefaultItem(String type, String content, String author) {
        super(type, content, author);
    }
}
