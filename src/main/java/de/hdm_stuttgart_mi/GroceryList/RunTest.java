package de.hdm_stuttgart_mi.GroceryList;
import java.util.ArrayList;
import java.util.Arrays;


public class RunTest {

    public static void main(String[] args) {

        GroceryList groceryList = new GroceryList();
        groceryList.initItems();
        //Item item = new Item("Kartoffel", "Roommate 3");
        System.out.print(groceryList.getItemList());
        //System.out.print(Arrays.toString(groceryList.getItemList().toArray()));
    }
}
