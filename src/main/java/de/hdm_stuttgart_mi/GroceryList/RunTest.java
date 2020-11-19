package de.hdm_stuttgart_mi.GroceryList;
import de.hdm_stuttgart_mi.notificationAndUsers.Roommate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class RunTest {

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy");

        GroceryList groceryList = new GroceryList();
        Roommate roommateTest = new Roommate("Anna", "Tajana", 10, "015735378152",
                true, formatter.parse("10-08-2020"), formatter.parse("10-08-2020"));
        groceryList.initItems();
        //Item item = new Item("Kartoffel", "Roommate 3");
        System.out.print(groceryList.toString());
        //System.out.print(Arrays.toString(groceryList.getItemList().toArray()));
        groceryList.boughtItem(groceryList.getItemList().get(1), 359, roommateTest);
        System.out.print(groceryList.toString());
    }
}
