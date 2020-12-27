package de.hdm_stuttgart_mi.GroceryList;
import de.hdm_stuttgart_mi.ItemFactory.ItemFactory;
import de.hdm_stuttgart_mi.notificationAndUsers.Roommate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;


public class RunTest {

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy");

        GroceryList groceryList = new GroceryList();
        Roommate roommateTest = new Roommate("Anna", "Tajana", 10, "015735378152",
                true, LocalDate.parse("10-08-2020"), LocalDate.parse("10-08-2020"),"/src/resources/images/Lucifer.jpg");
        groceryList.initItems();
        Iitem item = ItemFactory.getInstance("food","Kartoffel", "Roommate 3");
        System.out.print(Arrays.toString(groceryList.getItemList().toArray()));
        groceryList.boughtItem(groceryList.getItemList().get(1), "3,59€", roommateTest);
        System.out.print(groceryList.toString());
        groceryList.safeItems();
    }
}
