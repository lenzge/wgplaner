package de.hdm_stuttgart_mi.ItemFactory;

import de.hdm_stuttgart_mi.GroceryList.Item;

import java.time.LocalDate;

public class FurnitureItem extends Item {

    public FurnitureItem(String type, String content, String author, String price, LocalDate boughtDate, String boughtBy) {
        super(type, content, author, price, boughtDate, boughtBy);
    }

    public FurnitureItem(String type, String content, String author) {
        super(type, content, author);
    }
}
