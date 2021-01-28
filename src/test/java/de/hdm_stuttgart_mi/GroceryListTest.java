package de.hdm_stuttgart_mi;

import de.hdm_stuttgart_mi.GroceryList.GroceryList;
import de.hdm_stuttgart_mi.GroceryList.Iitem;
import de.hdm_stuttgart_mi.GroceryList.STATUS;
import de.hdm_stuttgart_mi.ItemFactory.ItemFactory;
import de.hdm_stuttgart_mi.Users.Roommate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.fail;

public class GroceryListTest {
    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate moveInDate = LocalDate.parse("21.03.2020",formatter);
    Roommate testRoommate = new Roommate("Karl","H.",0,"01","true",moveInDate, LocalDate.parse("21.03.2020",formatter),"/hdfjszfg");
    GroceryList groceryList = new GroceryList();

    @Before
    public void createTestItemList(){
        groceryList.deleteAllItems();
        groceryList.addItem("Lebensmittel","Karotten", testRoommate);
        groceryList.addItem("Lebensmittel","Chinakohl", testRoommate);
        groceryList.addItem("Reinigung","WC-Reiniger", testRoommate);
        groceryList.addItem("Sonstiges","Alufolie", testRoommate);
    }


    @Test
    public void testAddItem() {
        //new Item -> works
        Assert.assertEquals(groceryList.addItem("Lebensmittel","Zwiebeln", testRoommate), STATUS.RIGHT);
        Assert.assertEquals(groceryList.getItemList().get(groceryList.getItemList().size() -1).toString(),
                "Item{content='Zwiebeln', author='Karl H.', price='null', boughtDate=null, boughtBy='null', type='Lebensmittel'}");

        //Item that already exists -> doesn't work
        Assert.assertEquals(groceryList.addItem("Lebensmittel","Karotten", testRoommate), STATUS.EXISTS);
        Assert.assertEquals(groceryList.getItemList().get(groceryList.getItemList().size() -1).toString(),
                "Item{content='Zwiebeln', author='Karl H.', price='null', boughtDate=null, boughtBy='null', type='Lebensmittel'}");

        //Item without content -> doesn't work
        Assert.assertEquals(groceryList.addItem("Lebensmittel","", testRoommate), STATUS.EMPTY);
        Assert.assertEquals(groceryList.getItemList().get(groceryList.getItemList().size() -1).toString(),
                "Item{content='Zwiebeln', author='Karl H.', price='null', boughtDate=null, boughtBy='null', type='Lebensmittel'}");

    }

    @Test
    public void testDeleteItem(){
        Iitem toDelete = groceryList.getItemList().get(3);
        groceryList.deleteItem(toDelete);
        Assert.assertFalse( groceryList.getItemList().parallelStream().anyMatch(i -> i.getContent().equals(toDelete.getContent())));

    }

    @Test
    public void testBoughtItem(){
        Iitem toBuy = groceryList.getItemList().get(3);

        //wrong regex
        Assert.assertFalse(groceryList.boughtItem(toBuy, "1hxy", testRoommate));
        Assert.assertEquals(groceryList.getItemList().get(groceryList.getItemList().size() -1).toString(),
                "Item{content='Alufolie', author='Karl H.', price='null', boughtDate=null, boughtBy='null', type='Sonstiges'}");

        //no price
        Assert.assertFalse(groceryList.boughtItem(toBuy, "", testRoommate));
        Assert.assertEquals(groceryList.getItemList().get(groceryList.getItemList().size() -1).toString(),
                "Item{content='Alufolie', author='Karl H.', price='null', boughtDate=null, boughtBy='null', type='Sonstiges'}");

        //right price
        Assert.assertTrue(groceryList.boughtItem(toBuy, "1,10€", testRoommate));
        Assert.assertEquals(groceryList.getItemList().get(groceryList.getItemList().size() -1).toString(),
                "Item{content='Alufolie', author='Karl H.', price='1,10€', boughtDate=" + LocalDate.now()+ ", boughtBy='Karl H.', type='Sonstiges'}");


    }

}
