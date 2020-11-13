package de.hdm_stuttgart_mi.GroceryList;
import de.hdm_stuttgart_mi.notificationAndUsers.Roommate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GroceryList {

    private static List<Item> itemList = new ArrayList<Item>();
    SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy");

    public void initItems(){
        File file = new File("src\\main\\resources\\JSON\\items.json");
        try{
            String json_content = new String(Files.readAllBytes(Paths.get(file.toURI())),"UTF-8");
            JSONObject json = new JSONObject(json_content);

            JSONArray jsonArray = json.getJSONArray("items");
            for(int i=0; i<jsonArray.length();i++){
                JSONObject tempJasonObject = jsonArray.getJSONObject(i);

                String content = tempJasonObject.getString("content");
                String author = (String) tempJasonObject.get("author");


                if (tempJasonObject.isNull("price")){
                    itemList.add(new Item(content, author));
                }
                else {
                    int price = tempJasonObject.getInt("price");
                    Date boughtDate = formatter.parse(tempJasonObject.getString("boughtDate"));
                    String boughtBy = (String) tempJasonObject.get("boughtBy");
                    itemList.add(new Item(content, author, price, boughtDate, boughtBy));
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public static void putOnList(Item item){
        itemList.add(item);
    }

    public GroceryList() {

    }

    /**

    //Collection/JsonFile with all items


    //If an item has a Date, it obviously got bought
    public boolean isBought(Item item) {
        return item.getDate() != null;
    }

    //If an item got bought last month it gets deleted
    public void isOld(Item item){
        //if (got bought last month) delete item
    }

    //Price as String
    public String priceToString(Item item){
        return "price";
    }

    public static void addItem(Item item) {

    }
 **/

}
